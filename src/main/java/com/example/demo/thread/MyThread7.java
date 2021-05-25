package com.example.demo.thread;

/**
 * @param
 * @ClassName MyThread7
 * @Author zengyi
 * @Description
 * @Date 2021/5/18 15:10
 **/
public class MyThread7 {
    public static void main(String[] args) throws Exception {
        System.out.println(Thread.currentThread().isAlive());
        MyRun run = new MyRun("张三");
        Thread thread = new Thread(run);
        thread.start();
        Thread.sleep(1000);
        System.out.println(thread.isAlive());
    }
}

class MyRun implements Runnable {
    private String name;
    public MyRun(String name){
        this.name=name;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" --> "+ name);
    }
}
