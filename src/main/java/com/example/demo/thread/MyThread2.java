package com.example.demo.thread;



import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 *现实多线程下载图片
 */
public class MyThread2 extends Thread {
    //构造体
    private String url;
    private String name;
    public MyThread2(String url,String name){
        this.url=url;
        this.name=name;
    }

    @Override
    public void run() {
        new WebDownLoader().downLoad(url,name);
        System.out.println("下载了图片："+name);
    }

    public static void main(String[] args) {
        MyThread2 t1 = new MyThread2("https://upload.jianshu.io/users/upload_avatars/1447174/5b2925ac-99cb-4efc-b3b5-826eb4895273.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240", "程序员dd1.jpg");
        MyThread2 t2 = new MyThread2("https://upload.jianshu.io/users/upload_avatars/1447174/5b2925ac-99cb-4efc-b3b5-826eb4895273.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240", "程序员dd2.jpg");
        MyThread2 t3 = new MyThread2("https://upload.jianshu.io/users/upload_avatars/1447174/5b2925ac-99cb-4efc-b3b5-826eb4895273.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240", "程序员dd3.jpg");
        t1.start();
        t2.start();
        t3.start();

    }

    public class WebDownLoader{
        public void downLoad(String url,String name){
            try {
                FileUtils.copyURLToFile(new URL(url),new File(name));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
