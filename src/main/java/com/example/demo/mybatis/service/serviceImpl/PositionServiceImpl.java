package com.example.demo.mybatis.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mybatis.entity.Position;
import com.example.demo.mybatis.entity.PositionDto;
import com.example.demo.mybatis.entity.PositionVo;
import com.example.demo.mybatis.mapper.PositionMapper;
import com.example.demo.mybatis.service.PositionService;
import com.example.demo.mybatis.util.RespPageBean;
import com.example.demo.mybatis.util.RsBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @param
 * @ClassName PositionServiceImpl
 * @Author zengyi
 * @Description
 * @Date 2021/4/2 15:12
 **/
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService {
    @Autowired
    private PositionMapper positionMapper;

    @Override
    public RsBody<Page<PositionVo>> queryPage(PositionDto positionDto) {
        RsBody<Page<PositionVo>> rsBody=new RsBody<>();
        Page<PositionVo> page=new Page<>();
        page.setCurrent(positionDto.getCurrent());
        page.setSize(positionDto.getSize());
        Page<PositionVo> voPage = positionMapper.queryPositionPages(page, positionDto);
        rsBody.setData(voPage);
        return rsBody;
    }

    @Override
    public RespPageBean simplePage(Integer page, Integer size, PositionDto positionDto) {
        if(page!=null && size!=null){
            page=(page-1) * size;
        }
        List<PositionVo> list=positionMapper.simplePage(page,size,positionDto);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setList(list);
        respPageBean.setTotal((long) list.size());
        return respPageBean;
    }
}
