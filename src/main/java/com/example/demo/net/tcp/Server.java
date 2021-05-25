package com.example.demo.net.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @param
 * @ClassName Server
 * @Author zengyi
 * @Description
 * @Date 2021/4/25 9:36
 **/
public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("-------Server---------");
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket accept = serverSocket.accept();

        System.out.println("客户端建立了连接...");
        DataInputStream dis = new DataInputStream(accept.getInputStream());
        String readUTF = dis.readUTF();
        String uname=null;
        String upwd=null;
        String[] split = readUTF.split("&");
        for (String s : split) {
            String[] info = s.split("=");
            if("uname".equals(info[0])){
                uname=info[1];
                System.out.println("用户名为："+info[1]);
            }else if("upwd".equals(info[0])){
                upwd=info[1];
                System.out.println("密码为："+info[1]);
            }
        }


        DataOutputStream dos = new DataOutputStream(accept.getOutputStream());
        if(uname.equals("sam") && upwd.equals("123") ){
            dos.writeUTF("登录成功！");
        }else {
            dos.writeUTF("用户名或者密码错误!");
        }

        accept.close();
        dis.close();
        dos.close();


    }
}
