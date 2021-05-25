package com.example.demo.Random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PwdRandom {
    public static void main(String[] args) {
        int pwdCount=8;
        System.out.println(getPwd(pwdCount));
    }

    /**
     *随机返回pwdCount位字符串
     * @return
     */
    public  static String getPwd(int pwdCount){
        String numStr = "";
        String lowStr = "";
        String upStr = "";
        String specialStr = "";
        //数字
        String num = "23456789234567892";
        //小写字母
        String low = "abcdefghijkmnpqrstuvwxyz";
        //大写字母
        String up = "ABCDEFGHIJKLMNPQRSTUVWXYZ";
        //特殊字符
        String special = "@#*";
        if(pwdCount>5&&pwdCount<21){
            numStr = randomNum(num,pwdCount-3);
            lowStr = randomNum(low,1);
            upStr = randomNum(up,1);
            specialStr = randomNum(special,1);
        }else {
            numStr = randomNum(num,3);
            lowStr = randomNum(low,1);
            upStr = randomNum(up,1);
            specialStr = randomNum(special,1);
        }
        String result = numStr+lowStr+upStr+specialStr;
        //随机返回以数字、小写字母、大写字母、特殊字符组成的字符串
        return randomNum(result,result.length());
        /*if(StringUtils.isNotBlank(pwdCount)){
            //随机取3个数字
            String num = "23456789";
            //随机取1个小写字母
            String low = "abcdefghijkmnpqrstuvwxyz";
            //随机取1个大写字母
            String up = "ABCDEFGHIJKLMNPQRSTUVWXYZ";
            //随机取1个特殊字符
            String special = "@#*&%!";
            List<String> combtypeList = Arrays.asList(pedType.split(","));
            if(combtypeList.contains("1")&&combtypeList.contains("2")&&combtypeList.contains("3")){
                numStr = randomNum(num,3);
                lowStr = randomNum(low,1);
                upStr = randomNum(up,1);
                specialStr = randomNum(special,1);
            }else if(combtypeList.contains("1")&&combtypeList.contains("2")){
                numStr = randomNum(num,4);
                lowStr = randomNum(low,1);
                upStr = randomNum(up,1);
            }else if(combtypeList.contains("1")&&combtypeList.contains("3")){
                numStr = randomNum(num,4);
                specialStr = randomNum(special,2);
            }else if(combtypeList.contains("2")&&combtypeList.contains("3")){
                lowStr = randomNum(low,2);
                upStr = randomNum(up,2);
                specialStr = randomNum(special,2);
            }else if(combtypeList.contains("1")){
                numStr = randomNum(num,6);
            }else if(combtypeList.contains("2")){
                lowStr = randomNum(low,3);
                upStr = randomNum(upStr,3);
            }else if(combtypeList.contains("3")){
                specialStr = randomNum(special,6);
            }else {
                numStr = randomNum(num,3);
                lowStr = randomNum(low,1);
                upStr = randomNum(up,1);
                specialStr = randomNum(special,1);
            }
        }
        String result = numStr+lowStr+upStr+specialStr;*/
    }

    public static String randomNum(String str,int len){
        String result = "";
        try {
            StringBuilder stringBuilder = new StringBuilder();
            char[] c = str.toCharArray();
            List<Character> lst = new ArrayList<>();
            for (int i = 0; i < c.length; i++) {
                lst.add(c[i]);
            }
            //随机打乱原来的顺序
            Collections.shuffle(lst);
            for(Character s:lst){
                stringBuilder.append(s);
            }
            result = stringBuilder.toString().substring(0,len);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


}
