package com.renzy.common.exception;


import com.renzy.common.utils.ErrorCode;

/**
 * @author renzhiyong
 * @description:
 * @date 2018-03-29 16:30
 */
public class ReqException extends NormalException {

    private String errorCode;
    private String message;

    public ReqException() {
        super();
    }

    public ReqException(String message) {
        this.errorCode = ErrorCode.SYS_REQ_EXACT_ERROR;
        this.message = message;
    }
    @Override
    public String getErrorCode() {
        return errorCode;
    }
    @Override
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
