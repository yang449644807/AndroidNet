package com.hexinpass.merchant.exception;


import com.hexinpass.merchant.repository.ResponseUtils;

public class ApiException extends RuntimeException {

    private int errorCode;

    public ApiException(int errorCode) {
        super(ResponseUtils.judgeStatus(errorCode));
        this.errorCode = errorCode;
    }

    public ApiException(int errorCode, String msg) {
        super(msg + "("+ errorCode +")");
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }


}