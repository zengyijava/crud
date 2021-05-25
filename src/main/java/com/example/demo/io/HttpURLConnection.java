package com.example.demo.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @param
 * @ClassName test
 * @Author zengyi
 * @Description
 * @Date 2021/3/17 15:17
 **/
public class HttpURLConnection {

    public static void main(String[] args) throws Exception {
        //URL url = new URL("http://192.169.1.143:8080/emp/file/smstxt/manualsmstxt/2021/3/26/1_2_2021032614361610_118192_10001_1001.txt");
        URL url = new URL("https://blog.csdn.net/comeonyangzi/article/details/55510621");
        URLConnection urlConnection=url.openConnection();
        java.net.HttpURLConnection httpURLConnection= (java.net.HttpURLConnection) urlConnection;
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        InputStream inputStream = httpURLConnection.getInputStream();
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
        BufferedReader br = new BufferedReader(inputStreamReader);
        String read="";
        String readStr="";
        while ((read=br.readLine())!=null){
            read+=" </br> ";
            readStr=readStr+read;
        }
        System.out.println(readStr);
        br.close();

    }
}
