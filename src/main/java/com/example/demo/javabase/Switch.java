package com.example.demo.javabase;

import java.util.Scanner;

/**
 * @param
 * @ClassName Switch
 * @Author zengyi
 * @Description
 * @Date 2021/4/20 14:53
 **/
public class Switch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入第一个数字：");
        int one = scanner.nextInt();
        System.out.println("请输入运算符：");
        String operator = scanner.next();
        System.out.println("请输入第二个数字：");
        int two = scanner.nextInt();
        switch (operator) {
            case "+":
                System.out.println(one + two);
                break;
            case "-":
                System.out.println(one - two);
                break;
            case "*":
                System.out.println(one * two);
                break;
            case "/":
                System.out.println(one / two);
                break;
            default:
                System.out.println("输入的运算符错误");
        }

    }


}
