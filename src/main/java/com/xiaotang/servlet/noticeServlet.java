package com.xiaotang.servlet;

import com.alibaba.fastjson.JSON;
import com.xiaotang.pojo.notice;
import com.xiaotang.service.impl.noticeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/notice/*")
public class noticeServlet extends BaseServlet {
    private com.xiaotang.service.noticeService noticeService=new noticeServiceImpl();

    public void selectAllNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service进行查询
        List<notice> notices = noticeService.selectAllNotice();
        //2. 转为JSON
        String jsonString = JSON.toJSONString(notices);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.getWriter().write(jsonString);
        System.out.println(notices);
    }

    public void addNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        notice notice = JSON.parseObject(s, com.xiaotang.pojo.notice.class);
        System.out.println(notice);
        noticeService.addNotice(notice);
        response.getWriter().write("success");

    }



}
