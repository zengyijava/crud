package com.example.demo.easyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @param
 * @ClassName EasyExcelTest
 * @Author zengyi
 * @Description
 * @Date 2021/3/23 11:56
 **/
public class EasyExcelTest {
    public static void main(String[] args) {
        //C:\Users\dell\Desktop
        //File file=new File("C:\\dell\\Destop");
        String fileName="C:\\Users\\dell\\Desktop\\data.xlsx";
        //EasyExcel.write(fileName,ExcelData.class).sheet("学生表").doWrite(getData());


    }

    public static List<ExcelData> getData(){
        List<ExcelData> dataList=new ArrayList<>();
        for (int i=0;i<=10;i++){
            ExcelData data=new ExcelData();
            data.setSno(i);
            data.setSname("james"+i);
            dataList.add(data);
        }
        return dataList;
    }

}
