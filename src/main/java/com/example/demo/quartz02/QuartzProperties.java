package com.example.demo.quartz02;


import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

public class QuartzProperties {
    public static void main(String[] args) {
        //创建工厂实例
        StdSchedulerFactory stdSchedulerFactory=new StdSchedulerFactory();
        //创建配置工厂的属性的对象
        Properties properties=new Properties();
        properties.put(StdSchedulerFactory.PROP_THREAD_POOL_CLASS,"org.quartz.simpl.SimpleThreadPool");
        properties.put("org.quartz.threadPool.threadCount","5");   //指定线程数，至少为1（无默认值）(一般设置为1-100直接的整数合适)
        try {
            //加载上面定义的属性
            stdSchedulerFactory.initialize(properties);
            Scheduler scheduler=stdSchedulerFactory.getScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }
}
