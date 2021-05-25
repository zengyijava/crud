package com.example.demo.sort.controller;

import com.example.demo.mybatis.util.RsBody;
import com.example.demo.mybatis.util.Status;
import com.example.demo.sort.service.SortService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

/**
 * @param
 * @ClassName SortController
 * @Author zengyi
 * @Description
 * @Date 2021/3/23 9:23
 **/
@Api
@RequestMapping("/sort")
@RestController
public class SortController  {

    @Autowired
    private SortService sortService;

    @ApiOperation(value = "分类列表")
    @GetMapping("/list")
    public RsBody<List> querySortList(){
        RsBody<List> rsBody=new RsBody<>();
        List list=new ArrayList();
        try {
            list=sortService.querySortList();
        }catch (Exception e){
            rsBody.setCode(Status.BU_FAILD.getCode());
            rsBody.setMessage(e.getMessage());
        }
        rsBody.setData(list);
        return rsBody;
    }





}
