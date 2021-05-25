package com.example.demo.json;


import java.util.ArrayList;
import java.util.List;

public class test2 {
    public static void main(String[] args) {
        List<List<String>> lists=new ArrayList<>();
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        lists.add(list);
        System.out.println(lists);

        List<Address> addressList=new ArrayList<>();
        Address address=new Address();
        address.setIp("192.169.1.143");
        address.setPort("8080");
        address.setNo("emp");
        addressList.add(address);
        for (Address address1 : addressList) {
            System.out.println(address1.getPort());
        }

    }

}
