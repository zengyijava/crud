package com.example.demo.mybatis.util;

import com.example.demo.mybatis.entity.PositionDto;
import com.example.demo.mybatis.entity.PositionVo;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @param
 * @ClassName POIUtil
 * @Author zengyi
 * @Description
 * @Date 2021/4/6 10:26
 **/
public class POIUtil {


    public static String exportExcel(List<PositionVo> list, HttpServletRequest request, HttpServletResponse response) {
        //1. 创建一个 Excel 文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2. 创建文档摘要
        workbook.createInformationProperties();
        //3. 获取并配置文档信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        //文档类别
        docInfo.setCategory("用户信息");
        //文档管理员
        docInfo.setManager("zy1024bit");
        //设置公司信息
        docInfo.setCompany("www.zy.org");
        //4. 获取文档摘要信息
        SummaryInformation summInfo = workbook.getSummaryInformation();
        //文档标题
        summInfo.setTitle("用户职位表");
        //文档作者
        summInfo.setAuthor("zy");
        // 文档备注
        summInfo.setComments("本文档由 zy 提供");
        //5. 创建样式
        //创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        HSSFSheet sheet = workbook.createSheet("员工信息表");
        //设置列的宽度
        sheet.setColumnWidth(0, 12 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 20 * 256);
        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(5, 15 * 256);
        //6. 创建标题行
        HSSFRow r0 = sheet.createRow(0);
        HSSFCell c0 = r0.createCell(0);
        c0.setCellValue("职位编号");
        c0.setCellStyle(headerStyle);
        HSSFCell c1 = r0.createCell(1);
        c1.setCellStyle(headerStyle);
        c1.setCellValue("用户id");
        HSSFCell c2 = r0.createCell(2);
        c2.setCellStyle(headerStyle);
        c2.setCellValue("职位");
        HSSFCell c3 = r0.createCell(3);
        c3.setCellStyle(headerStyle);
        c3.setCellValue("创建时间");
        HSSFCell c4 = r0.createCell(4);
        c4.setCellStyle(headerStyle);
        c4.setCellValue("用户名");
        HSSFCell c5 = r0.createCell(5);
        c5.setCellStyle(headerStyle);
        c5.setCellValue("手机号码");
        for (int i = 0; i < list.size(); i++) {
            PositionVo positionVo = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(positionVo.getPid());
            row.createCell(1).setCellValue(positionVo.getUserId());
            row.createCell(2).setCellValue(positionVo.getPosition());
            HSSFCell cell3 = row.createCell(3);
            cell3.setCellStyle(dateCellStyle);
            cell3.setCellValue(positionVo.getCreateTime());
            row.createCell(4).setCellValue(positionVo.getUsername());
            row.createCell(5).setCellValue(positionVo.getPhone());
        }
        //用输出流写到excel
        try {
            OutputStream outputStream = response.getOutputStream();
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + "position" + ".xls");
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            //log.error("生成excel失败:"+e);
            e.printStackTrace();
        }
        return "生成excel文件成功";
    }

    public static List<PositionVo> importExcel(MultipartFile file, PositionDto positionDto) throws Exception {
        List<PositionVo> list=new ArrayList<>();
        PositionVo positionVo = null;
        //创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            //获取sheet
            HSSFSheet sheet = workbook.getSheetAt(i);
            //获取总行数
            int rows = sheet.getPhysicalNumberOfRows();
            for (int j = 0; j < rows; j++) {
                HSSFRow row = sheet.getRow(j);
                //从第二行开始读
                if(j==0){
                    continue;
                }
                //防止读取行为空
                if(sheet.getRow(j)==null){
                    continue;
                }
                //获取该行的总列数
                int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                positionVo=new PositionVo();
                for (int k = 0; k < physicalNumberOfCells; k++) {
                    HSSFCell cell = row.getCell(k);
                    switch (cell.getCellType()) {
                        case STRING:
                            String cellValue = cell.getStringCellValue();
                            switch (k) {
                                case 0:
                                    positionVo.setPid(Integer.parseInt(cellValue));
                                    break;
                                case 1:
                                    positionVo.setUserId(Integer.parseInt(cellValue));
                                    break;
                                case 2:
                                    positionVo.setPosition(cellValue);
                                    break;
                                case 3:
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Date date = simpleDateFormat.parse(cellValue);
                                    positionVo.setCreateTime(date);
                                    break;
                                case 4:
                                    positionVo.setUsername(cellValue);
                                    break;
                                case 5:
                                    positionVo.setPhone(Long.parseLong(cellValue));
                                    break;
                            }
                    }
                }
            list.add(positionVo);
            }
        }
        return list;
    }
}
