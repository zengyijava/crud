package com.example.demo.Collection;

import java.util.*;

/**
 * @param
 * @ClassName MapAndList
 * @Author zengyi
 * @Description
 * @Date 2021/4/26 16:14
 **/
public class MapAndList {
    public static void main(String[] args) {
        Map<Object, Object> map1 = new HashMap<>();
        map1.put("编号",1001);
        map1.put("年龄",23);
        map1.put("姓名","张三");

        Map<Object, Object> map2 = new HashMap<>();
        map2.put("编号",1002);
        map2.put("年龄",25);
        map2.put("姓名","李四");

        List<Map<Object, Object>> mapList=new ArrayList<>();
        mapList.add(map1);
        mapList.add(map2);
        for (Map<Object, Object> objectMap : mapList) {
            Set<Map.Entry<Object, Object>> entries = objectMap.entrySet();
            for (Map.Entry<Object, Object> entry : entries) {
                System.out.print(entry.getKey()+":"+entry.getValue()+"  ");
            }
            System.out.println();
        }
    }

}
