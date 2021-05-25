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
public class LoginMulClient {
    public static void main(String[] args) throws Exception {
        System.out.println("-------Client---------");

        Socket socket = new Socket("localhost", 8888);

        new Send(socket).send();

        new Receive(socket).receive();

        socket.close();
    }

    //发送
    static class Send {
        private Socket client;
        private DataOutputStream dos;
        BufferedReader br;
        String msg;

        public Send(Socket client) throws Exception {
            br = new BufferedReader(new InputStreamReader(System.in));
            this.client = client;
            this.msg = init();
            try {
                dos = new DataOutputStream(client.getOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private String init() throws Exception {
            System.out.print("请输入用户名：");
            String uname = br.readLine();
            System.out.print("请输入密码：");
            String upwd = br.readLine();
            return "uname=" + uname + "&" + "upwd=" + upwd;
        }

        public void send() throws Exception {
            dos.writeUTF(msg);
            dos.flush();
        }
    }

    //接收
    static class Receive {
        private Socket client;
        private DataInputStream dis;

        public Receive(Socket client) throws IOException {
            this.client = client;
            dis = new DataInputStream(client.getInputStream());
        }

        public void receive() throws IOException {
            String readUTF = dis.readUTF();
            System.out.println(readUTF);
        }
    }

}
