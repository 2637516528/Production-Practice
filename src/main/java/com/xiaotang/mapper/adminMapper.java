package com.xiaotang.mapper;

import com.xiaotang.pojo.admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface adminMapper {
    /**
     *
     * @return
     */
    List<admin> selectAll();

    /**
     *
     * @param username
     * @param password
     * @return
     */
    admin loginAdmin(@Param("username") String username, @Param("password") String password);

}
