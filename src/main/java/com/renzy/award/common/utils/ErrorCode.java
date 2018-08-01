package com.renzy.award.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;
import java.util.Objects;

/**
 * @author renzhiyong
 * @description:
 * @date 2018-03-29 16:30
 */
public class ErrorCode {

    private static final Logger log = LoggerFactory.getLogger(ErrorCode.class);

    private static final String ERROR_MSG_FORMAT = "error.%s";

    private static AbstractMessageSource messageSource = null;

    // system error code 0 ~ 10
    public static final String SYS_SUCCESS = "0";

    public static final String SYS_ERROR = String.format("%s%s", "034", "10001");
    public static final String SYS_REQ_ERROR = String.format("%s%s", "034", "10002");
    public static final String SYS_FREQUENCY_OPERATION = String.format("%s%s", "034", "10003");
    public static final String SYS_PRIVILEGE_ERROR = String.format("%s%s", "034", "10004");
    public static final String SYS_OPER_ERROR = String.format("%s%s", "034", "10005");
    public static final String SYS_NOT_SUPPORT_METHOD = String.format("%s%s", "034", "10006");
    public static final String SYS_REMOTE_CALL_FAILED = String.format("%s%s", "034", "10007");


    public static String getErrorMsg(String errorCode, Objects[] objectses, Locale locale, ReloadableResourceBundleMessageSource messageSource) {

        if (messageSource == null) {
            log.error("==> getErrorMsg can not get message because message bundle source is null.");
            return String.format("errorCode = %d , get message source fail.", errorCode);
        }

        String message = messageSource.getMessage(String.format(ERROR_MSG_FORMAT, errorCode), objectses, locale == null ? Locale.CHINA : locale);

        if (message == null) {
            message = String.format("errorCode = %d , get message source fail . ", errorCode);
        }
        return message;
    }


    public static String getErrorMsg(String errorCode, ReloadableResourceBundleMessageSource messageSource) {
        return getErrorMsg(errorCode, null, null, messageSource);
    }

    public static void setMessageSource(AbstractMessageSource messageSource) {
        ErrorCode.messageSource = messageSource;
    }
}