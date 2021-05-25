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
public class LoginMulServer {
    public static void main(String[] args) throws Exception {
        System.out.println("-------Server---------");
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true){
            Socket accept = serverSocket.accept();
            System.out.println("客户端建立了连接...");
            new Thread(new Channel(accept)).start();
        }
    }

    static class Channel implements Runnable{
        private Socket accept;
        private DataInputStream dis;
        private DataOutputStream dos;
        public Channel(Socket accept) {
            this.accept=accept;
            try {
                dis = new DataInputStream(accept.getInputStream());
                dos = new DataOutputStream(accept.getOutputStream());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //接收数据
        private String receive() throws IOException {
            String readUTF = dis.readUTF();
            return readUTF;
        }
        //发送数据
        private void send(String msg) throws IOException {
            dos.writeUTF(msg);
            dos.flush();
        }
        //释放资源
        private void release() throws IOException {
            if(null!=accept){
                accept.close();
            }
            if(null!=dis){
                dis.close();
            }
            if(null!=dos){
                dos.close();
            }
        }

        @Override
        public void run() {
            try {
                String uname=null;
                String upwd=null;
                String[] split = receive().split("&");
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
                if(uname.equals("sam") && upwd.equals("123") ){
                    send("登录成功！");
                }else {
                    send("用户名或者密码错误!");
                }
                //释放资源
                release();
            }catch (Exception e){
                e.printStackTrace();
            }

        }




    }

}
