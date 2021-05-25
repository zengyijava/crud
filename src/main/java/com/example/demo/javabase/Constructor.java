package com.example.demo.javabase;

/**
 * @param
 * @ClassName Constructor
 * @Author zengyi
 * @Description
 * @Date 2021/4/21 15:49
 **/
public class Constructor {
    double x,y;

    public Constructor(double x,double y) {
         x=this.x;
         y=this.y;
    }

    public double get(Constructor constructor){
        return Math.sqrt( (x-constructor.x)*(x-constructor.x)+(y-constructor.y)*(y-constructor.y) );
    }

    public class testConstructor{
        public void main(String[] args) {
            Constructor a = new Constructor(0.0, 0.0);
            Constructor b = new Constructor(3.0, 4.0);
            System.out.println(a.get(b));

        }
    }

}
