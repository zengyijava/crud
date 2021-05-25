package com.example.demo;


import lombok.Data;

import java.util.Date;

/**
 * @param
 * @ClassName InfoDto
 * @Author zengyi
 * @Description
 * @Date 2021/3/24 17:28
 **/
@Data
public class InfoDto {

    private Integer id;


    private String title;


    private Integer parentId;

    private Date time;


}
