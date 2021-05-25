package com.example.demo.io;

import com.alibaba.excel.constant.ExcelXmlConstants;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @param
 * @ClassName test
 * @Author zengyi
 * @Description
 * @Date 2021/4/23 9:43
 **/

public class test {
    public static void main(String[] args)  {
        File file = new File("C:\\Users\\dell\\Desktop\\1.txt");
        BufferedReader br = null;
        StringBuilder sb=null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line=null;
            sb=new StringBuilder();
            while ( (line=br.readLine()) !=null ){
                sb.append(line);
            }
            System.out.println(sb.toString());

            byte[] bytes = new byte[60 * 1024];
            FileInputStream in = new FileInputStream(file);
            int len = in.read(bytes);
            System.out.println("bytes.length:    "+bytes.length);
            System.out.println("len:    "+len);
            System.out.println(new String(bytes, 0, len));
            System.out.println(new String(bytes, 0, bytes.length));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(null!=br){
                    br.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
