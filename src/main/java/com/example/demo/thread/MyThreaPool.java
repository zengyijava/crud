package com.example.demo.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreaPool {

    public static void main(String[] args) {
        //创建线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        //执行
        service.execute(new testThread());
        service.execute(new testThread());
        service.execute(new testThread());
        //关闭
        service.shutdown();
    }



}

class testThread implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
