package com.example.demo.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@ApiModel
@Data
@TableName(value = "tb_position")
public class Position {

    @ApiModelProperty(value = "职位id")
    @TableId(value = "pid",type = IdType.AUTO)
    private Integer pid;

    @ApiModelProperty(value = "用户id")
    @TableField(value = "userId")
    private Integer userId;

    @ApiModelProperty(value = "职位")
    @TableField(value = "position")
    private String position;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "createTime")
    private Date createTime;

    @ApiModelProperty(value = "")
    @TableField(value = "enable")
    private String enable;

    @ApiModelProperty(value = "备注")
    @TableField(value = "temp")
    private String temp;


}
