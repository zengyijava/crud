package com.example.demo.mybatis.util;


public enum Status {
    MAINTENANCE("-300", "停机维护"),
    SUCCESS("1", "服务器连接成功"),
    MANAGERUSER_LOGIN_SUCCESS("1", "后台用户登录成功"),
    FAILD("-1", "服务器异常，请稍后再试"),
    Bu_SUCCESS("1", "成功"),
    BU_FAILD("-1", "失败"),
    BU_DEALING("0", "处理中"),
    ERROR("500", "系统错误"),
    INVALID("400", "请求参数出错"),
    LOGOUT("-1", "退出登录"),
    LOGIN("-2", "登录名或者密码错误"),
    LOGIN_TIMEOUT("-4", "登录超时"),
    MANAGERUSER_UNAUTHORIZED("-3", "没有权限"),
    FREQUENT("-3", "请求过于频繁，请稍后再试"),
    APP_MUST_UPDATE("-4", "app强制更新"),
    PARAM_FAIL_CODE("-5", "参数错误"),
    PUT_FORWARD_FREQUENCY("-6", "今天提现次数已达上限，请明天再试吧"),
    FILE_UPLOAD_ERROR("-7", "文件上传错误"),
    METHOD_ERROR("501", "请求方法错误"),
    SYSTEM_BUSY("502", "系统繁忙，请稍后再试！"),
    ROLE_NAME_REPEAT("-8","角色名重复！"),
    DATA_TRANSFER_ERROR("503", "数据转换异常");

    private final String name;
    private final String value;

    private Status(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /** @deprecated */
    @Deprecated
    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    /** @deprecated */
    @Deprecated
    public String getText() {
        return this.value;
    }

    public static Status getValueByName(String value) {
        Status[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Status status = var1[var3];
            if (status.value.equals(value)) {
                return status;
            }
        }

        return null;
    }
}

