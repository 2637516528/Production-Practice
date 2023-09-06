package com.xiaotang.servlet;

import com.alibaba.fastjson.JSON;
import com.xiaotang.pojo.reader;
import com.xiaotang.service.impl.readerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/reader/*")
public class readerServlet extends BaseServlet {
    private com.xiaotang.service.readerService readerService=new readerServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service进行查询
        List<reader> readers = readerService.selectAll();
        //2. 转为JSON
        String jsonString = JSON.toJSONString(readers);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.getWriter().write(jsonString);
        System.out.println(readers);
    }



    public void loginReader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sno = request.getParameter("sno");
        String password = request.getParameter("password");
        System.out.println(sno);
        System.out.println(password);
        reader reader1 = readerService.loginReader(sno, password);
        if(reader1 != null){
//             登陆成功
            HttpSession session = request.getSession();
            session.setAttribute("reader",reader1);
            response.getWriter().write("success");

        }else {
            // 登陆失败
            response.getWriter().write("fail");
        }


    }

    public void message(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("reader"));
        Object reader = session.getAttribute("reader");
        System.out.println(reader);
        com.xiaotang.pojo.reader reader1 = (com.xiaotang.pojo.reader) reader;
        System.out.println(reader1);

        String jsonString = JSON.toJSONString(reader1);
        System.out.println(jsonString);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void addReader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        com.xiaotang.pojo.reader reader1 = JSON.parseObject(s, com.xiaotang.pojo.reader.class);
        System.out.println(reader1);
        readerService.addReader(reader1);
        response.getWriter().write("success");

    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        System.out.println(s);
        int[] id = JSON.parseObject(s, int[].class);
        System.out.println(id[0]);
        readerService.deleteById(id[0]);
        response.getWriter().write("success");

    }


    public void selectByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        com.xiaotang.pojo.reader reader1 = JSON.parseObject(s, com.xiaotang.pojo.reader.class);
        List<com.xiaotang.pojo.reader> readers = readerService.selectByCondition(reader1);
        String jsonString = JSON.toJSONString(readers);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.getWriter().write(jsonString);
        System.out.println(readers);

    }

    public void registerReader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        com.xiaotang.pojo.reader reader1 = JSON.parseObject(s, com.xiaotang.pojo.reader.class);
        System.out.println(reader1);
        readerService.registerReader(reader1);
        response.getWriter().write("success");

    }

    public void updateReader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        System.out.println(s);
        com.xiaotang.pojo.reader reader1 = JSON.parseObject(s, com.xiaotang.pojo.reader.class);
        System.out.println(reader1);
        readerService.updateReader(reader1);
        response.getWriter().write("success");

    }

    public void selectReader(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        System.out.println("1111"+s);
//        int id[] = JSON.parseObject(s, int[].class);
//
//        reader reader1 = readerService.selectReader(id[0]);
//        System.out.println(reader1);
//        String jsonString = JSON.toJSONString(reader1);
//        //3. 写数据
//        response.setContentType("text/json;charset=utf-8");
//        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
//        response.getWriter().write(jsonString);
//        System.out.println(reader1);
    }


}
