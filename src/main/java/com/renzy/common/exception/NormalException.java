package com.renzy.common.exception;


import com.renzy.common.utils.ErrorCode;

/**
 * @author renzhiyong
 * @description:
 * @date 2018-03-29 16:30
 */
public class NormalException extends RuntimeException {

    private String errorCode;
    private String message;

    public NormalException() {
        super();
    }

    public NormalException(String message) {
        this.errorCode = ErrorCode.SYS_OPER_ERROR;
        this.message = message;
    }

    public NormalException(String errorCode, String message) {
        this.errorCode = errorCode;
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
