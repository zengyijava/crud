package com.example.demo.mail;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @param
 * @ClassName mail
 * @Author zengyi
 * @Description
 * @Date 2021/3/24 14:00
 **/
@Component
public class SendMessage {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;
    @Value("${she.mail}")
    private String[] sheMail;
    public void sendMessage(String subject,String message) {

//        try {
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
//            helper.setFrom(from);//发送者邮件邮箱
//            helper.setTo(sheMail);//收邮件者邮箱
//            helper.setSubject(subject);//发件主题
//            helper.setText(message);//发件内容
//            mailSender.send(helper.getMimeMessage());//发送邮件
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//    }
//    /**远程获取要发送的信息*/
//    public static String getOneS(){
//        try {
//            //创建客户端对象
//            HttpClient client = HttpClients.createDefault();
//            /*创建地址 https://du.shadiao.app/api.php*/
//            HttpGet get = new HttpGet("https://chp.shadiao.app/api.php");
//            //发起请求，接收响应对象
//            HttpResponse response = client.execute(get);
//            //获取响应体，响应数据是一种基于HTTP协议标准字符串的对象
//            //响应体和响应头，都是封装HTTP协议数据。直接使用可能出现乱码或解析错误
//            HttpEntity entity = response.getEntity();
//            //通过HTTP实体工具类，转换响应体数据
//            String responseString = EntityUtils.toString(entity, "utf-8");
//
//            return responseString;
//
//        } catch (IOException e) {
//            throw  new RuntimeException("网站获取句子失败");
//        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前系统时间为：  "+ sdf.format(new Date())+ "    "+  message);
    }


}
