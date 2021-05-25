package com.example.demo.quartz01;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class Schedule {

    //@Scheduled(cron = "0 15 10 ? * 6L")
    @Scheduled(cron = "0/5 * * * * ?")
    public static void timer(){
        System.out.println("当前时间： "+LocalDateTime.now());
    }

    public static void main(String[] args) {
        timer();
    }
}
