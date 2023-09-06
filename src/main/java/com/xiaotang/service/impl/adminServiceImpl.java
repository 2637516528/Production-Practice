package com.xiaotang.service.impl;

import com.xiaotang.mapper.adminMapper;
import com.xiaotang.pojo.admin;
import com.xiaotang.service.adminService;
import com.xiaotang.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class adminServiceImpl implements adminService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<admin> selectAll() {
        SqlSession sqlSession = factory.openSession();
        adminMapper mapper = sqlSession.getMapper(adminMapper.class);
        List<admin> admins = mapper.selectAll();
        sqlSession.close();
        return admins;
    }

    @Override
    public admin loginAdmin(String username, String password) {
        SqlSession sqlSession = factory.openSession();
        adminMapper mapper = sqlSession.getMapper(adminMapper.class);
        admin admin1 = mapper.loginAdmin(username,password);
        sqlSession.close();
        return admin1;
    }
}
