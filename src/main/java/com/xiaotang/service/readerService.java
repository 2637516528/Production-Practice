package com.xiaotang.service;

import com.xiaotang.pojo.reader;
import com.xiaotang.pojo.feedback;

import java.util.List;

public interface readerService {
    reader loginReader (String sno, String password);

    List<reader> selectAll();

    reader selectReader(int id);

    void addReader(reader reader);

    void deleteById(int id);

    List<reader> selectByCondition(reader reader);

    void registerReader(reader reader);

    void updateReader(reader reader);



}
