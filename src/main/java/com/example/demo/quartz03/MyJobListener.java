package com.example.demo.quartz03;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @param
 * @ClassName JobListener
 * @Author zengyi
 * @Description
 * @Date 2021/3/10 15:29
 **/
public class MyJobListener implements org.quartz.JobListener {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println("jobExecutionContext: "+ jobExecutionContext);
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println("jobExecutionContext: "+ jobExecutionContext);
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println("jobExecutionContext: "+jobExecutionContext);
    }
}
