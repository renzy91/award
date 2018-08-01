package com.renzy.award.common.utils;

import com.renzy.award.common.context.Constants;
import com.renzy.award.common.model.BaseResponse;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.servlet.http.HttpServletRequest;

/**
 * @author renzhiyong
 * @description:
 * @date 2018-03-29 16:30
 */
public class Utility {

    // 代理头域名称
    private static final String[] PROXY_REMOTE_IP_ADDRESS = {"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP"};

    private static final String UNKNOWN = "unknown";

    /**
     * return right response , status is 0 , message is success .
     *
     * @param content
     * @return
     */
    public static <T> BaseResponse<T> getRightResponse(T content) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setCode("0");
        response.setMsg(Constants.SUCCESS);
        response.setData(content);
        return response;
    }

    /**
     * return right response , status is 0 , message of specified .
     *
     * @param message
     * @param content
     * @return
     */
    public static <T> BaseResponse<T> getRightResponse(String message, T content) {
        BaseResponse<T> response = new BaseResponse();
        response.setCode("0");
        response.setMsg(message);
        response.setData(content);
        return response;
    }

    /**
     * return error response , status is errorCode specified , message is correspond to errorCode bundled .
     *
     * @param errorCode
     * @param messageSource
     * @return
     */
    public static <T> BaseResponse<T> getErrorResponse(String errorCode, ReloadableResourceBundleMessageSource messageSource) {
        BaseResponse<T> response = new BaseResponse();
        response.setCode(errorCode);
        response.setMsg(ErrorCode.getErrorMsg(errorCode, messageSource));
        return response;
    }

    /**
     * return error response , status is errorCode specified , message is correspond to errorCode bundled .
     *
     * @param errorCode
     * @param message
     * @return
     */
    public static <T> BaseResponse<T> getErrorResponse(String errorCode, String message) {
        BaseResponse<T> response = new BaseResponse();
        response.setCode(errorCode);
        response.setMsg(message);
        return response;
    }

    /**
     * 获取客户端地址
     *
     * @param request
     * @return
     */
    public static String getRemoteIP(HttpServletRequest request) {
        for (int i = 0; i < PROXY_REMOTE_IP_ADDRESS.length; i++) {
            String ip = request.getHeader(PROXY_REMOTE_IP_ADDRESS[i]);
            if (StringUtil.isNotBlank(ip) && !ip.trim().equalsIgnoreCase(UNKNOWN)) {
                return getRemoteIpFromForward(ip.trim());
            }
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取多个地址中的第一个地址
     *
     * @param xforwardIp
     * @return
     */
    private static String getRemoteIpFromForward(String xforwardIp) {
        int commaOffset = xforwardIp.indexOf(',');
        if (commaOffset < 0) {
            return xforwardIp;
        }
        return xforwardIp.substring(0, commaOffset);
    }
}
