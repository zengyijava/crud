package com.example.demo.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mybatis.entity.Position;
import com.example.demo.mybatis.entity.PositionDto;
import com.example.demo.mybatis.entity.PositionVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PositionMapper extends BaseMapper<Position> {

    Page<PositionVo> queryPositionPages(Page<PositionVo> voPage, @Param("dto") PositionDto positionDto);

    List<PositionVo> simplePage(@Param("page") Integer page,@Param("size") Integer size,@Param("dto") PositionDto positionDto);

}
