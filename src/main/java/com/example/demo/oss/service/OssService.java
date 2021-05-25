package com.example.demo.oss.service;


import org.springframework.web.multipart.MultipartFile;

/**
 * @param
 * @ClassName OssService
 * @Author zengyi
 * @Description
 * @Date 2021/3/22 15:45
 **/
public interface OssService {
    public String upload(MultipartFile file) ;
}
