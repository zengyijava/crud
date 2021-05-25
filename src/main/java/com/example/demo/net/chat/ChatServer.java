package com.example.demo.net.chat;

import org.springframework.data.redis.connection.MessageListener;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @param
 * @ClassName ChatServer
 * @Author zengyi
 * @Description
 * @Date 2021/4/26 8:57
 **/
public class ChatServer {
    public static void main(String[] args) {
        new ChatServer().start();
    }

    private static List<Socket> clients = new ArrayList<>();

    public void start() {
        try {
            System.out.println("---------------服务端开启--------------------");
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket accept = serverSocket.accept();
                System.out.println("用户【" + accept.getRemoteSocketAddress() + "】" + " 建立了连接，当前连接用户数为： " + clients.size());
                clients.add(accept);
                new Thread(new MessageListener(accept)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MessageListener implements Runnable {
        private Socket client;
        PrintWriter pw;
        BufferedReader br;
        public MessageListener(Socket socket) {
            this.client = socket;
        }

        @Override
        public void run() {
            try {
                // 每个用户连接上了，就发送一条系统消息（类似于广播）
                sendMsg(0, "[系统消息]：欢迎" + client.getRemoteSocketAddress() + "来到聊天室，当前共有" + clients.size() + "人在聊天");
                // 循环监听消息
                while (true) {
                    sendMsg(1, "[" + client.getRemoteSocketAddress() + "]：" + receiveMsg());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * type:1为用户消息，0为系统消息
         *
         * @param type
         * @param msg
         */
        public void sendMsg(int type, String msg) throws Exception {
            if (type == 1) {
                System.out.println("用户消息：" + msg);
            }
            for (Socket socket : clients) {
                if (type == 1 && socket == client) {
                    continue;
                }
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                pw.println(msg);
                pw.flush();
            }
        }

        public String receiveMsg() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            return br.readLine();
        }

    }


}
