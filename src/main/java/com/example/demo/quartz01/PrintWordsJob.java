package com.example.demo.quartz01;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @param
 * @ClassName PrintWordsJob
 * @Author zengyi
 * @Description
 * @Date 2021/3/9 19:18
 **/
@PersistJobDataAfterExecution    //多次调用job的时候，会对job进行持久化,及保存一个暑假的信息
public class PrintWordsJob implements Job {

    private Integer count;
    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("开始时间: "+format+", JOB====="+new Random().nextInt(100));

        //从JobDetail对象中获取jobDataMap的数据
        JobDataMap jobDataMap=context.getJobDetail().getJobDataMap();
        String jobMsg = jobDataMap.getString("JobMsg");
        System.out.println("任务数据的参数值："+jobMsg);

        //Trigger对象中获取jobDataMap的值
        JobDataMap jobDataMap1 = context.getTrigger().getJobDataMap();
        String triggerMsg = jobDataMap1.getString("TriggerMsg");
        System.out.println("触发器数据的参数值："+triggerMsg);

        ++count;
        System.out.println("count: "+count);
        context.getJobDetail().getJobDataMap().put("count",count);

    }
}
