package com.example.demo.net.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @param
 * @ClassName Receive
 * @Author zengyi
 * @Description
 * @Date 2021/4/25 8:46
 **/
public class Receive implements Runnable{
    DatagramSocket server;
    public Receive(int port)  {
        try {
            server = new DatagramSocket(port);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            while (true){
                byte[] bytes = new byte[1024 * 60];
                DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);
                server.receive(packet);
                byte[] data = packet.getData();
                int length = packet.getLength();
                String s = new String(data, 0, length);
                System.out.println("对方说："+s);
                if(s.equals("bye")){
                    break;
                }
            }
            server.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
