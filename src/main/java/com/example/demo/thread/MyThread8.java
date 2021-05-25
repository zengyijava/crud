package com.example.demo.thread;

/**
 * @param
 * @ClassName MyThread8
 * @Author zengyi
 * @Description
 * @Date 2021/5/18 16:19
 **/
public class MyThread8 {
    public static class SynchronizedThread {
         static int count = 0;
         static class Test{
            synchronized public void func(){
                count++;
            }

        }

        public static void main(String[] args) {
            Test t = new Test();
            Thread t1 = new Thread(){
                @Override
                public void run() {
                    System.out.println("线程1开始运行");
                    for (int i = 0; i < 5000; i++) {
                        t.func();
                    }
                }
            };

            Thread t2 = new Thread(){
                @Override
                public  void run() {
                    System.out.println("线程2开始运行");
                    for (int i = 0; i < 5000; i++) {
                        t.func();
                    }
                }
            };
            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(count);
        }
    }

}
