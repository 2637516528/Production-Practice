package com.xiaotang.servlet;

import com.alibaba.fastjson.JSON;
import com.xiaotang.pojo.feedback;
import com.xiaotang.service.impl.feedbackServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/feedback/*")
public class feedbackServlet extends BaseServlet {
    private com.xiaotang.service.feedbackService feedbackService=new feedbackServiceImpl();

    public void selectAllFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service进行查询
        List<feedback> feedbacks = feedbackService.selectAllFeedback();
        //2. 转为JSON
        String jsonString = JSON.toJSONString(feedbacks);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.getWriter().write(jsonString);
        System.out.println(feedbacks);

    }

    public void addFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        System.out.println("!!!"+s);
        feedback feedback1 = JSON.parseObject(s, com.xiaotang.pojo.feedback.class);
        System.out.println(feedback1);
        feedbackService.addFeedback(feedback1);
        response.getWriter().write("success");

    }



}
