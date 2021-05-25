package com.example.demo.webserver;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @param
 * @ClassName Request
 * @Author zengyi
 * @Description
 * @Date 2021/5/6 18:19
 **/
public class Request {
    //协议信息
    private String requestInfo;
    //请求方式
    private String method;
    //请求url
    private String url;
    //请求参数
    private String queryStr;
    public Request(Socket client) throws IOException {
        this(client.getInputStream());
    }

    public Request(InputStream in){
        try {
            byte[] bytes = new byte[60 * 1024];
            int len = in.read(bytes);
            String s = new String(bytes, 0, len);
            System.out.println("获取请求协议： " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //分解字符串
        parseRequestInfo();
    }

    private void parseRequestInfo(){

    }

}
