package com.example.demo.Collection;

import java.util.TreeSet;

/**
 * @param
 * @ClassName MyTreeSet
 * @Author zengyi
 * @Description
 * @Date 2021/4/26 13:56
 **/
public class MyTreeSet {
    public static void main(String[] args) {
        TreeSet<Object> treeSet = new TreeSet<>();
        treeSet.add("5");
        treeSet.add("5");
        treeSet.add("2");
        treeSet.add("8");
        System.out.println(treeSet);

    }
}
