package com.xiaotang.servlet;

import com.alibaba.fastjson.JSON;
import com.xiaotang.pojo.admin;
import com.xiaotang.service.impl.adminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/admin/*")
public class adminServlet extends BaseServlet {
    private com.xiaotang.service.adminService adminService=new adminServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service进行查询
        List<admin> admins = adminService.selectAll();
        //2. 转为JSON
        String jsonString = JSON.toJSONString(admins);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.getWriter().write(jsonString);
        System.out.println(admins);
    }


    public void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("************************");
        System.out.println(request.getParameter("username"));
        System.out.println("************************");
        Enumeration<String> parameterNames = request.getParameterNames();
        System.out.println(parameterNames);
        System.out.println(username);
        System.out.println(password);
        admin admin1 = adminService.loginAdmin(username, password);
        if(admin1 != null){
//             登陆成功
            HttpSession session = request.getSession();
            session.setAttribute("admin",admin1);
            response.getWriter().write("success");


        }else {
            // 登陆失败
            response.getWriter().write("fail");
        }


    }



}
