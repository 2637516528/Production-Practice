package com.xiaotang.service;

import com.xiaotang.pojo.Book;
import com.xiaotang.pojo.Borrow;

import java.util.List;

public interface BookService {

    List<Book> selectAll();
    Book selectByIdBook(int id);
    Book selectById(int id);
    void addBook(Book book);
    void deleteById(int id);
    List<Book> selectByCondition(Book book);
    void borrow(int bid,String sno);

    List<Borrow> selectBorrow();
    List<Borrow> selectAuditBorrow();
    List<Borrow> selectBySno(String sno);

    /**
     * 管理员审核通过
     *
     * @param bid 图书ID
     */
    void returnBook(int bid);

    void updateBook(Book book);

    /**
     * 图书导入
     *
     * @param importList
     */
    void importBooks(List<Book> importList);
}
