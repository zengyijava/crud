package com.example.demo.Random;

import java.util.Random;

/**
 * @param
 * @ClassName SixTest
 * @Author zengyi
 * @Description
 * @Date 2021/4/30 15:14
 **/
public class SixTest {
    /**
     *  随机生成6位不重复的整数
     * @param args
     */
    public static void main(String[] args) {
        int count=6;
        StringBuffer sb = new StringBuffer();
        String str="0123456789";
        for (int i = 0; i < count; i++) {
            Random random = new Random();
            //随机生成[0,10)的整数
            int num = random.nextInt(str.length());
            sb.append(str.charAt(num));
            str=str.replace((str.charAt(num)+""),"");
        }
        System.out.println(sb.toString());
    }
}
