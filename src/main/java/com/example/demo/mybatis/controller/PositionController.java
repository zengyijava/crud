package com.example.demo.mybatis.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mybatis.entity.Position;
import com.example.demo.mybatis.entity.PositionDto;
import com.example.demo.mybatis.entity.PositionVo;
import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.service.PositionService;
import com.example.demo.mybatis.service.UserService;
import com.example.demo.mybatis.util.POIUtil;
import com.example.demo.mybatis.util.RespPageBean;
import com.example.demo.mybatis.util.RsBody;
import com.example.demo.mybatis.util.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @param
 * @ClassName PositionController
 * @Author zengyi
 * @Description
 * @Date 2021/4/2 15:05
 **/
@Api
@RestController()
@RequestMapping("/position")
@Slf4j
public class PositionController {
    /**
     * 声明:如果不想每次都写private  final Logger logger = LoggerFactory.getLogger(当前类名.class);
     * 可以用注解@Slf4j;
     */
    @Autowired
    private PositionService positionService;
    @Autowired
    private UserService userService;

    @GetMapping("/pageByPosition")
    @ApiOperation(value = "分页查询position")
    public RsBody<Page<PositionVo>> pageByPosition(PositionDto positionDto){
        RsBody<Page<PositionVo>> rsBody=new RsBody<>();
        try {
            rsBody=positionService.queryPage(positionDto);
        }catch (Exception e){
            log.error("分页查询position失败");
            rsBody.setCode(Status.BU_FAILD.getCode());
            rsBody.setMessage(e.getMessage());
        }
        return rsBody;
    }

    @GetMapping("/simplePage")
    @ApiOperation(value = "传page，size的分页查询")
    public RespPageBean simplePage(@RequestParam("page") Integer page, @RequestParam("size") Integer size, PositionDto positionDto){
        return positionService.simplePage(page,size,positionDto);
    }

    @GetMapping("/export")
    @ApiOperation(value = "poi导出position")
    public RsBody<String> exportData(@RequestParam("page") Integer page, @RequestParam("size") Integer size,
                                     PositionDto positionDto, HttpServletRequest request, HttpServletResponse response){
        RsBody<String> rsBody = new RsBody<>();
        String msg = null;
        try {
            List<PositionVo> list = (List<PositionVo>) positionService.simplePage(page, size, positionDto).getList();
            msg = POIUtil.exportExcel(list,request,response);
        }catch (Exception e){
            log.error("poi导出position失败...");
            rsBody.setCode(Status.BU_FAILD.getCode());
            rsBody.setMessage(e.getMessage());
        }
        rsBody.setMessage(msg);
        return rsBody;

    }

    @GetMapping("/import")
    @ApiOperation(value = "导入position")
    public RsBody<String> importData(MultipartFile file,PositionDto positionDto) throws Exception {
        RsBody<String> rsBody=new RsBody<>();
        List<PositionVo> positionVoList=new ArrayList<>();
        String msg="";
        String filename = file.getOriginalFilename();
        String filename_suffix = filename.substring(filename.lastIndexOf("."));
        if(!filename_suffix.equals(".txt") && !filename_suffix.equals(".xls") && !filename_suffix.equals(".xlsx")){
            rsBody.setCode(Status.BU_FAILD.getCode());
            rsBody.setMessage("上传文件格式不对...");
        }
        if(positionDto.getFileType().equals("1")){
            //导入txt文件

        }else {
            //导入excel文件
            positionVoList=POIUtil.importExcel(file,positionDto);
        }
        //入库
        if(CollectionUtils.isNotEmpty(positionVoList)){
            for (PositionVo positionVo : positionVoList) {
                User user = new User();
                user.setId(positionVo.getUserId());
                user.setUsername(positionVo.getUsername());
                user.setPhone(positionVo.getPhone());
                user.setTime(positionVo.getCreateTime().toString());
                LambdaQueryWrapper<User> userWrapper=new LambdaQueryWrapper<>();
                userWrapper.eq(User::getPhone,positionVo.getPhone());
                if(userService.getOne(userWrapper)==null){
                    userService.save(user);
                    msg="入库user表成功";
                    rsBody.setMessage(msg);
                }else {
                    rsBody.setCode(Status.BU_FAILD.getCode());
                    rsBody.setMessage("添加user表，phone重复！");
                }
                Position position = new Position();
                position.setPid(positionVo.getPid());
                position.setUserId(positionVo.getUserId());
                position.setPosition(positionVo.getPosition());
                position.setCreateTime(positionVo.getCreateTime());
                boolean save = positionService.save(position);
                if(save){
                    rsBody.setCode(Status.Bu_SUCCESS.getCode());
                    rsBody.setMessage("导入文件postion表成功");
                }else {
                    rsBody.setCode(Status.BU_FAILD.getCode());
                    rsBody.setMessage("导入文件postion表失败...");
                }
            }

        }
        return rsBody;
    }




}
