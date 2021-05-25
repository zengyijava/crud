package com.example.demo.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * 前端传参对象
 */
@Data
public class UserDto extends User{

    @ApiModelProperty(value = "主键id集合")
    private List<Integer> userIds;

    /**
     * 文导出文件类型 1是excel  2是txt
     */
    @ApiModelProperty(value = "导出文件类型 1是excel  2是txt")
    private String fileType;

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


    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "手机号码")
    private Long phone;



}
