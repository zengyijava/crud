package com.example.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

public class test4 {
    public static void main(String[] args) {
        Map<String,Object> detail1Map=new HashMap<>();
        detail1Map.put("姓名","张三");
        detail1Map.put("年龄",23);

        Map<String,Object> detail2Map=new HashMap<>();
        detail2Map.put("公司","梦网科技");
        List<Address> addrList = new ArrayList<>();
        Address address1 = new Address();
        address1.setIp("192.169.1.143");
        address1.setNo("emp");
        address1.setPort("8080");
        Address address2 = new Address();
        address2.setIp("192.169.1.144");
        address2.setNo("emp");
        address2.setPort("9090");
        addrList.add(address1);
        addrList.add(address2);
        detail2Map.put("地址",addrList);

        Map<String,Object> detailMap=new HashMap<>();
        detailMap.put("detail1",detail1Map);
        detailMap.put("detail2",detail2Map);

        Map<String,Object> map=new HashMap<>();
        map.put("total",10);
        map.put("detail",detailMap);
        System.out.println("map:   "+map);

        //map转json对象方法一
        JSONObject jsonObject1 = new JSONObject(map);
        System.out.println("map转json对象一：  "+jsonObject1);

        //map转json对象方法二
        String JSONStr = JSON.toJSONString(map);
        System.out.println("JSONStr:  "+JSONStr);
        JSONObject jsonObject2 = JSONObject.parseObject(JSONStr);
        System.out.println("map转json对象二：  "+jsonObject2);

        //*******************************从前端传过来的json数据中取值存库********************************
        //System.out.println("错误的转化json=======    "+JSONObject.parseObject(jsonObject1.getString("detail")));

        HashMap hashMap = jsonObject1.getObject("detail", HashMap.class);
        String detailStr = JSON.toJSONString(hashMap);
        JSONObject detailJSON = JSONObject.parseObject(detailStr);
        System.out.println("detailStr:   "+detailStr);
        System.out.println("detailJSON:   "+detailJSON);

        String detail2Str = detailJSON.getString("detail2");
        JSONObject detail2JSON = JSONObject.parseObject(detail2Str);

        JSONArray addrJSONArr = detail2JSON.getJSONArray("地址");
        System.out.println("addrJSONArr:   "+addrJSONArr);

        //json数组转化为list集合
        List<Address> addressList = addrJSONArr.toJavaList(Address.class);
        /**
         * json数组:          [{"no":"emp","port":"8080","ip":"192.169.1.143"},{"no":"emp","port":"9090","ip":"192.169.1.144"}]
         * 转化为字符串：      "192.169.1.143:8080/emp,192.169.1.144:9090/emp"
         */
        StringBuffer sb=new StringBuffer();
        for (Address address : addressList) {
            sb.append(address.getIp()+":"+address.getPort()+"/"+address.getNo()+",");
        }
        if(sb.indexOf(",")!=-1){
            sb.delete(sb.length()-1,sb.length());
        }
        System.out.println("sb字符串=========="+sb.toString());


        /**
         *  字符串：             "192.169.1.143:8080/emp,192.169.1.144:9090/emp"
         * 转化为json数组：      {"strMap":[{"ip":"192.169.1.143","no":"emp","port":"8080"},{"ip":"192.169.1.144","no":"emp","port":"9090"}]}
         */
        String str="192.169.1.143:8080/emp,192.169.1.144:9090/emp";
        Map<String,Object> strMap=new HashMap<>();
        List<Address> list = getAddrList(str);
        //类似等于detail2Map
        strMap.put("strMap",list);
        System.out.println("strMap:    "+strMap);
        //map转json
        JSONObject jsonObject = new JSONObject(strMap);
        System.out.println("jsonObject:   "+jsonObject);

        /**
         * 返回bigJSON ：
         * {"total":10,"detail":{"detail1":{"姓名":"张三","年龄":"23"},"detail2":{"公司":"梦网科技","地址":[{"ip":"192.169.1.143","no":"emp","port":"8080"},{"ip":"192.169.1.144","no":"emp","port":"9090"}]}}}
         *
         */
        JSONObject d2JSON = new JSONObject();
        d2JSON.put("公司","梦网科技");
        d2JSON.put("地址",list);
        JSONObject d1JSON = new JSONObject();
        d1JSON.put("姓名","张三");
        d1JSON.put("年龄","23");
        JSONObject dJSON = new JSONObject();
        dJSON.put("detail1",d1JSON);
        dJSON.put("detail2",d2JSON);

        JSONObject bigJSON = new JSONObject();
        bigJSON.put("total",10);
        bigJSON.put("detail",dJSON);
        System.out.println("返回bigJSON:      "+bigJSON);

    }

    private static List<Address> getAddrList(String str) {
        List<Address> returnList=new ArrayList<>();
        List<String> asList = Arrays.asList(str.split(","));
        //asList =  [192.169.1.143:8080/emp,192.169.1.144:9090/emp]
        if(CollectionUtils.isNotEmpty(asList)){
            for (String s : asList) {
                Address address = new Address();
                String temp;
                if(s.indexOf("/")!=-1){
                    address.setNo( s.split("/")[1]);
                    temp=s.split("/")[0];
                    if(temp.indexOf(":")!=-1){
                        address.setIp(temp.split(":")[0]);
                        address.setPort(temp.split(":")[1]);
                    }
                }
                returnList.add(address);
            }
        }
        return returnList;
    }




}
