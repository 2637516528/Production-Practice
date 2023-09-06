package com.xiaotang.service.impl;

import com.xiaotang.mapper.readerMapper;
import com.xiaotang.pojo.reader;
import com.xiaotang.pojo.feedback;
import com.xiaotang.mapper.feedbackMapper;
import com.xiaotang.service.readerService;
import com.xiaotang.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import java.util.List;

public class readerServiceImpl implements readerService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public reader loginReader(String sno, String password) {
        SqlSession sqlSession = factory.openSession();
        readerMapper mapper = sqlSession.getMapper(readerMapper.class);
        reader reader1 = mapper.loginReader(sno, password);
        sqlSession.close();
        return reader1;
    }

    @Override
    public List<reader> selectAll() {
        SqlSession sqlSession = factory.openSession();
        readerMapper mapper = sqlSession.getMapper(readerMapper.class);
        List<reader> readers = mapper.selectAll();
        sqlSession.close();
        return readers;
    }

    @Override
    public reader selectReader(int id) {
        SqlSession sqlSession = factory.openSession();
        readerMapper mapper = sqlSession.getMapper(readerMapper.class);
        reader reader1 = mapper.selectReader(id);
        sqlSession.close();
        return reader1;
    }

    @Override
    public void addReader(reader reader) {
        SqlSession sqlSession = factory.openSession();
        readerMapper mapper = sqlSession.getMapper(readerMapper.class);
        mapper.addReader(reader);
        sqlSession.commit();
        sqlSession.close();

    }

    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = factory.openSession();
        readerMapper mapper = sqlSession.getMapper(readerMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateReader(reader reader) {
        SqlSession sqlSession = factory.openSession();
        readerMapper mapper = sqlSession.getMapper(readerMapper.class);
        mapper.updateReader(reader);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<reader> selectByCondition(reader reader) {
        SqlSession sqlSession = factory.openSession();
        readerMapper mapper = sqlSession.getMapper(readerMapper.class);
        if(reader.getSno()!=null&&reader.getSno().length()>0){
            reader.setSno("%"+reader.getSno()+"%");
        }
        if(reader.getName()!=null&&reader.getName().length()>0){
            reader.setName("%"+reader.getName()+"%");
        }
        List<com.xiaotang.pojo.reader> readers = mapper.selectByCondition(reader);
        sqlSession.close();
        return readers;
    }

    @Override
    public void registerReader(reader reader) {
        SqlSession sqlSession = factory.openSession();
        readerMapper mapper = sqlSession.getMapper(readerMapper.class);
        mapper.registerReader(reader);
        sqlSession.commit();
        sqlSession.close();
    }


}
