package com.example.demo.Collection;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @param
 * @ClassName MyArrayList
 * @Author zengyi
 * @Description
 * @Date 2021/4/23 15:32
 **/
public class MyArrayList {
    public static void main(String[] args) {
        Info info1 = new Info("张三", 23);
        Info info2 = new Info("李四", 25);
        Map<Object, Info> map = new HashMap<>();
        map.put(1,info1);
        map.put(2,info2);
        System.out.println(map);
        System.out.println(map.get(2).getName());

    }

    static class Info{
        private String name;
        private int age;

        Info(String name,int age){
            this.name=name;
            this.age=age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
