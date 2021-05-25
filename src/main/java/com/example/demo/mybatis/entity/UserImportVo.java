package com.example.demo.mybatis.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @param
 * @ClassName 导入xlsx文件实体类  UserImportVo
 * @Author zengyi
 * @Description
 * @Date 2021/3/17 17:22
 **/
@Data
public class UserImportVo extends BaseRowModel {

    @ExcelProperty(value = "手机号码", index = 0)
    @ApiModelProperty(value = "手机号码")
    private Long phone;

}
