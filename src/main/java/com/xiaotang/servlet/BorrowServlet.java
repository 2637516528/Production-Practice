package com.xiaotang.servlet;

import com.alibaba.fastjson.JSON;
import com.xiaotang.pojo.Borrow;
import com.xiaotang.service.BorrowService;
import com.xiaotang.service.impl.BorrowServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/borrow/*")
public class BorrowServlet extends BaseServlet {
    private BorrowService borrowService=new BorrowServiceImpl();

    public void selectAuditBorrowByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        Borrow borrow = JSON.parseObject(s, Borrow.class);
        System.out.println(borrow);
        List<Borrow> Borrows = borrowService.selectAuditBorrowByCondition(borrow);
        System.out.println(Borrows);
        String jsonString = JSON.toJSONString(Borrows);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.getWriter().write(jsonString);
        System.out.println(Borrows);

    }

    public void selectBorrowByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        Borrow borrow = JSON.parseObject(s, Borrow.class);
        System.out.println(borrow);
        List<Borrow> Borrows = borrowService.selectBorrowByCondition(borrow);
        System.out.println(Borrows);
        String jsonString = JSON.toJSONString(Borrows);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.getWriter().write(jsonString);
        System.out.println(Borrows);

    }

    public void audit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        boolean isSuccess = borrowService.audit(Integer.parseInt(idStr));
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.getWriter().write(isSuccess ? "success" : "fail");
    }



}
