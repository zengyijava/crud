package com.example.demo.javabase;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @param
 * @ClassName MyDate
 * @Author zengyi
 * @Description
 * @Date 2021/5/8 13:42
 **/
public class MyDate {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str="20210409";
        // 将yyyyMMdd 转化为 yyyy-MM-dd
        String s = DateFormatUtils.format(DateUtils.parseDate(str, "yyyyMMdd"), "yyyy-MM-dd");
        Date oldDate = sdf.parse(s);
        String format = sdf.format(new Date());
        Date nowDate = sdf.parse(format);
        long timeDiff=nowDate.getTime()-oldDate.getTime();
        long day = timeDiff / (24 * 60 * 60 * 1000);
        if(day>30){
            System.out.println("时间大于30天");
        }
    }
}
