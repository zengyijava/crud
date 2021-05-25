package com.example.demo.easyExcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @param
 * @ClassName ExcelData
 * @Author zengyi
 * @Description
 * @Date 2021/3/23 11:55
 **/
@Data
public class ExcelData {
    @ExcelProperty(value = "学习编号")
    private Integer sno;

    @ExcelProperty(value = "学习姓名")
    private String sname;


}
