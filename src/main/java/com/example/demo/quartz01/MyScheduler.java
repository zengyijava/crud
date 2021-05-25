package com.example.demo.quartz01;

import com.example.demo.quartz03.MyJobListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;


import java.util.concurrent.TimeUnit;

/**
 * 程序每隔1s会打印出内容，且在一分钟后结束
 */
public class MyScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //1、创建调度器Scheduler
        SchedulerFactory schedulerFactory=new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class)
                .withIdentity("job1", "group1")    //参数1：任务的名称（唯一的实例）；参数2：任务组的名称
                .usingJobData("JobMsg","JobDetail值")  //map<k,v>存储
                .usingJobData("count",1)
                .build();

        System.out.println("job1=="+jobDetail.getKey().getName()+", group=="+jobDetail.getKey().getGroup()+", PrintWordsJob=="+jobDetail.getClass());
        // 3、构建Trigger实例,每隔1s执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triggerGroup1")
                .startNow()//立即生效
                .withSchedule(  SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(1)//每隔1s执行一次
                .repeatForever()  )  //一直执行
                .usingJobData("TriggerMsg","Trigger值")
                .build();

        //4、执行
        scheduler.scheduleJob(jobDetail, trigger);  //调度器scheduler（任务Job，触发器Trigger）
        System.out.println("--------scheduler start ! ------------");
        scheduler.start();
//        scheduler.resumeAll();
//        scheduler.pauseAll();
        //睡眠
        TimeUnit.MINUTES.sleep(1);  //一分钟之后停掉
        scheduler.shutdown();
        System.out.println("--------scheduler shutdown ! ------------");

        //创建并注册一个全局的Job Listener
        scheduler.getListenerManager().addJobListener(new MyJobListener(), EverythingMatcher.allJobs());
        //创建并注册一个局部的Job Listener，表示指定的任务Job
        scheduler.getListenerManager().addJobListener(new MyJobListener());

    }
}
