package com.example.demo.thread;

/**
 * 模拟火车票抢票
 */
public class MyThread4 implements Runnable{
    //票数
    private int ticketNum=10;

    @Override
    public void run() {
        while (true){
            if (ticketNum<=0){
                break;
            }
            try{
                //模拟延时
                //Thread.sleep(300);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" --> 抢到了第"+ticketNum--+"票");
        }
    }

    public static void main(String[] args) {
        MyThread4 myThread4 = new MyThread4();
        new Thread(myThread4,"张三").start();
        new Thread(myThread4,"李四").start();
        new Thread(myThread4,"王五").start();



    }

}
