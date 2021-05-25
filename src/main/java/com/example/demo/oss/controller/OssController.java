package com.example.demo.oss.controller;

import com.example.demo.mybatis.util.RsBody;
import com.example.demo.mybatis.util.Status;
import com.example.demo.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api
@RestController
@RequestMapping("/oss")
public class OssController {
    @Autowired
    private OssService ossService;

    @ApiOperation(value = "oss上传图片")
    @PostMapping(value = "/upload")
    public RsBody<String> uploadOssFile(MultipartFile file){
        RsBody<String> rsBody=new RsBody<>();
        String ossUrl=null;
        try {
            ossUrl=ossService.upload(file);
        }catch (Exception e){
            rsBody.setCode(Status.BU_FAILD.getCode());
            rsBody.setMessage(e.getMessage());
        }
        rsBody.setData(ossUrl);
        return rsBody;
    }

}
