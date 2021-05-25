package com.example.demo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @param
 * @ClassName MyScheduled
 * @Author zengyi
 * @Description
 * @Date 2021/3/24 14:04
 **/
@Component
public class MyScheduled {

    @Autowired
    private SendMessage sendMessage;

    /*定时执行任务方法 每天5点20执行该任务*/
    //每个5秒
    @Scheduled(cron = "0/3 * * * * *")
   //@Scheduled(cron ="0 52 09 * * *")
    public void dsrw(){
        //String message = SendMessage.getOneS();
        //sendMessage.sendMessage("来自清茶淡粥的消息！❤",message);
       sendMessage.sendMessage("你好","工作");
    }

}
