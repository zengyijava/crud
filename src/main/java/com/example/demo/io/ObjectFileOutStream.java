package com.example.demo.io;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class ObjectFileOutStream {

    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/dell/Desktop/b.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
        Student student = new Student("张三", 22);
        Student student1 = new Student("李四", 23);
        ArrayList<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student1);
        oos.writeObject(list);
        oos.close();


    }

}
