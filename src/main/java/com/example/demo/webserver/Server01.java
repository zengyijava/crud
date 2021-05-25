package com.example.demo.webserver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 通过ServerSock 建立与浏览器之间的联系，获取请求协议
 **/
public class Server01 {
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        Server01 server01 = new Server01();
        server01.start();
    }

    public void start() {
        try {
            //  http://localhost:8888/login.html?name=%E5%BC%A0%E4%B8%89&pwd=123
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receive() {
        try {
            Socket client = serverSocket.accept();
            System.out.println("一个客户端建立了连接......");
            InputStream in = client.getInputStream();
            byte[] bytes = new byte[60 * 1024];
            int len = in.read(bytes);
            System.out.println("bytes.length:  " + bytes.length);
            System.out.println("len:  " + len);
            String s = new String(bytes, 0, len);
            System.out.println("获取请求协议： " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stop() {

    }


}
