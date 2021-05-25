package com.example.demo.enumDemo;

import java.util.Objects;

/**
 * @param
 * @ClassName Enum
 * @Author zengyi
 * @Description
 * @Date 2021/3/19 14:17
 **/
public enum  EnumDemo {
    Type1(1,"A"),
    Type2(2,"B"),
    Type3(3,"C");

    private Integer index;
    private String grade;
    EnumDemo(Integer index, String grade) {
        this.index=index;
        this.grade=grade;
    }

    public Integer getIndex(){
        return index;
    }

    public String getGrade(){
        return grade;
    }

    public static String getGrageByIndex(Integer index){
        String result=null;
        if(index==null){
            return null;
        }
        for (EnumDemo value : EnumDemo.values()) {
            if(Objects.equals(index,value.getIndex())){
                result=value.getGrade();
                break;
            }
        }
        return result;


    }


}
