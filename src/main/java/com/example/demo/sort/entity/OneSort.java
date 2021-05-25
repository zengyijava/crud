package com.example.demo.sort.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @param
 * @ClassName OneSort
 * @Author zengyi
 * @Description
 * @Date 2021/3/23 9:26
 **/
@Data
public class OneSort {

    private Integer id;

    private String title;

    List<TwoSort> children=new ArrayList<>();

}
