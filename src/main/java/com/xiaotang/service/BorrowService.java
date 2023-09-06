package com.xiaotang.service;

import com.xiaotang.pojo.Borrow;

import java.util.List;

public interface BorrowService {

    List<Borrow> selectAuditBorrowByCondition(Borrow borrow);

    List<Borrow> selectBorrowByCondition(Borrow borrow);

    /**
     * 还书申请
     *
     * @param bid 借阅记录的ID
     * @return
     */
    boolean audit(int bid);
}
