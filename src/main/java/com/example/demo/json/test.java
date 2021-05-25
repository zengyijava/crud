package com.example.demo.json;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class test {

    public static void main(String[] args) {
       //数组转字符串
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println("list:  "+list);
        String[] strings = list.toArray(new String[list.size()]);
        System.out.println("strings:  "+strings);
        StringBuffer sb=new StringBuffer();
        for (String string : list) {
            sb.append(string);
        }
        System.out.println("数组转字符串:  "+sb.toString());    //   abc
        System.out.println("数组转字符串:  "+ StringUtils.join(strings,","));  //  a,b,c
        System.out.println("数组转字符串:  "+ArrayUtils.toString(strings,","));
        StringBuffer sb1=new StringBuffer();
        String arr[]={"a","b","c"};    //   {"a","b","c"} = [a, b, c]
        for (String s : arr) {
            sb1.append(s);
        }
        System.out.println("sb1:  "+sb1.toString());


        //字符串转数组
        System.out.println("-------------------------------------------------------------------");
        String str="a,b,c";
        String[] split = str.split(",");
        System.out.println(split);
        System.out.println("字符串转数组:   "+Arrays.asList(split));  //[a, b, c]


    }

}
