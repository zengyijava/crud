package com.example.demo.mybatis.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mybatis.entity.User;
import com.example.demo.mybatis.entity.UserDto;
import com.example.demo.mybatis.entity.UserEasyExportVo;
import com.example.demo.mybatis.entity.UserVo;
import com.example.demo.mybatis.service.UserService;
import com.example.demo.mybatis.util.FileUtil;
import com.example.demo.mybatis.util.RqBody;
import com.example.demo.mybatis.util.RsBody;
import com.example.demo.mybatis.util.Status;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.input.BOMInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/user")
@Api
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    private static HashMap<String, String> fileTypeMap = null;
    static {
        fileTypeMap = new HashMap<String, String>();
        fileTypeMap.put("txt", "");
        fileTypeMap.put("xls", "");
        fileTypeMap.put("xlsx", "");
    }

    String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    @GetMapping("/findByPage")
    @ApiOperation(value = "tb_user分页查询")
    public RsBody<Page<UserVo>> findByPage(UserDto userDto){
        log.info("tb_user分页查询",userDto);
        RsBody<Page<UserVo>> rsBody=new RsBody<>();
        try {
            Page<UserVo> voPage = userService.queryByPage(userDto);
            rsBody.setCode(Status.Bu_SUCCESS.getCode());
            rsBody.setMessage("成功");
            rsBody.setData(voPage);
        }catch (Exception e){
            log.info("分页查询异常...");
            rsBody.setCode(Status.BU_FAILD.getCode());
            rsBody.setMessage(e.getMessage());
        }
        return rsBody;
    }

    //@RequestMapping(value = "/find",method = RequestMethod.GET);

    @GetMapping("/query")
    public RsBody<Page<User>> query(UserDto userDto){
        RsBody<Page<User>> rsBody=new RsBody<>();
        try {
            rsBody=userService.query(userDto);
        }catch (Exception e){
            rsBody.setCode(Status.BU_FAILD.getCode());
            rsBody.setMessage(e.getMessage());
            log.info("分页查询异常...");
        }
        return rsBody;
    }



    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public RsBody addUser(@RequestBody RqBody<User> rqBody){
        RsBody rsBody=new RsBody();
        User user = rqBody.getData();
        try {
            rsBody=userService.addUser(user);
        }catch (Exception e){
            rsBody.setCode(Status.BU_FAILD.getCode());
            rsBody.setMessage(e.getMessage());
            log.info("添加user异常...");
        }
        return rsBody;
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "支持批量删除")
    public RsBody batchDelete(@RequestBody RqBody<UserDto> userDto){
        RsBody rsBody=new RsBody();
        UserDto data = userDto.getData();
        try {
            userService.deleteUserIds(data.getUserIds());
        }catch (Exception e){
            rsBody.setCode(Status.BU_FAILD.getCode());
            rsBody.setMessage(e.getMessage());
            log.info("批量删除userIds异常...",e);
        }
        return rsBody;
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改")
    public RsBody updatePwd(@RequestBody UserDto userDto){
        RsBody rsBody=new RsBody();
        //UserDto data = userDto.getData();
        try {
            String result=userService.updatePwd(userDto);
            if(StringUtils.isNotBlank(result)){
                rsBody.setCode(Status.BU_FAILD.getCode());
                rsBody.setMessage(result);
            }
        }catch (Exception e){
            rsBody.setCode(Status.BU_FAILD.getCode());
            rsBody.setMessage(e.getMessage());
            log.info("修改user异常...",e);
        }
        return rsBody;
    }

    @ApiOperation(value = "导入")
    @PostMapping(value = "/import")
    public RsBody<String> importFile(@RequestParam("importFile")MultipartFile file) throws Exception {
        RsBody<String> rsBody=new RsBody<>();
        String returnStr="导入文件失败";
        List<User> userList=new ArrayList<>();
        if(!file.isEmpty()){
            //校验文件类型
            String fileName = file.getOriginalFilename();
            String file_suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if(!fileTypeMap.containsKey(file_suffix.trim())){
                returnStr="上传文件格式不正确";
                rsBody.setData(returnStr);
                rsBody.setCode(Status.BU_FAILD.getCode());
                return rsBody;
            }
            //txt格式的导入
            if("txt".equals(file_suffix.trim())){
                InputStream in=file.getInputStream();
                String charset = new FileUtil().getFilecharset(in);
                // BOMInputStream自动去除UTF-8中的BOM
                BOMInputStream bomInputStream = new BOMInputStream(file.getInputStream());
                BufferedReader br=new BufferedReader(new InputStreamReader(bomInputStream,charset));
                String lineStr = "";
                String regex="^[0-9]+$";
                Pattern p = Pattern.compile(regex);
                while ((lineStr=br.readLine())!=null){
                    if(lineStr.length()!=0){
                        try {
                            String[] lineValue = lineStr.split(",");
                            //正则表达式校验
                            if(p.matcher(lineValue[0]).matches()==true){
                                User user=new User();
                                user.setPhone(Long.parseLong(lineValue[0]));
                                user.setTime(format);
                                user.setUsername("txt");
                                userList.add(user);
                            }
                        }catch (Exception e){
                            //log.error("上传txt文件解析异常{}", e);
                            continue;
                        }
                    }else {
                        continue;
                    }
                }
                br.close();
                if(CollectionUtils.isEmpty(userList)){
                    returnStr = "上传文件内容为空,或无合法的手机号码";
                    rsBody.setData(returnStr);
                    rsBody.setMessage(returnStr);
                    rsBody.setCode(Status.BU_FAILD.getCode());
                    return rsBody;
                }
            }else {
                //excel格式的导入
                try {
                    userList = new FileUtil().getExcelData(file);
                    if (CollectionUtils.isEmpty(userList)) {
                        returnStr = "上传文件内容为空,或无合法的手机号码";
                        rsBody.setData(returnStr);
                        rsBody.setMessage(returnStr);
                        rsBody.setCode(Status.BU_FAILD.getCode());
                        return rsBody;
                    }
                    if (userList.size() >= 200000) {
                        returnStr = "文件记录数超过二十万，上传失败";
                        rsBody.setData(returnStr);
                        rsBody.setMessage(returnStr);
                        rsBody.setCode(Status.BU_FAILD.getCode());
                        return rsBody;
                    }
                } catch (Exception e) {
                    //log.error("导入数据格式异常:", e);
                    returnStr = "数据格式异常";
                    rsBody.setData(returnStr);
                    rsBody.setMessage(returnStr);
                    rsBody.setCode(Status.BU_FAILD.getCode());
                    return rsBody;
                }
            }

            //入库
            if(!CollectionUtils.isEmpty(userList)){
                for (User u : userList) {
                    LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
                    wrapper.eq(User::getPhone,u.getPhone());
                    if(null==userService.getOne(wrapper)){
                        userService.save(u);
                    }else {
                        rsBody.setMessage("用户名或者手机号码已存在！");
                        rsBody.setCode(Status.BU_FAILD.getCode());
                    }
                }
                rsBody.setData("导入文件成功！");
                rsBody.setCode(Status.Bu_SUCCESS.getCode());
                return rsBody;
            }
        }else {
            returnStr = "上传文件内容为空!";
            rsBody.setData(returnStr);
            rsBody.setMessage(returnStr);
            rsBody.setCode(Status.BU_FAILD.getCode());
            return rsBody;
        }
        return rsBody;
    }

    /**
     *   不需要加@RequestBody
     * @param userDto
     * @param request
     * @param response
     */
    @ApiOperation(value = "导出txt，excel文件")
    @GetMapping(value = "/export")
    public void export(UserDto userDto, HttpServletRequest request, HttpServletResponse response){
        RsBody rsBody=new RsBody();
        List<User> userList=new ArrayList<>();
        try {
            userList=userService.list(queryWrapper(userDto));
        }catch (Exception e){
            rsBody.setMessage(e.getMessage());
            rsBody.setCode(Status.BU_FAILD.getCode());
            //log.info("查询tb_user表异常");
        }
        //导入excel
        if("1".equals(userDto.getFileType())){
            String exportExcelStr = generatorExcel(userList, response, request);
            rsBody.setData(exportExcelStr);
        }else {
            //导出txt文件
            BufferedOutputStream buff=null;
            try {
                OutputStream outputStream = response.getOutputStream();
                response.setContentType("multipart/form-data");
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-disposition", "attachment;filename=" + "user" + ".txt");
                buff = new BufferedOutputStream(outputStream);

                for (User user : userList) {
                    if(StringUtils.isNotEmpty(user.getUsername())){
                        buff.write( (user.getUsername()+","+String.valueOf(user.getPhone())).getBytes("utf-8") );
                    }else {
                        buff.write( String.valueOf(user.getPhone()).getBytes("utf-8") );
                    }
                    buff.write("\r\n".getBytes());
                }
                buff.flush();
                buff.close();

                rsBody.setCode(Status.Bu_SUCCESS.getCode());
            }catch (Exception e){
                log.error("导出数据失败:" + e);
                rsBody.setCode(Status.BU_FAILD.getCode());
            }finally {
                try {
                    rsBody.setCode(Status.Bu_SUCCESS.getCode());
                    buff.close();
                } catch (Exception e) {
                    log.error("导出数据失败:" + e);
                    rsBody.setCode(Status.BU_FAILD.getCode());
                }
            }
        }


    }

    @PostMapping(value = "/easyExport")
    public RsBody<String> EasyExport(@RequestBody UserDto userDto){
        log.info("请求EasyExport方法，请求参数UserDto：{}",userDto);
        RsBody<String> rsBody=new RsBody<>();
        Page<UserVo> voPage = userService.queryByPage(userDto);
        List<UserVo> records = voPage.getRecords();
        if(org.apache.commons.collections4.CollectionUtils.isNotEmpty(records)){
            if(records.size()>100*10000){
                rsBody.setMessage("数据大于1000000，无法导出");
            }else {
                List<UserEasyExportVo> userEasyExportVoList=new ArrayList<>();
                for (int i = 0; i < records.size(); i++) {
                    UserVo record = records.get(i);
                    UserEasyExportVo userEasyExportVo = new UserEasyExportVo();
                    userEasyExportVo.setSerialNumber(i+1);
                    userEasyExportVo.setId(record.getId());
                    userEasyExportVo.setUsername(record.getUsername());
                    userEasyExportVo.setPassword(record.getPassword());
                    userEasyExportVo.setPhone(record.getPhone());
                    userEasyExportVoList.add(userEasyExportVo);
                }
                String fileName="easyExport";
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
                // 导出excel
                rsBody = postExcelExport2007(request, response, userEasyExportVoList, fileName);
            }
        }

        return rsBody;
    }

    private RsBody<String> postExcelExport2007(HttpServletRequest request, HttpServletResponse response, List<? extends BaseRowModel> data, String fileName) {
        RsBody rsBody = new RsBody();
        String fileDirUnique = UUID.randomUUID().toString().replace("-", ""); //临时文件夹名 确保唯一
        File file = null;
        OutputStream out = null;
        try {
            file = new File("/temp/"  + fileName + ".xlsx");
            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            //设置在程序退出时删除临时文件
            file.deleteOnExit();
            out = new FileOutputStream(file);
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            Sheet sheet1 = new Sheet(1, 0, data.get(0).getClass());
            writer.write(data, sheet1);
            writer.finish();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(null!=out){
                    out.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //组装返回前端的URL
        String realPath = "excel/postExcelExport?fileUUID=";
        String basePath =  realPath + file.getName();
        rsBody.setData(basePath);
        rsBody.setCode(Status.SUCCESS.getCode());
        rsBody.setMessage(Status.Bu_SUCCESS.getValue());
        return rsBody;

    }


    private  String generatorExcel(List<User> userList, HttpServletResponse response, HttpServletRequest request) {
        log.info("生成excel",userList);
        //创建工作簿
        XSSFWorkbook xssfWorkbook = null;
        xssfWorkbook = new XSSFWorkbook();
        //创建工作表
        XSSFSheet xssfSheet;
        xssfSheet = xssfWorkbook.createSheet();
        //创建行
        XSSFRow xssfRow;
        //创建列，即单元格Cell
        XSSFCell xssfCell;
        //表头
        xssfRow = xssfSheet.createRow(0);
        //创建每个单元格Cell，即列的数据
        xssfCell = xssfRow.createCell(0); //创建单元格
        xssfCell.setCellValue("用户名");
        //表头，第一行的第二列
        if(!CollectionUtils.isEmpty(userList)){
            xssfCell = xssfRow.createCell(1); //创建单元格
            xssfCell.setCellValue("手机号码");
        }
        //把List里面的数据写到excel中，excel从第二列开始写
        for (int i = 1; i <= userList.size(); i++) {
            //从第二行开始写入
            xssfRow = xssfSheet.createRow(i);
            //创建每个单元格Cell，即列的数据
            xssfCell = xssfRow.createCell(0); //创建第二行的第一列单元格
            // i-1为从下标为0的那条数据开始写入excel,如果从i条写入，会丢失第一条数据
            xssfCell.setCellValue(userList.get(i-1).getUsername());
            //写入手机号码
            if(!CollectionUtils.isEmpty(userList)){
                xssfCell = xssfRow.createCell(1); //创建第二行第二列单元格
                // 如果导出的手机号码为空，则设置为"-"
                if(StringUtils.isEmpty( String.valueOf(userList.get(i-1).getPhone())) ){
                    xssfCell.setCellValue("-");
                }else{
                    xssfCell.setCellValue(userList.get(i-1).getPhone());
                }
            }

        }
        //用输出流写到excel
        try {
            OutputStream outputStream = response.getOutputStream();
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + "user" + ".xlsx");
            xssfWorkbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("生成excel失败:"+e);
            e.printStackTrace();
        }

        return "生成excel文件成功";
    }

    /**
     * 查询条件
     * @param userDto
     * @return
     */
    private Wrapper<User> queryWrapper(UserDto userDto) {
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(userDto.getUsername())){
            wrapper.eq(User::getUsername,userDto.getUsername());
        }else if(userDto.getPhone()!=null){
            wrapper.eq(User::getPhone,userDto.getPhone());
        }
        //wrapper.between(User::getTime,"2021-03-17 00:00:00","2022-03-17 00:00:00");
        return wrapper;
    }




}
