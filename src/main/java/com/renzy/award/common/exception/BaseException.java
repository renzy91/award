package com.renzy.award.common.exception;

/**
 * @author renzhiyong
 * @description:
 * @date 2018-03-29 16:30
 */
public class BaseException extends RuntimeException {

    private String errorCode;
    private String message;

    public BaseException() {
        super();
    }

    public BaseException(String errorCode) {
        this.errorCode = errorCode;
    }

    public BaseException(String errorCode, String message) {
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
