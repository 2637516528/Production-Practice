package com.xiaotang.service;

import com.xiaotang.pojo.admin;

import java.util.List;

public interface adminService {

    List<admin> selectAll();

    admin loginAdmin(String username, String password);


}
