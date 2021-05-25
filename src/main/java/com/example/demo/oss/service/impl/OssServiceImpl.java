package com.example.demo.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.demo.oss.service.OssService;
import com.example.demo.oss.util.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String upload(MultipartFile file) {

        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String endPoind = ConstantPropertiesUtil.END_POIND;

        try {
            OSS oss = new OSSClientBuilder().build(endPoind, accessKeyId, accessKeySecret);

            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String format = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            filename = format + "/" + uuid + filename;

            oss.putObject(bucketName, filename, inputStream);
            oss.shutdown();
            String url = "https://" + bucketName + "." + endPoind + "/" + filename;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
