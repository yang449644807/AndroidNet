package com.hexinpass.merchant.mvp.bean;

import com.google.gson.annotations.SerializedName;

public class BaseBean<T> {

    @SerializedName("cmd")
    public int cmd;

    /**
     * 错误码
     */
    @SerializedName("code")
    public int errorCode;

    /**
     * 错误描述
     */
    @SerializedName("msg")
    public String error;

    /**
     * 返回数据
     */
    @SerializedName("data")
    public T data;

}
