package com.xiaotang.servlet;

import com.alibaba.fastjson.JSON;
import com.xiaotang.pojo.Book;
import com.xiaotang.pojo.Borrow;
import com.xiaotang.pojo.reader;
import com.xiaotang.service.BookService;
import com.xiaotang.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet("/book/*")
public class BookServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service进行查询
        List<Book> Books = bookService.selectAll();
        //2. 转为JSON
        String jsonString = JSON.toJSONString(Books);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.getWriter().write(jsonString);
        System.out.println(Books);
    }
    public void message(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("admin"));
        Object admin = session.getAttribute("admin");
        System.out.println(admin);
        com.xiaotang.pojo.admin admin1=(com.xiaotang.pojo.admin)admin;
        System.out.println(admin1);

        String jsonString = JSON.toJSONString(admin1);
        response.getWriter().write(jsonString);
    }

    public void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        Book book = JSON.parseObject(s, Book.class);
        System.out.println(book);
        bookService.addBook(book);
        response.getWriter().write("success");

    }
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        System.out.println(s);
        int[] id = JSON.parseObject(s, int[].class);
        System.out.println(id[0]);
        bookService.deleteById(id[0]);
        response.getWriter().write("success");

    }
    public void selectByIdBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        BufferedReader reader = request.getReader();
//        String s = reader.readLine(); //json字符串
//        book book = JSON.parseObject(s, com.xiaotang.pojo.book.class);
//        System.out.println(book);
//        book books = bookService.selectByIdBook(book.getId());
//        System.out.println(books);
//        String jsonString = JSON.toJSONString(books);
//        //3. 写数据
//        response.setContentType("text/json;charset=utf-8");
//        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
//        response.getWriter().write(jsonString);
//        System.out.println(books);

        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        System.out.println(s);
        int id = JSON.parseObject(s, int.class);
        Book books = bookService.selectByIdBook(id);
        //2. 转为JSON
        String jsonString = JSON.toJSONString(books);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.getWriter().write(jsonString);
        System.out.println(books);

    }
    public void selectByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        Book book = JSON.parseObject(s, Book.class);
        System.out.println(book);
        List<Book> Books = bookService.selectByCondition(book);
        System.out.println(Books);
        String jsonString = JSON.toJSONString(Books);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.getWriter().write(jsonString);
        System.out.println(Books);

    }

    public void borrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        System.out.println(s);
        int[] id = JSON.parseObject(s, int[].class);
        System.out.println(id[0]);
//        获取当前使用者的sno
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("reader"));
        Object readerMessage = session.getAttribute("reader");
        com.xiaotang.pojo.reader readermessage = (com.xiaotang.pojo.reader) readerMessage;
        System.out.println(readermessage);

        bookService.borrow(id[0],readermessage.getSno());
        response.getWriter().write("success");

    }

    public void selectBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service进行查询
        List<Borrow> Borrows = bookService.selectBorrow();
        //2. 转为JSON
        String jsonString = JSON.toJSONString(Borrows);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.getWriter().write(jsonString);
        System.out.println(Borrows);
    }

    public void selectAuditBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service进行查询
        List<Borrow> Borrows = bookService.selectAuditBorrow();
        //2. 转为JSON
        String jsonString = JSON.toJSONString(Borrows);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.getWriter().write(jsonString);
        System.out.println(Borrows);
    }

    public void selectBySno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object readerMessage = session.getAttribute("reader");
        reader readermessage = (reader) readerMessage;


        //1.调用service进行查询
        List<Borrow> borrow = bookService.selectBySno(readermessage.getSno());
        //2. 转为JSON
        String jsonString = JSON.toJSONString(borrow);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.getWriter().write(jsonString);
        System.out.println(borrow);
    }

    public void returnBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        System.out.println(s);
        int[] id = JSON.parseObject(s, int[].class);
        System.out.println(id[0]);

        bookService.returnBook(id[0]);
        response.getWriter().write("success");

    }

    public void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine(); //json字符串
        System.out.println(s);
        Book book = JSON.parseObject(s, Book.class);
        System.out.println(book);
        bookService.updateBook(book);
        response.getWriter().write("success");

    }
}
