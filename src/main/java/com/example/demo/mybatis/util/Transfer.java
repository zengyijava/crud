package com.example.demo.mybatis.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.entity.UserVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @param
 * @ClassName Transfer
 * @Author zengyi
 * @Description
 * @Date 2021/3/19 11:18
 **/
public class Transfer {
    public Page<UserVo> transfer(Page<User> page){
        if(page==null){
            return null;
        }
        Page<UserVo> voPage=new Page<UserVo>();
        voPage.setCurrent(page.getCurrent());
        voPage.setSize(page.getSize());
        voPage.setTotal(page.getTotal());
        voPage.setPages(page.getPages());
        voPage.setRecords(userListToUserVoList(page.getRecords()));
        return voPage;
    }

    private List<UserVo> userListToUserVoList(List<User> records) {
        if(null==records){
            return null;
        }
        List<UserVo> userVoList=new ArrayList<UserVo>();
        for (User record : records) {
            userVoList.add(userToUserVo(record));
        }
        return userVoList;

    }

    private UserVo userToUserVo(User record) {
        if(null==record){
            return null;
        }
        UserVo userVo=new UserVo();
        userVo.setId(record.getId());
        userVo.setUsername(record.getUsername());
        userVo.setPhone(record.getPhone());
        userVo.setTime(record.getTime());
        return userVo;
    }


}
