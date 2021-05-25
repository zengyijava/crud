package com.example.demo.webserver;

/**
 * @param
 * @ClassName MyReflect
 * @Author zengyi
 * @Description
 * @Date 2021/5/6 16:37
 **/
public class MyReflect {
    public static void main(String[] args) throws Exception {
        //对象.getClass
        Class<? extends Info> aClass = new Info().getClass();
        System.out.println(aClass);
        //类.class
        Class<Info> infoClass = Info.class;
        System.out.println(infoClass);
        //Class.forName("包名.类名")
        Class<?> aClass1 = Class.forName("com.example.demo.webserver.Info");
        System.out.println(aClass1);
        Object o = aClass1.getConstructor().newInstance();
        System.out.println(o);
    }
}


 class Info{
    public Info(){
    }
}
