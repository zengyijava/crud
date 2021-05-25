package com.example.demo.net.udp;

/**
 * @param
 * @ClassName TalkStudent
 * @Author zengyi
 * @Description
 * @Date 2021/4/25 9:10
 **/
public class TalkStudent {
    public static void main(String[] args) {
        // 发送方
        new Thread(new Send(8888,"localhost",9999)).start();
        //接收方
        new Thread(new Receive(7777)).start();

    }
}
