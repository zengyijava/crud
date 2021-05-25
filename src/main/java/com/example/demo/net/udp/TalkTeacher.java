package com.example.demo.net.udp;

/**
 * @param
 * @ClassName TalkTeacher
 * @Author zengyi
 * @Description
 * @Date 2021/4/25 9:10
 **/
public class TalkTeacher {
    public static void main(String[] args) {
        //接收方
        new Thread(new Receive(9999)).start();
        // 发送方
        new Thread(new Send(6666,"localhost",7777)).start();
    }
}
