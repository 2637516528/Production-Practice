package com.xiaotang.service;

import com.xiaotang.pojo.feedback;
import com.xiaotang.pojo.notice;

import java.util.List;

public interface feedbackService {

    List<feedback> selectAllFeedback();

    void addFeedback(feedback feedback);
}
