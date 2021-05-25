package com.example.demo.javabase;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @param
 * @ClassName MyArr
 * @Author zengyi
 * @Description
 * @Date 2021/4/22 9:27
 **/
public class MyArr {
    public static void main(String[] args) throws ParseException {
        String []arr={"c","a","b"};
        System.out.println(java.util.Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        Object []a={1001,"张三",23,"java开发"};
        Object []b={1001,"张三",23,"java开发"};
        Object []c={1001,"张三",23,"java开发"};
        Object [][]datas=new Object[3][];
        datas[0]=a;
        datas[1]=a;
        datas[2]=a;
        for (Object[] data : datas) {
            System.out.println(Arrays.toString(data));
        }
        /**
         * 冒泡排序
         */
        int sort[]={3,5,2,7,1,4,2,11,9};
        int temp;
        for (int i = 0; i < sort.length-1; i++) {
            for (int j = 0; j < sort.length-1; j++) {
                if(sort[j]>sort[j+1]){
                    temp=sort[j];
                    sort[j]=sort[j+1];
                    sort[j+1]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(sort));

        StringBuilder sb=new StringBuilder("baidu: ");
        sb.append("china");
        System.out.println(sb);
        sb.delete(6,12);
        System.out.println(sb);
        sb.insert(6,"beijing");
        System.out.println(sb);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dayFileName="20200322";
        String oldStr = DateFormatUtils.format(DateUtils.parseDate(dayFileName, "yyyyMMdd"), "yyyy-MM-dd");
        String nowStr = sdf.format(new Date());
        Date oldDate = sdf.parse(oldStr);
        Date nowDate = sdf.parse(nowStr);
        long diff=nowDate.getTime()- oldDate.getTime();
        System.out.println(diff);
    }


}
