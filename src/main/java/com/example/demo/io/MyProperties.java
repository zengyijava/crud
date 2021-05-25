package com.example.demo.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @param
 * @ClassName MyProperties
 * @Author zengyi
 * @Description
 * @Date 2021/3/30 11:31
 **/
public class MyProperties {
    public static void main(String[] args) throws FileNotFoundException {
        Properties properties = new Properties();
        properties.setProperty("name","张三");
        properties.setProperty("age","23");
        System.out.println(properties);
        Set<String> set = properties.stringPropertyNames();
        for (String s : set) {
            System.out.println(s+"==========="+properties.getProperty(s));
        }

        PrintWriter pw=new PrintWriter("C:/Users/dell/Desktop/100.txt");
        properties.list(pw);
        pw.close();


        HashMap<String, String> map = new HashMap<>();
        map.put("name","中国");
        map.put("addr","美国");
        System.out.println(map);
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey()+"*******"+next.getValue());
        }

        Set<String> set1 = map.keySet();
        for (String s : set1) {
            System.out.println(s);
        }

    }


}
