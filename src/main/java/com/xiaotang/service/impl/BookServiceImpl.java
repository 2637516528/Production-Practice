package com.xiaotang.service.impl;

import com.xiaotang.enums.BookStatusEnum;
import com.xiaotang.mapper.bookMapper;
import com.xiaotang.pojo.Book;
import com.xiaotang.pojo.Borrow;
import com.xiaotang.service.BookService;
import com.xiaotang.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookServiceImpl implements BookService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<Book> selectAll() {
        SqlSession sqlSession = factory.openSession();
        bookMapper mapper = sqlSession.getMapper(bookMapper.class);
        List<Book> Books = mapper.selectAll();
        sqlSession.close();
        return Books;
    }

    @Override
    public void addBook(Book book) {
        SqlSession sqlSession = factory.openSession();
        bookMapper mapper = sqlSession.getMapper(bookMapper.class);
        mapper.addBook(book);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = factory.openSession();
        bookMapper mapper = sqlSession.getMapper(bookMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Book selectByIdBook(int id) {
        SqlSession sqlSession = factory.openSession();
        bookMapper mapper = sqlSession.getMapper(bookMapper.class);
        Book books = mapper.selectByIdBook(id);
        sqlSession.close();
        return books;
    }

    @Override
    public Book selectById(int id) {
        SqlSession sqlSession = factory.openSession();
        bookMapper mapper = sqlSession.getMapper(bookMapper.class);

        Book books = mapper.selectByIdBook(id);
        sqlSession.close();
        return books;
    }

    @Override
    public List<Book> selectByCondition(Book book) {
        SqlSession sqlSession = factory.openSession();
        bookMapper mapper = sqlSession.getMapper(bookMapper.class);
        if(book.getBname()!=null&&book.getBname().length()>0){
            book.setBname("%"+book.getBname()+"%");
        }
        if(book.getAuthor()!=null&&book.getAuthor().length()>0){
            book.setAuthor("%"+book.getAuthor()+"%");
        }
        if(book.getType()!=null&&book.getType().length()>0){
            book.setType("%"+book.getType()+"%");
        }
        System.out.println(book);
        List<Book> Books = mapper.selectByCondition(book);
        sqlSession.close();
        return Books;

    }

    /**
     * TODO 此方法有bug，需要开启MySQL事务
     *
     * @param bid
     * @param sno
     */
    @Override
    public synchronized void borrow(int bid, String sno) {
        SqlSession sqlSession = factory.openSession();
        bookMapper mapper = sqlSession.getMapper(bookMapper.class);
        Book book1 = mapper.selectByIdBook(bid); //找到要借的书的相关信息

        if (BookStatusEnum.BEING_BORROWED.getCode().equals(book1.getStatus())) {
            throw new RuntimeException("该图书已被借阅");
        }

        //获取开始和结束时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        Date date = now.getTime();

        String begintime = sdf.format(date);

        now.add(Calendar.MONTH, 1); //现在时间是1个月后
        date = now.getTime();

        String endtime = sdf.format(date);

        try {
            // 开启事务
            mapper.borrow(sno,bid, book1.getBname(),begintime,endtime);

            mapper.updateStatus(book1);

            // 全部成功 commit
            sqlSession.commit();

        } catch (Exception e) {
            e.printStackTrace();

            // 全部失败
            sqlSession.rollback();

        } finally {
            // 无论操作失败还是成功，都会执行
            sqlSession.close();
        }

    }

    @Override
    public List<Borrow> selectBorrow() {
        SqlSession sqlSession = factory.openSession();
        bookMapper mapper = sqlSession.getMapper(bookMapper.class);
        List<Borrow> Borrows = mapper.selectBorrow();
        sqlSession.close();
        return Borrows;
    }

    @Override
    public List<Borrow> selectAuditBorrow() {
        SqlSession sqlSession = factory.openSession();
        bookMapper mapper = sqlSession.getMapper(bookMapper.class);
        List<Borrow> Borrows = mapper.selectAuditBorrow();
        sqlSession.close();
        return Borrows;
    }

    @Override
    public List<Borrow> selectBySno(String sno) {
        SqlSession sqlSession = factory.openSession();
        bookMapper mapper = sqlSession.getMapper(bookMapper.class);
        List<Borrow> borrow = mapper.selectBySno(sno);
        sqlSession.close();
        return borrow;
    }

    @Override
    public void returnBook(int bid) {
        SqlSession sqlSession = factory.openSession();
        bookMapper mapper = sqlSession.getMapper(bookMapper.class);

        //获取开始和结束时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        Date date = now.getTime();

        String endtime = sdf.format(date);

        // 并没有判断真正图书的状态是否为存库状态
        Book book = mapper.selectByIdBook(bid);
        if (BookStatusEnum.ON_SHELF.getCode().equals(book.getStatus())) {
            return;
        }

        try {
            mapper.returnBook(bid,endtime);

            mapper.updateStatus2(bid);

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void updateBook(Book book) {
        SqlSession sqlSession = factory.openSession();
        bookMapper mapper = sqlSession.getMapper(bookMapper.class);
        mapper.updateBook(book);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void importBooks(List<Book> importList) {
        if (importList == null || importList.isEmpty()) {
            return;
        }
        SqlSession sqlSession = factory.openSession();
        try {
            bookMapper mapper = sqlSession.getMapper(bookMapper.class);

            mapper.insertBatch(importList);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }


    }


}
