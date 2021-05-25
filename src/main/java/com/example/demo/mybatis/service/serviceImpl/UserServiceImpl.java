package com.example.demo.mybatis.service.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.entity.UserDto;
import com.example.demo.mybatis.entity.UserVo;
import com.example.demo.mybatis.mapper.UserMapper;
import com.example.demo.mybatis.service.UserService;
import com.example.demo.mybatis.util.RsBody;
import com.example.demo.mybatis.util.Status;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    @Override
    public Page<UserVo> queryByPage(UserDto userDto) {
        Page<UserVo> page=new Page<>(userDto.getCurrent(),userDto.getSize());
        Page<UserVo> voPage = userMapper.queryByPage(page, userDto);
        return voPage;
    }

    /**
     * 使用mybatis-plus自带分页
     * @param userDto
     * @return
     */
    @Override
    public RsBody<Page<User>> query(UserDto userDto) {
        RsBody<Page<User>> rsBody=new RsBody<>();
        //使用mybatis-plus自带分页
        Page<User> userPage=new Page<>(userDto.getCurrent(),userDto.getSize());
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(userDto.getUsername())){
            wrapper.like(User::getUsername,userDto.getUsername());
        }else if(null!=userDto.getPhone()){
            wrapper.eq(User::getPhone,userDto.getPhone());
        }
        wrapper.orderByAsc(User::getId);
        IPage<User> userIPage =userMapper.selectPage(userPage,wrapper);
        rsBody.setData((Page<User>) userIPage);
        return rsBody;
    }



    @Override
    public RsBody addUser(User user) {
        RsBody rsBody=new RsBody();
        //判断用户名是否重名，防止重复添加
        String message=queryWrapper(user);
        if(StringUtils.isEmpty(message)){
            user.setTime(format);
            userMapper.insert(user);
        }else {
            rsBody.setCode(Status.BU_FAILD.getCode());
            rsBody.setMessage(message);
        }
        return rsBody;
    }

    @Override
    public void deleteUserIds(List<Integer> userIds) {
        userMapper.deleteBatchIds(userIds);
    }

    @Override
    public String updatePwd(UserDto userDto) {
        String result="修改user失败！";
        userDto.setTime(format);
        int update = userMapper.updateById(userDto);
        if(update>0){
            result="";
        }
        return result;
    }



    /**
     * 防止重复添加相同用户名
     * @param user
     * @return
     */
    private String queryWrapper(User user) {
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        String message="该用户名已经存在，切勿重复添加！";
        User selectOne = userMapper.selectOne(wrapper);
        if(null==selectOne){
            message="";
        }
        return message;
    }



}
