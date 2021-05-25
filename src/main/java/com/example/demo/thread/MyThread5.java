package com.example.demo.thread;


import lombok.SneakyThrows;

/**
 * 模拟龟兔赛跑
 */
public class MyThread5 implements Runnable {
    //获胜者
    private static String winner;

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 1; i <=8 ; i++) {
            if (Thread.currentThread().getName().equals("兔子") || i%2==0){
                Thread.sleep(1);
            }
            boolean flag = gameOver(i);
            if (flag){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"  --> 跑了第  "+i+"  步");
        }
    }

    public static void main(String[] args) {
        MyThread5 myThread5 = new MyThread5();
        new Thread(myThread5,"兔子").start();
        new Thread(myThread5,"乌龟").start();
    }

    public boolean gameOver(int step){
        if(winner!=null){
            return true;
        }else if(step>=8){
            winner=Thread.currentThread().getName();
            System.out.println("winner is: "+winner);
            return true;
        }else {
            return false;
        }
    }
}
