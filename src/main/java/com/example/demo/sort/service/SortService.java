package com.example.demo.sort.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.sort.entity.Sort;

import java.util.List;

/**
 * @param
 * @ClassName SortService
 * @Author zengyi
 * @Description
 * @Date 2021/3/23 9:24
 **/
public interface SortService extends IService<Sort> {
    List querySortList();
}
