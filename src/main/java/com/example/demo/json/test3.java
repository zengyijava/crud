package com.example.demo.json;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class test3 {
    public static void main(String[] args) {
        HashMap<String,Object> detail1=new HashMap<>();
        detail1.put("姓名","张三");
        detail1.put("年龄",23);

        HashMap<String,Object> detail2=new HashMap<>();
        List<Address> list=new ArrayList<>();
        Address address=new Address();
        address.setIp("192.169.1.143");
        address.setPort("8080");
        address.setNo("emp");
        list.add(address);
        Address address1=new Address();
        address1.setIp("192.169.1.144");
        address1.setPort("9090");
        address1.setNo("emp");
        list.add(address1);
        detail2.put("公司","梦网科技");
        detail2.put("职位","java开发");
        detail2.put("地址",list);

        HashMap<String,Object> detail=new HashMap<>();
        detail.put("detail1",detail1);
        detail.put("detail2",detail2);

        HashMap<String,Object> map=new HashMap<>();
        map.put("total",10);
        map.put("detail",detail);
        //System.out.println("json转map：   "+map);
        JSONObject jsonObject=new JSONObject(map);
        System.out.println("map转json：   "+jsonObject);

        String detailStr = jsonObject.getString("detail");
        String s = JSON.toJSONString(detailStr);
        //System.out.println(s);
        //System.out.println(JSON.toJSONString(s));

        String string = JSON.toJSONString(jsonObject.getObject("detail", HashMap.class));
        System.out.println(jsonObject.getObject("detail", HashMap.class));
        System.out.println("JSON字符串：  "+string);
        JSONObject detailJSON = JSONObject.parseObject(string);
        System.out.println("detailJSON:   "+detailJSON);
        String detail2Str = detailJSON.getString("detail2");
        JSONObject detail2JSON = JSONObject.parseObject(detail2Str);
        JSONArray addrJSONArray = detail2JSON.getJSONArray("地址");
        System.out.println(addrJSONArray);

        List<Address> addressList = addrJSONArray.toJavaList(Address.class);
        if(!CollectionUtils.isEmpty(addressList)){
            StringBuffer sb=new StringBuffer();
            for (Address addr : addressList) {
                sb.append(addr.getIp()+":"+addr.getPort()+"/"+addr.getNo()+",");
            }
            if(sb.indexOf(",")!=-1){
                sb.delete(sb.length()-1,sb.length());
            }
            System.out.println("sb:  "+sb);
        }

    }

}
