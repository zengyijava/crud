package com.example.demo.mybatis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.mybatis.entity.Position;
import com.example.demo.mybatis.entity.PositionDto;
import com.example.demo.mybatis.entity.PositionVo;
import com.example.demo.mybatis.util.RespPageBean;
import com.example.demo.mybatis.util.RsBody;

/**
 * @param
 * @ClassName PositonService
 * @Author zengyi
 * @Description
 * @Date 2021/4/2 15:10
 **/
public interface PositionService extends IService<Position> {

    RsBody<Page<PositionVo>> queryPage(PositionDto positionDto);

    RespPageBean simplePage(Integer page, Integer size, PositionDto positionDto);
}
