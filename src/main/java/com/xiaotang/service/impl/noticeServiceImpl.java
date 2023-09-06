package com.xiaotang.service.impl;

import com.xiaotang.mapper.noticeMapper;
import com.xiaotang.pojo.notice;
import com.xiaotang.service.noticeService;
import com.xiaotang.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class noticeServiceImpl implements noticeService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<notice> selectAllNotice() {
        SqlSession sqlSession = factory.openSession();
        noticeMapper mapper = sqlSession.getMapper(noticeMapper.class);
        List<notice> notices = mapper.selectAllNotice();
        sqlSession.close();
        return notices;
    }

    @Override
    public void addNotice(notice notice) {
        SqlSession sqlSession = factory.openSession();
        noticeMapper mapper = sqlSession.getMapper(noticeMapper.class);

        //获取开始和结束时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        Date date = now.getTime();

        String posttime = sdf.format(date);

        mapper.addNotice(notice.getNoticedesc(),posttime);
        sqlSession.commit();
        sqlSession.close();
    }
}
