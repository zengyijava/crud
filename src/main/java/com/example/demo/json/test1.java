package com.example.demo.json;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;



import java.util.*;

public class test1 {
    public static void main(String[] args) {
        /////////////////////////////////     detail         ////////////////////////////////////
        HashMap<String,Object> detail1=new HashMap<>();
        detail1.put("姓名","张三");
        detail1.put("年龄",23);

        HashMap<String,Object> detail2=new HashMap<>();
        List list=new ArrayList<>();
        list.add("192.169.1.143:8080/emp,192.169.1.144:9090/emp");
        detail2.put("公司","梦网科技");
        detail2.put("职位","java开发");
        detail2.put("地址",list);

        HashMap<String,Object> detail=new HashMap<>();
        detail.put("detail1",detail1);
        detail.put("detail2",detail2);

        /////////////////////////////////     info         ////////////////////////////////////
        HashMap<String,Object> info1=new HashMap<>();
        List listInfo=new ArrayList<>();
        listInfo.add("192.169.1.100:8080/web,192.169.1.101:9090/web");
        info1.put("name","James");
        info1.put("postion","NBAPlayer");
        info1.put("listInfo",listInfo);

        HashMap<String,Object> info=new HashMap<>();
        info.put("info1",info1);

        HashMap<String,Object> map=new HashMap<>();
        map.put("total",10);
        map.put("detail",detail);
        map.put("info",info);
        System.out.println("json转map：   "+map);
        JSONObject jsonObject=new JSONObject(map);
        System.out.println("map转json：   "+jsonObject);

        JSONObject detailJSON = JSONObject.parseObject(JSON.toJSONString(jsonObject.getObject("detail",HashMap.class)));
        String detail2Str = detailJSON.getString("detail2");
        System.out.println("detail2Str:   "+detail2Str);

        JSONObject detail2JSON = JSONObject.parseObject(detail2Str);
        JSONArray addrJsonArr = detail2JSON.getJSONArray("地址");
        System.out.println("addrJsonArr:   "+addrJsonArr);
        String join = StringUtils.join(addrJsonArr);
        System.out.println("join:    "+join.toString());
        //地址，addrJsonArr转string
        JSONObject returnJson=new JSONObject();
//        String string = AddrJsonArrToString(returnJson, addrJsonArr, "addrString");
//        System.out.println("string:   "+string);


        //转化为list
        String detail2JSONString = detail2JSON.getString("地址");
        String substring = detail2JSONString.substring(2, detail2JSONString.length() - 2);
        String[] detail2Arr = substring.split(",");
        System.out.println("detail2JSONString:   "+detail2JSONString);
        System.out.println("substring:  "+substring);
        System.out.println("detail2Arr:  "+detail2Arr);

        List<Address> addressList = AddrJsonArrToList(detail2Arr);
        for (Address address : addressList) {
            System.out.println(address.getIp()+":"+address.getPort()+"/"+address.getNo());
        }


    }

    private static List<Address> AddrJsonArrToList(String[] detail2Arr) {
        //detail2Arr=["192.169.1.143:8080/emp","192.169.1.144:9090/emp"]
        List<Address> addrList=new ArrayList<>();
        Address address;
        String temp=null;
        for(String str:detail2Arr){
            address=new Address();
            if(str.indexOf("/")!=-1){
                address.setNo(str.split("/")[1]);
                temp=str.split("/")[0];
            }
            if(temp.indexOf(":")!=-1){
                address.setIp(temp.split(":")[0]);
                address.setPort(temp.split(":")[1]);
            }
            addrList.add(address);
        }
        return addrList;


    }

//    private static String AddrJsonArrToString(JSONObject returnJson, JSONArray addrJsonArr, String addrString) {
//        //List<Address> addressList = addrJsonArr.toJavaList(Address.class);
////        if(!CollectionUtils.isEmpty(addressList)){
////            StringBuffer sb=new StringBuffer();
////            for (Address address : addressList) {
////                sb.append(address.getIp().)
////            }
////
////        }
//        String[] arrays = (String[]) addrJsonArr.toArray();
//        StringBuffer sb=new StringBuffer();
//        for (String str : arrays) {
//            sb.append(str);
//        }
//        return sb.toString();
//    }

}
