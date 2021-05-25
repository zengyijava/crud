package com.example.demo.thread;

import org.apache.poi.hssf.record.DVALRecord;

/**
 * @param
 * @ClassName TicketRun
 * @Author zengyi
 * @Description
 * @Date 2021/5/18 17:29
 **/
public class TicketRun implements Runnable {
    int tickNum=10;
    Object object=new Object();
    @Override
    public void run() {
        synchronized (object){
            if(tickNum>0){
                System.out.println(Thread.currentThread().getName()+" --> "+tickNum);
                tickNum--;
            }
        }
    }
}
