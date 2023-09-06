package com.xiaotang.service.impl;

import com.xiaotang.mapper.feedbackMapper;
import com.xiaotang.pojo.feedback;
import com.xiaotang.service.feedbackService;
import com.xiaotang.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class feedbackServiceImpl implements feedbackService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<feedback> selectAllFeedback() {
        SqlSession sqlSession = factory.openSession();
        feedbackMapper mapper = sqlSession.getMapper(feedbackMapper.class);
        List<feedback> feedbacks = mapper.selectAllFeedback();
        sqlSession.close();
        return feedbacks;
    }

    @Override
    public void addFeedback(feedback feedback) {
        SqlSession sqlSession = factory.openSession();
        feedbackMapper mapper = sqlSession.getMapper(feedbackMapper.class);
        mapper.addFeedback(feedback);
        sqlSession.commit();
        sqlSession.close();
    }
}
