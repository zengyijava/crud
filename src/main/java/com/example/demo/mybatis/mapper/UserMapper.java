package com.example.demo.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.entity.UserDto;
import com.example.demo.mybatis.entity.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    Page<UserVo> queryByPage (Page<UserVo> page, @Param("dto") UserDto userDto);


}
