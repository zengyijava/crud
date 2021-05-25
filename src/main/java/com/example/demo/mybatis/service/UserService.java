package com.example.demo.mybatis.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.entity.UserDto;
import com.example.demo.mybatis.entity.UserVo;
import com.example.demo.mybatis.util.RsBody;

import java.util.List;

public interface UserService extends IService<User> {


    Page<UserVo> queryByPage(UserDto userDto);

    RsBody addUser(User user);

    void deleteUserIds(List<Integer> userIds);

    String  updatePwd(UserDto userDto);

    RsBody<Page<User>> query(UserDto userDto);


}
