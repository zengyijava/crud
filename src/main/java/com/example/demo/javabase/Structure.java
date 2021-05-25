package com.example.demo.javabase;

import io.swagger.models.auth.In;

/**
 * @param
 * @ClassName Structure
 * @Author zengyi
 * @Description
 * @Date 2021/4/21 15:08
 **/
public class Structure {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public Integer getAge(){
        return age;
    }
    public void setAge(Integer age){
        this.age=age;
    }

    @Override
    public String toString() {
        return "Structure{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }



}
