package com.example.demo.io;

import java.io.Serializable;

/**
 * @param
 * @ClassName Student
 * @Author zengyi
 * @Description
 * @Date 2021/3/29 18:06
 **/

public class Student implements Serializable {
    private static final long serialVersionUID=1000L;

    private String name;

    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
