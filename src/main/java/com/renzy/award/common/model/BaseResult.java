package com.renzy.award.common.model;

import com.renzy.award.common.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * @author renzhiyong
 * @description:
 * @date 2018-03-29 16:30
 */
@Component
public class BaseResult {
    @Autowired
    private static ReloadableResourceBundleMessageSource messageSource;

    /**
     * 请求参数错误
     *
     * @param msg
     * @return
     */
    public static <T> BaseResponse<T> badRequest(String msg) {
        return new BaseResponse<>(ErrorCode.SYS_REQ_ERROR, msg);
    }

    /**
     * 没有权限
     *
     * @return
     */
    public static <T> BaseResponse<T> unauthorized() {
        return getErrorResponse(ErrorCode.SYS_PRIVILEGE_ERROR);
    }

    /**
     * 请求成功
     *
     * @param content
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> ok(T content) {
        return new BaseResponse<>(ErrorCode.SYS_SUCCESS, ErrorCode.getErrorMsg(ErrorCode.SYS_SUCCESS, messageSource), content);
    }

    /**
     * 操作失败
     *
     * @return
     */
    public static <T> BaseResponse<T> operatError() {
        return getErrorResponse(ErrorCode.SYS_OPER_ERROR);
    }

    /**
     * 操作失败
     *
     * @param msg
     * @return
     */
    public static <T> BaseResponse<T> operatError(String msg) {
        return new BaseResponse<>(ErrorCode.SYS_OPER_ERROR, msg);
    }

    /**
     * 远程调用失败
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> remoteFail() {
        return getErrorResponse(ErrorCode.SYS_REMOTE_CALL_FAILED);
    }

    /**
     * 获取详细信息
     *
     * @param errorCode
     * @return
     */
    public static <T> BaseResponse<T> getErrorResponse(String errorCode) {
        return new BaseResponse<>(errorCode, ErrorCode.getErrorMsg(errorCode, messageSource));
    }

    /*@Autowired
    public void setMessageSource(ReloadableResourceBundleMessageSource messageSource) {
        BaseResult.messageSource = messageSource;
    }*/
}
