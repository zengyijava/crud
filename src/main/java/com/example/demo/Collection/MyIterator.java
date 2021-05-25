package com.example.demo.Collection;

import java.util.*;

/**
 * @param
 * @ClassName MyIterator
 * @Author zengyi
 * @Description
 * @Date 2021/4/26 14:24
 **/
public class MyIterator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("2");
        list.add("5");
        list.add("10");
        list.add("7");
        list.add("9");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }
        //二分法查找，前提是集合已经排好序
        Collections.sort(list);
        System.out.println("二分法查找："+Collections.binarySearch(list, "7"));

        Map<Object, Object> map = new HashMap<>();
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }

        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        Iterator<Map.Entry<Object, Object>> it = entries.iterator();
        while (it.hasNext()){
            Map.Entry<Object, Object> next = it.next();
            System.out.println(next.getKey()+" : "+next.getValue());
        }

        Set<Object> keySet = map.keySet();
        for (Object o : keySet) {
            System.out.println(o+" : "+map.get(o));
        }



    }
}
