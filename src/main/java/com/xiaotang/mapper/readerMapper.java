package com.xiaotang.mapper;

import com.xiaotang.pojo.reader;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface readerMapper {
    reader loginReader(@Param("sno") String sno, @Param("password") String password);

    List<reader> selectAll();

    reader selectById(int id);

    reader selectReader(int id);

    void addReader(reader reader);

    void deleteById(int id);

    //    修改
    void updateReader(reader reader);

    List<reader> selectByCondition(reader reader);

    void registerReader(reader reader);

}
