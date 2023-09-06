package com.xiaotang.mapper;

import com.xiaotang.pojo.feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface feedbackMapper {

    List<feedback> selectAllFeedback();

    void addFeedback(feedback feedback);


}
