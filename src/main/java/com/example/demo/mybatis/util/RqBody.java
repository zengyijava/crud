package com.example.demo.mybatis.util;


import io.swagger.annotations.ApiModel;

import javax.validation.Valid;
import java.io.Serializable;



@ApiModel("请求体")
public class RqBody<T> implements Serializable, Cloneable {
    public static final long serialVersionUID = 6334971409410300128L;
    @Valid
    private T data;

    public RqBody() {
    }

    public RqBody<T> clone() {
        RqBody o = null;

        try {
            o = (RqBody)super.clone();
        } catch (CloneNotSupportedException var3) {
            var3.printStackTrace();
        }

        return o;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "RequestBody [data=" + this.data + "]";
    }
}
