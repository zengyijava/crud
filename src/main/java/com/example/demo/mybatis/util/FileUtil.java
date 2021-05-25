package com.example.demo.mybatis.util;

import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.entity.UserImportVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
public class FileUtil {

    String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    /**
     * 读取txt文件字符编码
     * @param inputStream
     * @return
     */
    public String getFilecharset(InputStream inputStream) {
        //默认GBK
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            // 文件编码为 ANSI
            if (read == -1) {
                return charset;
            }
            // 文件编码为 Unicode
            if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                return "UTF-16LE";
            }
            // 文件编码为 Unicode big endian
            if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
                return "UTF-16BE";
            }
            // 文件编码为 UTF-8
            if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB && first3Bytes[2] == (byte) 0xBF) {
                return "UTF-8";
            }
            bis.reset();
            int loc = 0;
            while ((read = bis.read()) != -1) {
                loc++;
                if (read >= 0xF0) {
                    break;
                }
                // 单独出现BF以下的，也算是GBK
                if (0x80 <= read && read <= 0xBF) {
                    break;
                }
                if (0xC0 <= read && read <= 0xDF) {
                    read = bis.read();
                    // 双字节 (0xC0 - 0xDF)
                    if (0x80 <= read && read <= 0xBF) {
                        // (0x80 - 0xBF),也可能在GB编码内
                        continue;
                    }
                    break;
                }
                if (0xE0 <= read && read <= 0xEF) {
                    read = bis.read();
                    if (0x80 <= read && read <= 0xBF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            charset = "UTF-8";
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            log.error("获取文件编码异常：", e);
        }
        return charset;
    }

    /**
     *读取excel文件数据
     */
    public  List<User> getExcelData(MultipartFile file) throws Exception {
        checkFile(file);
        List<UserImportVo> list=new ArrayList<>();
        List<User> returnList=new ArrayList<>();
        String regex="^[0-9]+$";
        Pattern p = Pattern.compile(regex);
        try {
            //获得文件名
            String fileName = file.getOriginalFilename();
            if (fileName.endsWith("xls")) {
                list = parseXls(file);
            } else if (fileName.endsWith("xlsx")) {
                //0从第一行开始读
                list = ExcelImportUtil.readExcel(file, UserImportVo.class, 1, 0);
            }
            //将list中手机号码校验，返回returnList集合
            if(list!=null && list.size()>0){
                for (int i = 0; i < list.size(); i++) {
                    if(p.matcher(list.get(i).getPhone().toString()).matches()==true){
                        User u=new User();
                        u.setPhone(list.get(i).getPhone());
                        u.setTime(format);
                        if(fileName.endsWith("xls")){
                            u.setUsername("xls");
                        }else {
                            u.setUsername("xlsx");
                        }
                        returnList.add(u);
                    }
                }
            }
        }catch (Exception e){
            throw e;
        }
        return returnList;
    }

    /**
     * 读取xls文件
     * @param file
     * @return
     * @throws Exception
     */
    public static List<UserImportVo> parseXls(MultipartFile file) throws Exception {
        List<UserImportVo> userList=new ArrayList<>();
        String regex="^[0-9]+$";
        Pattern p = Pattern.compile(regex);
        jxl.Workbook workbook = null;
        try {
            workbook = jxl.Workbook.getWorkbook(file.getInputStream());
            if (workbook != null) {
                jxl.Sheet sheet = workbook.getSheet(0);
                if (sheet == null) {
                    return userList;
                }
                for (int i = 0; i < sheet.getRows(); i++) {
                    UserImportVo importVo=new UserImportVo();
                    jxl.Cell[] cells = sheet.getRow(i);
                    if (cells.length>=1) {
                        if (cells[0] != null) {
                            if (p.matcher(cells[0].getContents()).matches() == true) {
                                importVo.setPhone(Long.parseLong(cells[0].getContents()));
                                userList.add(importVo);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return userList;
    }



    /**
     * 判断file是否为空，Excel文件？
     * @param file
     */
    private void checkFile(MultipartFile file) {
          if(file==null){
              log.error("上传文件为空！");
          }
        String filename = file.getOriginalFilename();
        if(!filename.endsWith(".xls") && !filename.endsWith(".xlsx")){
            log.error(filename+"上传文件不是Excel文件！");
        }
    }


}
