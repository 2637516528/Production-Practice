package com.xiaotang.mapper;

import com.xiaotang.pojo.Book;
import com.xiaotang.pojo.Borrow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface bookMapper {
    /**
     *
     * @return
     */
    List<Book> selectAll();

    Book selectByIdBook(int id);

    /**
     *
     * @param book
     */
    void addBook(Book book);

    void deleteById(int id);

    List<Book> selectByCondition(Book book);

//    书被借走
    void updateStatus(Book book);
//    插入借书记录
    void borrow (@Param("sno") String sno, @Param("bid") int bid, @Param("bname") String bname,
                 @Param("begintime") String begintime, @Param("endtime") String endtime);

//    查询当前借阅
    List<Borrow> selectBorrow();


    List<Borrow> selectAuditBorrow();

    List<Borrow> selectBySno(String sno);

//    还书操作

    void returnBook ( @Param("bid") int bid,@Param("endtime") String endtime);

    void updateStatus2(int id);

//    修改
    void updateBook(Book book);

    int insertBatch(List<Book> importList);

}
