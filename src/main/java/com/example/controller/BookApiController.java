package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.Book;
import com.example.repo.BookRepository;
import com.example.utils.FileUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/api/book",""})
public class BookApiController {

    //文件夹绝地路径
    @Value("${file.upload.ab_path}")
    private String ab_path;

    @Value("${file.upload.mdImageDir}")
    private String mdImageDir;


    @Value("${server.port}")
    private String port;

    @Value("${file.upload.bookImg")
    private String bookImg;

    @Resource
    private BookRepository repository;

    //文档上穿
    @RequestMapping("/postMd")
    public String saveMd(@RequestBody JSONObject param,HttpServletRequest request){
        String str = param.getString("content");
        String title = param.getString("name");
        Date date = new Date();
        System.out.println(date);
        String filepath = ab_path+title+".md";
        System.out.println(filepath);
        FileUtil.string2File(str,filepath);
        String mdAddress="http:"+request.getHeader("Origin").split(":")[1]+":"+port+"/note/"+title+".md";
        String result = "{\"status\":\"200\",\"data\":\""+mdAddress+"\"}";
        return result;
    }
    //上传图片
    @PostMapping("uploadFile")
    public String uploadFile(@RequestParam("image")MultipartFile file, HttpServletRequest request){
        String imgAbPath= ab_path+"image/";
        String imgUrlDir="http:"+request.getHeader("Origin").split(":")[1]+":"+port+mdImageDir;
        File f = FileUtil.upload(file,imgAbPath);
        String result = "{\"status\":\"200\",\"data\":\""+imgUrlDir+f.getName()+"\"}";
        return result;
    }
    //文章上传信息
    @PostMapping("/getBook")
    public Map<String, Object> getBook(@RequestParam("title")String title,
                                       @RequestParam("type")String type,
                                       @RequestParam("contextAddress")String contextAddress,
                                       @RequestParam("contextPictureAddress")String contextPictureAddress,
                                       @RequestParam("file")MultipartFile file,
                                       HttpServletRequest request){
//        保存文件返回文件保存地址
        System.out.println(bookImg);
        String ImgBook = ab_path+ "bookImg/";
        File f = FileUtil.upload(file,ImgBook);
        String bookFile ="http:"+request.getHeader("Origin").split(":")[1]+":"+port+"/note/bookImg/"+f.getName();
        Date date = new Date();
        Book book = new Book();
        book.setTitle(title);
        book.setType(type);
        book.setCreate_time(date);
        book.setContext(contextAddress);
        book.setPicture(bookFile);
        book.setContextPictureAddress(contextPictureAddress);
        repository.save(book);
        System.out.println(title+"   "+type+"   "+contextAddress+"  "+contextPictureAddress);
        Map<String,Object> map = new HashMap<>();
        map.put("status",200);
        map.put("data",bookFile);
        return map;
    }
    @GetMapping("/test")
    public String test(){
        return "test";
    }
    //返回书籍列表信息
    @RequestMapping("/getBookInfo")
    public Map<String, Object> getBookInfo(String index){
        List<Book> books= repository.getBooksByType(index);
        System.out.println(index);
        System.out.println("发送书籍列表信息");
        Map<String,Object> map = new HashMap<>();
        map.put("status",200);
        map.put("data",books);
        return map;
    }
    //返回图片信息

    @GetMapping(value = "/note/bookImg/{address}")
    @ResponseBody
    public void img(@PathVariable("address")String address, HttpServletRequest request,HttpServletResponse response) throws Exception{
        System.out.println(ab_path+"bookImg/"+address);
        File file = new File(ab_path+"bookImg/"+address);
        FileInputStream inputStream = new FileInputStream(file);
       response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(inputStream,response.getOutputStream());
    }
}
