package com.example.demo.thread;


import java.util.ArrayList;
import java.util.List;

public class MyThread  {
    public static void main(String[] args) throws Exception {
        List<String> list=new ArrayList<>();
        for (int i = 0; i <1000 ; i++) {
            new Thread(()->{
                synchronized (list){
                    System.out.println(Thread.currentThread().getName());
                }
            }).start();
        }
        Thread.sleep(10000);
        System.out.println(list.size());
    }



}
