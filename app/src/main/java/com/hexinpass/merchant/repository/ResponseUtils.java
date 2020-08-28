package com.hexinpass.merchant.repository;

/**
 * 功能描述: HTTP相应错误码及解析
 */
public class ResponseUtils {

    public interface ResponseConstants {
        int CODE_0 = 0;
        String MSG_0 = "系统错误,请重试!";
    }

    public static String judgeStatus(int errorCode) {
        String desc = "加载失败,请稍后重试";
        switch (errorCode) {
            case ResponseConstants.CODE_0:
                desc = ResponseConstants.MSG_0;
                break;
        }
        return desc;
    }

}