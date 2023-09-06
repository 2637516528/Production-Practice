package com.xiaotang.service.impl;

import com.xiaotang.enums.BorrowStatusEnum;
import com.xiaotang.mapper.BorrowMapper;
import com.xiaotang.pojo.Borrow;

import com.xiaotang.service.BorrowService;
import com.xiaotang.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BorrowServiceImpl implements BorrowService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Borrow> selectAuditBorrowByCondition(Borrow borrow) {

        borrow.setStatus(BorrowStatusEnum.AUDIT.getCode());

        return this.selectBorrowByCondition(borrow);

    }

    @Override
    public List<Borrow> selectBorrowByCondition(Borrow borrow) {
        SqlSession sqlSession = factory.openSession();
        BorrowMapper mapper = sqlSession.getMapper(BorrowMapper.class);
        if(borrow.getSno()!=null&&borrow.getSno().length()>0){
            borrow.setSno("%"+borrow.getSno()+"%");
        }
        if(borrow.getBname()!=null&&borrow.getBname().length()>0){
            borrow.setBname("%"+borrow.getBname()+"%");
        }

        System.out.println(borrow);
        List<Borrow> borrows = mapper.selectBorrowByCondition(borrow);
        sqlSession.close();
        return borrows;

    }

    /**
     * 还书申请
     *
     * @param bid 借阅记录的ID
     * @return
     */
    @Override
    public boolean audit(int bid) {
        SqlSession sqlSession = factory.openSession();
        BorrowMapper mapper = sqlSession.getMapper(BorrowMapper.class);

        Borrow borrow = mapper.selectByNum(bid);
        if (borrow == null) {
            // 找不到借阅记录
            return false;
        }
        if (!BorrowStatusEnum.BEING_BORROWED.getCode().equals(borrow.getStatus())) {
            // 借阅记录不是已还书状态
            return false;
        }

        borrow.setStatus(BorrowStatusEnum.AUDIT.getCode());

        try {
            // sql修改
            mapper.audit(borrow);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return true;
    }

}
