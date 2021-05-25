package com.example.demo.io;



import java.io.FileReader;
import java.io.IOException;

public class FileRead {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("C:/Users/dell/Desktop/a.txt");
        int data=0;
        char[] chars = new char[1024];
        while ( (data=fr.read())!=-1 ){
            System.out.print( (char) data);
            //System.out.println( new String(chars,0,data) );
        }



    }




}
