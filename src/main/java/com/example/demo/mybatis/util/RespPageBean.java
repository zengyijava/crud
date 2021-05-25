package com.example.demo.mybatis.util;

import lombok.Data;

import java.util.List;

/**
 * @param
 * @ClassName RespPageBean
 * @Author zengyi
 * @Description
 * @Date 2021/4/6 9:22
 **/
@Data
public class RespPageBean {
    private Long total;
    private List<?> list;

}
