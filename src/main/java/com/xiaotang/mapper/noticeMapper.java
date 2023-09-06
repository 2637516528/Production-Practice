package com.xiaotang.mapper;

import com.xiaotang.pojo.notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface noticeMapper {

    List<notice> selectAllNotice();

    void addNotice(@Param("noticedesc") String noticedesc,
                   @Param("posttime") String posttime);


}
