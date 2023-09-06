package com.xiaotang.mapper;

import com.xiaotang.pojo.admin;
import com.xiaotang.pojo.Borrow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BorrowMapper {

    /**
     * 查询当前借阅
     *
     * @param borrow
     * @return
     */
    List<Borrow> selectBorrowByCondition(Borrow borrow);

    /**
     * 根据主键ID查询借阅记录
     *
     * @param id
     * @return
     */
    Borrow selectByNum(int num);

    int audit(Borrow borrow);

}
