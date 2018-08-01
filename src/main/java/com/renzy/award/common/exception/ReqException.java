package com.renzy.award.common.exception;


import com.renzy.award.common.utils.ErrorCode;

/**
 * @author renzhiyong
 * @description:
 * @date 2018-03-29 16:30
 */
public class ReqException extends RuntimeException {

    private String errorCode;
    private String message;

    public ReqException() {
        super();
    }

    public ReqException(String message) {
        this.errorCode = ErrorCode.SYS_REQ_ERROR;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
