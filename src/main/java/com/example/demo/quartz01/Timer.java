package com.example.demo.quartz01;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class Timer {
    public static void main(String[] args) {
        java.util.Timer timer=new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(new SimpleDateFormat("yyyy-HH-dd HH:mm:ss").format(new Date()));
            }
        },1000,5000);
    }

}
