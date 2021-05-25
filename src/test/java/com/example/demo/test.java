package com.example.demo;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * @param
 * @ClassName test
 * @Author zengyi
 * @Description
 * @Date 2021/3/17 15:17
 **/
public class test {

    public static void main(String[] args) throws Exception {
        HttpServletRequest request=null;
        HttpServletResponse response = null;
        //创建xls
        XSSFWorkbook sheets = new XSSFWorkbook();
        //创建标题行的样式
        XSSFCellStyle style = sheets.createCellStyle();
        style.setFillForegroundColor(IndexedColors.YELLOW.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //创建sheet
        XSSFSheet sheet = sheets.createSheet("test表");
        //设置列的宽度
        sheet.setColumnWidth(0,15 * 256);
        sheet.setColumnWidth(1,10 * 256);
        //创建行
        XSSFRow row = sheet.createRow(0);
        //第一行第一列
        XSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("主题");
        //第一行第二列
        XSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("姓名");
        ArrayList<Info> list = new ArrayList<>();
        Info info1 = new Info();
        info1.setTitle("java开发");
        info1.setName("张三");
        Info info2 = new Info();
        info2.setTitle("C++开发");
        info2.setName("李四");
        list.add(info1);
        list.add(info2);

        for (int i = 0; i < list.size(); i++) {
            //从第二行开始写数据
            XSSFRow row1 = sheet.createRow(i + 1);
            //第二行的第一列
            XSSFCell cell = row1.createCell(0);
            cell.setCellValue(list.get(i).getName());
            //第二行的第二列
            XSSFCell cell2 = row1.createCell(1);
            cell2.setCellValue(list.get(i).getTitle());
        }

        //用输出流写到excel
        try {
            OutputStream outputStream = response.getOutputStream();
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + "user" + ".xlsx");
            sheets.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
