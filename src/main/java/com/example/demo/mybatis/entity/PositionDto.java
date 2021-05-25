package com.example.demo.mybatis.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @param
 * @ClassName PositionDto
 * @Author zengyi
 * @Description
 * @Date 2021/4/2 14:22
 **/
@Data
public class PositionDto {

    /**
     * 当前页
     */
    @ApiModelProperty(hidden = true)
    protected Integer current =1;

    /**
     * 页大小
     */
    @ApiModelProperty(hidden = true)
    private Integer size = 5;


    private String position;


    private String username;

    //文件类型：1为txt，2为excel
    private String fileType;



}
