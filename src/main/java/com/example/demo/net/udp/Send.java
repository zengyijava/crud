package com.example.demo.net.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @param
 * @ClassName Send
 * @Author zengyi
 * @Description
 * @Date 2021/4/25 8:46
 **/
public class Send implements Runnable{
    String host;
    int toPort;
    DatagramSocket client;
    BufferedReader br;
    public Send(int port,String host,int toPort){
        this.host=host;
        this.toPort=toPort;
        try {
            client = new DatagramSocket(port);
            br = new BufferedReader(new InputStreamReader(System.in));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true){
                String line = br.readLine();
                byte[] bytes = line.getBytes("UTF-8");
                DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length,
                        new InetSocketAddress(host,toPort));
                client.send(packet);
                if(line.equals("bye")){
                    break;
                }
            }
            client.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
