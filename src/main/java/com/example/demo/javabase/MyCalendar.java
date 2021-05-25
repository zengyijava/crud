package com.example.demo.javabase;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @param
 * @ClassName MyCalendar
 * @Author zengyi
 * @Description
 * @Date 2021/4/22 17:05
 **/
public class MyCalendar {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date());
//        Calendar calendar = new GregorianCalendar();
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH)+1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(LocalDate.now());

    }
}
