package com.example.demo.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;



@Data
public class UserExportDto {

    /**
     * 文导出文件类型 1是excel  2是txt
     */
    @ApiModelProperty(value = "导出文件类型 1是excel  2是txt")
    private String fileType;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "手机号码")
    private Long phone;

    /**
     * 当前页
     */
    @ApiModelProperty(hidden = true)
    protected Integer current =1;

    /**
     * 页大小
     */
    @ApiModelProperty(hidden = true)
    private Integer size = 10;


}
