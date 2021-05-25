package com.example.demo.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @param
 * @ClassName test1
 * @Author zengyi
 * @Description
 * @Date 2021/3/29 10:57
 **/
public class ReadFile {
    public static void main(String[] args) throws IOException {
//        FileInputStream fileInputStream = new FileInputStream("C:/Users/dell/Desktop/a.txt");
//        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//        BufferedReader br=new BufferedReader(inputStreamReader);
//        String read="";
//        String readStr="";
//        while ( (read=br.readLine())!=null ){
//            read+="\n";
//            readStr=readStr+read;
//        }
//        System.out.println(read);
//        System.out.println(readStr);
//        br.close();

        FileInputStream fileInputStream = new FileInputStream("C:/Users/dell/Desktop/1_2_20210506163005283_147511_10001_1001.txt.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        String read="";
        String readStr="";
        while ( (read=br.readLine())!=null ){
            read+="\n";
            System.out.print(read);
        }

        int data=0;
        StringBuffer sb=new StringBuffer();
        while ( (data=inputStreamReader.read()) !=-1 ){
            sb.append(data);
        }
        System.out.println(sb.toString());

        String line=null;
        byte[] bytes = new byte[1024];
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/dell/Desktop/1_2_20210506163005283_147511_10001_1001.txt.txt")));
        while ( (line=reader.readLine()) !=null ){
            sb.append(line);
        }
        System.out.println(sb.toString());


    }
}
