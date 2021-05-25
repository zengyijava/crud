package com.example.demo.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @param
 * @ClassName jsonArr
 * @Author zengyi
 * @Description
 * @Date 2021/4/23 17:07
 **/
public class jsonArr {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(jsonArr.class);
        ArrayList<Address> list = new ArrayList<>();
        Address address1 = new Address();
        address1.setIp("192.169.1.143");
        address1.setPort("8080");
        address1.setNo("emp");

        Address address2 = new Address();
        address2.setIp("192.169.1.143");
        address2.setPort("8080");
        address2.setNo("emp");

        list.add(address1);
        list.add(address2);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("json",list);
        System.out.println(jsonObject);

        JSONArray jsonArray = jsonObject.getJSONArray("json");
        System.out.println(jsonArray);

    }
}
