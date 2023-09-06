package com.xiaotang.service;

import com.xiaotang.pojo.notice;

import java.util.List;

public interface noticeService {

    List<notice> selectAllNotice();

    void addNotice(notice notice);
}
