package com.example.demo.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @param
 * @ClassName CopyImg
 * @Author zengyi
 * @Description
 * @Date 2021/3/29 16:18
 **/
public class CopyImg {
    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("C:/Users/dell/Pictures/0.jpg");
        FileOutputStream out = new FileOutputStream("C:/Users/dell/Pictures/copy.jpg");
        Integer data=0;
        byte [] buff=new byte[1024];
        while ( (data=in.read(buff))!=-1 ){
            out.write(data);
        }
        in.close();
        out.close();



    }

}
