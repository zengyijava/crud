package com.example.demo.io;


import java.io.File;
import java.io.IOException;

public class MyFile {
    public static void main(String[] args) throws IOException {
        //File file = new File("C:/Users/dell/Desktop/file.txt");
        //File file = new File("C:/Users/dell/Desktop/file.txt");
        File file = new File("D:\\A\\B");
        if(!file.exists()){
            file.mkdirs();
        }
        fileDir(new File("D:\\A"));


    }

    private static void fileDir(File file) {
        File[] files = file.listFiles();
        for (File f : files) {
            if(f.isDirectory()){
                fileDir(f);
            }else {
                System.out.println(f.getAbsolutePath());
            }
        }

    }

}
