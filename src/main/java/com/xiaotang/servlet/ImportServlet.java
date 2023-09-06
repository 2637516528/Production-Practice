package com.xiaotang.servlet;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.xiaotang.enums.BookStatusEnum;
import com.xiaotang.pojo.Book;
import com.xiaotang.service.BookService;
import com.xiaotang.service.impl.BookServiceImpl;
import com.xiaotang.util.ImportExcelUtil;
import org.apache.commons.collections4.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1024 * 1024 * 10 10M
 */
@MultipartConfig(maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 10)
@WebServlet("/import/*")
public class ImportServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();


    public void download(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取要下载的文件的绝对路径
        //在resources目录放入QQ.png，注意项目导出后resource中的文件被打包到/WEB-INF/classes下
        String realPath = this.getServletContext().getRealPath("/WEB-INF/图书导入模板.xls");
        //或者直接写绝对路径
        // String realPath="";
        //2.获取要下载的文件名
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
        //下载中文文件时，需要注意的地方就是中文文件名要使用
        // URLEncoder.encode方法进行编码(URLEncoder.encode(fileName, "字符编码"))，否则会出现文件名乱码。
        resp.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
        //4.获取要下载的文件输入流
        FileInputStream inputStream = new FileInputStream(new File(realPath));
        //5.准备数据缓冲区
        int len=0;
        byte[] buffer=new byte[1024];
        //6.通过response对象获取OutputStream流
        ServletOutputStream outputStream = resp.getOutputStream();
        try {
            //7.将FileInputStream流写入到buffer缓冲区
            while ((len=inputStream.read(buffer))!=-1){
                //8.使用OutputStream将缓冲区的数据输出到客户端浏览器
                outputStream.write(buffer,0,len);
            }
            outputStream.flush();
        } finally {
            //关闭流
            outputStream.close();
            inputStream.close();
        }
    }

    public void importBooks(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            Part part = req.getPart("file");
            //工具类
            ImportExcelUtil readExcelUtil = new ImportExcelUtil();
            List<List<String>> read = readExcelUtil.read(part.getInputStream(), true);

            if (CollectionUtils.isNotEmpty(read)){

                List<Book> importList = read.stream().map(e -> {
                    Book book = new Book();
                    book.setBname(e.get(0));
                    book.setAuthor(e.get(1));
                    book.setPress(e.get(2));
                    book.setType(e.get(3));
                    book.setStatus(BookStatusEnum.ON_SHELF.getCode());
                    book.setIntroduction(e.get(4));
                    return book;
                }).collect(Collectors.toList());

                if (CollectionUtils.isEmpty(importList)){
                    resp.setContentType("text/json;charset=utf-8");
                    //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
                    resp.getWriter().write("不能导入空文件");
                }

                //调用业务层
                bookService.importBooks(importList);
                resp.setContentType("text/json;charset=utf-8");
                //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
                resp.getWriter().write("success");
            }else{
                resp.setContentType("text/json;charset=utf-8");
                //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
                resp.getWriter().write("不能导入空文件");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setContentType("text/json;charset=utf-8");
            //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
            resp.getWriter().write("导入失败，更新数据库时报错！报错信息：" + e.toString());
        }
    }

}
