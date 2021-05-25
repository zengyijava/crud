package com.example.demo.mybatis.entity;

import lombok.Data;

import java.util.Date;

/**
 * @param
 * @ClassName PositionVo
 * @Author zengyi
 * @Description
 * @Date 2021/4/2 14:17
 **/
@Data
public class PositionVo {

    private Integer pid;

    private Integer userId;

    private String position;

    private Date createTime;

    private String username;

    private Long phone;



}
