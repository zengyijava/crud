package com.example.demo.sort.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @param
 * @ClassName Sort
 * @Author zengyi
 * @Description
 * @Date 2021/3/23 9:26
 **/
@ApiModel
@TableName("sort")
@Data
public class Sort {
    @ApiModelProperty(value = "分类id")
    @TableId(value = "id")
    private Integer id;

    @ApiModelProperty(value = "主题")
    @TableField(value = "title")
    private String title;

    @ApiModelProperty(value = "父id")
    @TableField(value = "parentId")
    private Integer parentId;





}
