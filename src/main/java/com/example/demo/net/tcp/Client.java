package com.example.demo.net.tcp;

import java.io.*;
import java.net.Socket;

/**
 * @param
 * @ClassName Client
 * @Author zengyi
 * @Description
 * @Date 2021/4/25 9:36
 **/
public class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("-------Client---------");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入用户名：");
        String uname = br.readLine();
        System.out.print("请输入密码：");
        String upwd = br.readLine();

        Socket socket = new Socket("localhost", 8888);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF("uname="+uname+"&"+"upwd="+upwd);

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String s = dis.readUTF();
        System.out.println("Server返回信息："+s);


    }
}
