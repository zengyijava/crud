package com.example.demo.io;


import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile {
    public static void main(String[] args) throws IOException {
//        Long phone=13500000000L;
//        File file=new File("C:/Users/dell/Desktop/a.txt");
//        FileOutputStream out=new FileOutputStream(file);
//        StringBuffer temp;
//        if(!file.exists()){
//            file.createNewFile();
//        }
//        for (int i = 0; i <=10 ; i++) {
//            phone+=1;
//            temp = new StringBuffer(phone + "\n");
//            out.write(temp.toString().getBytes());
//            out.flush();
//        }
//        out.close();

        FileOutputStream out = new FileOutputStream("C:/Users/dell/Desktop/111.txt");
        BufferedOutputStream bos=new BufferedOutputStream(out);
        for (int i = 0; i <10 ; i++) {
            bos.write("hello\n".getBytes("utf-8"));
        }
        bos.close();

    }
}
