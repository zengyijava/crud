package com.example.demo.stream;

import com.example.demo.json.Address;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @param
 * @ClassName ListStream
 * @Author zengyi
 * @Description
 * @Date 2021/4/30 15:32
 **/
public class ListStream {
    public static void main(String[] args) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        List<Address> list=new ArrayList<>();
        Address address1 = new Address();
        address1.setIp("192.169.1.143");
        address1.setNo("emp");
        address1.setPort("8080");
        Address address2 = new Address();
        address2.setIp("192.169.1.144");
        address2.setNo("emp");
        address2.setPort("9090");
        list.add(address1);
        list.add(address2);

        if(CollectionUtils.isNotEmpty(list)){
            list.forEach(v->{
                hashMap.put(v.getIp(),v.getPort());
            });
        }
        System.out.println(hashMap);

        List<String> ipList = list.stream().map(Address::getIp).collect(Collectors.toList());
        System.out.println(ipList);
        emp emp1 = new emp(1, "张三");
        System.out.println(emp1);
        Iterator<Address> iterator = list.iterator();
        while (iterator.hasNext()){
            Address next = iterator.next();
            if(next.getIp().equals("192.169.1.143")){
                continue;
            }
            System.out.println(next.getIp());
        }
        Set<Map.Entry<Object, Object>> entries = hashMap.entrySet();
        System.out.println();

    }
}


class emp{
    private Integer id;
    private String name;

    public emp(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
