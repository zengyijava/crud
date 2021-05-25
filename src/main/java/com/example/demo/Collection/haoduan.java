package com.example.demo.Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @param
 * @ClassName haoduan
 * @Author zengyi
 * @Description
 * @Date 2021/4/30 17:33
 **/
public class haoduan {
    public static void main(String[] args) {
        String[] strs = new String[3];
        List<String> haoduanList = new ArrayList<>();
        haoduanList.add("134,135,136,137,138,139,147,150,151,158,159,157,154,152,188,187,182,183,184,1705,178,1703,1706");
        haoduanList.add("130,131,132,155,156,185,186,145,176,1709,175,1707,1708,1718,1719");
        haoduanList.add("133,153,189,180,181,1700,177,173,1701,1702");

        for (int i = 0; i < haoduanList.size(); i++) {
            if(i==0){
                strs[0]=haoduanList.get(i);
            }else if(i==1){
                strs[1]=haoduanList.get(i);
            }else if(i==2){
                strs[2]=haoduanList.get(i);
            }
        }
        System.out.println(Arrays.asList(strs));

        //验证号码的号段是否合法
        String phone="13017347348";
        checkMobile(phone,haoduanList);

    }

    private static boolean checkMobile(String phone, List<String> haoduanList) {
        int[] numSegment = new int[1000];
        int phoneType;
        if(phone.length()!=11){
            return false;
        }
        for (int k = phone.length(); --k >= 0; ) {
            char single = phone.charAt(k);
            //增加根据Unicode编码判断是否为0-9的数字
            if (!Character.isDigit(single) || (int) single > 57 || (int) single < 48) {
                return false;
            }
        }
        if (phone != null && phone.length() > 6 && "1".equals(phone.substring(0, 1))) {
            int numSegmentTemp = Integer.parseInt(phone.substring(1, 4));
            if (numSegmentTemp > 0 && numSegmentTemp <= 999) {
                phoneType = numSegment[numSegmentTemp];
                System.out.println("号码运营商: "+phoneType);
            }
        }
        return true;
    }
}
