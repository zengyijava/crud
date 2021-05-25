package com.example.demo.io;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class ObjectFileInputStream {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:/Users/dell/Desktop/b.txt");
        ObjectInputStream ois = new ObjectInputStream(fileInputStream);
        int data=0;
        ArrayList<Student> list = (ArrayList<Student>) ois.readObject();
        System.out.println(list.toString());
        ois.close();


    }


}
