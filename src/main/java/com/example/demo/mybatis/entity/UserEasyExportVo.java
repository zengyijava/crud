package com.example.demo.mybatis.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @param
 * @ClassName UserEasyExportVo
 * @Author zengyi
 * @Description
 * @Date 2021/5/7 11:06
 **/
@Data
@ApiModel(description = "UserEasyExportVo导出对象")
public class UserEasyExportVo extends BaseRowModel {
    @ExcelProperty(value = "序号", index = 0)
    @ApiModelProperty(value = "序号")
    private Integer serialNumber;

    @ExcelProperty(value = "主键id", index = 1)
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ExcelProperty(value = "用户名", index = 2)
    @ApiModelProperty(value = "用户名")
    private String username;

    @ExcelProperty(value = "密码", index = 3)
    @ApiModelProperty(value = "密码")
    private String password;

    @ExcelProperty(value = "手机号码", index = 4)
    @ApiModelProperty(value = "手机号码")
    private Long phone;
}
