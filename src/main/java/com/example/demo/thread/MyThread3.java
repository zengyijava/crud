package com.example.demo.thread;

/**
 * 实现Runnable接口
 */
public class MyThread3 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <3 ; i++) {
            System.out.println("run（）方法执行了********************");
        }
    }

    public static void main(String[] args) {
        MyThread3 myThread3 = new MyThread3();
        new Thread(myThread3).start();

        for (int i = 0; i <3 ; i++) {
            System.out.println("main（）启动了。。。。。。。。。。。。。");
        }
    }

}
