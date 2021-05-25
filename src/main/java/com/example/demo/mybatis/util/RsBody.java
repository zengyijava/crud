package com.example.demo.mybatis.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@ApiModel
@Accessors(chain = true)
@Data
public class RsBody<T> implements Serializable, Cloneable {
    public static final long serialVersionUID = 6334971409410300128L;
    private T data;
    @ApiModelProperty("业务处理码")
    private String code = "1";
    @ApiModelProperty("业务处理消息")
    private String message = "成功";

    public RsBody<T> clone() {
        RsBody o = null;

        try {
            o = (RsBody)super.clone();
        } catch (CloneNotSupportedException var3) {
            var3.printStackTrace();
        }

        return o;
    }


    public RsBody() {
    }

    public RsBody(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return Status.Bu_SUCCESS.getCode().equals(this.code);
    }

    /** @deprecated */
    @Deprecated
    public RsBody(Status status) {
        this.code = status.getName();
        this.message = status.getValue();
    }

    /** @deprecated */
    @Deprecated
    public RsBody(T data, Status status) {
        this.data = data;
        this.code = status.getName();
        this.message = status.getValue();
    }


    public RsBody(T data, String code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    /** @deprecated */
    @Deprecated
    public RsBody(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public RsBody<T> setBody(Boolean flag) {
        if (flag) {
            this.setCode(Status.Bu_SUCCESS.getCode());
            this.setMessage(Status.Bu_SUCCESS.getValue());
        } else {
            this.setCode(Status.BU_FAILD.getCode());
            this.setMessage(Status.BU_FAILD.getValue());
        }
        return this;
    }

    public RsBody<T> setDataBody(T t) {
        this.setData(t);
        this.setCode(Status.Bu_SUCCESS.getCode());
        this.setMessage(Status.Bu_SUCCESS.getValue());
        return this;
    }

    public String toString() {
        return "ResponseBody [data=" + this.data + ",code=" + this.code + ",message=" + this.message + "]";
    }
}
