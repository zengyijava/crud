package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mybatis.entity.*;
import com.example.demo.mybatis.mapper.PositionMapper;
import com.example.demo.mybatis.mapper.UserMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PositionMapper positionMapper;

    @Test
    void contextLoads() {

    }

    @Test
    public void findBypage(){
        User user1 = new User();
        user1.setUsername("李四");
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user1.getUsername());
        List<User> userList = userMapper.selectList(wrapper);
        for (User user : userList) {
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void UserBypage(){
        Page<UserVo> page=new Page<>();
        // SELECT id,username,password,time FROM tb_user where username='txt' LIMIT 0,3 ;
        page.setSize(3);
        page.setCurrent(1);
        UserDto userDto=new UserDto();
        userDto.setUsername("txt");
        Page<UserVo> voPage = userMapper.queryByPage(page, userDto);
        for (User user : voPage.getRecords()) {
            System.out.println("用户名： ~~~~~~~~~~~~~~~~~~~~~~~~~~~ : "+user.getUsername()+"  id： ~~~~~~~~~~~~~~~~~~~: "+user.getId());
        }
    }

    @Test
    public void pagePosition(){
        Page<PositionVo> voPage=new Page<>();
        voPage.setCurrent(1);
        voPage.setSize(5);
        PositionDto positionDto=new PositionDto();
        //positionDto.setUsername("王五");
        Page<PositionVo> pages = positionMapper.queryPositionPages(voPage, positionDto);
        if(CollectionUtils.isNotEmpty(pages.getRecords())){
            for (PositionVo page : pages.getRecords()) {
                System.out.println("--------------------------用户名：  "+page.getUsername()+"--------------------------用户id：   "+page.getUserId());
                System.out.println("**************************查询出来的总条数：            "+pages.getRecords().size());
            }
        }

    }

}
