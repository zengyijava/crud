package com.example.demo.net.chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @param
 * @ClassName ChatClient
 * @Author zengyi
 * @Description
 * @Date 2021/4/26 8:57
 **/
public class ChatClient {
    public static void main(String[] args) {
        new ChatClient().start();
    }


    private Socket server=null;
    private void start(){
        try {
            server = new Socket("localhost",8888);
            System.out.println("连接服务器成功，身份证："+server.getLocalAddress());
            // 启动接受消息的线程
            new Thread(new ReceiveMessageListener()).start();
            // 启动发送消息的线程
            new Thread(new SendMessageListener()).start();
        }catch (Exception e){
            System.out.println("服务器: "+server.getRemoteSocketAddress()+" 挂掉了");
        }
    }

    class SendMessageListener implements Runnable{
        @Override
        public void run() {
            try {
                Scanner scanner = new Scanner(System.in);
                while (true){
                    sendMsg(scanner.next());
                }
            }catch (Exception e){
                System.out.println("服务器: "+server.getRemoteSocketAddress()+" 挂掉了");
            }
        }
    }

    class ReceiveMessageListener implements Runnable{
        @Override
        public void run() {
            try {
                while (true){
                    System.out.println(receiveMsg());
                }
            }catch (Exception e){
                System.out.println("服务器: "+server.getRemoteSocketAddress()+" 挂掉了");
            }
        }
    }

    /**
     * 发送消息
     * @param msg
     */
    private void sendMsg(String msg) throws IOException {
        PrintWriter pw = new PrintWriter(server.getOutputStream());
        pw.println(msg);
        pw.flush();
    }

    private String receiveMsg() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(server.getInputStream()));
        String line = br.readLine();
        return line;
    }


}
