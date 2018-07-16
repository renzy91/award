/**
 * Project Name: feature-20170427-optimize
 * File Name: AccessLogUtils.java
 * Package Name: com.huifenqi.saas.utils
 * Date: 2017年4月27日下午3:14:43
 * Copyright (c) 2017, www.huizhaofang.com All Rights Reserved.
 *//*

package com.renzy.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.SocketException;
import java.util.Enumeration;

*/
/**
 * ClassName: AccessLogUtils
 * date: 2017年4月27日 下午3:14:43
 * Description:
 *
 * @author xiaozhan
 * @since JDK 1.8
 *//*

public class AccessLogUtils {

    private static Log logger = LogFactory.getLog(AccessLogUtils.class);

    */
/**
     * 输出访问日志
     *
     * @param request
     * @param responses
     *//*

    public static void printAccessLog(HttpServletRequest request, HfqResponse responses) {
        String srcIp = request.getHeader("X-Real-IP");
        String dstIp = "";
        try {
            dstIp = NetUtil.getFirstExternalIp();
        } catch (SocketException e) {
        }

        String uri = request.getRequestURI();
        Enumeration<String> parameters = request.getParameterNames();
        Cookie[] cookies = request.getCookies();
        String deviceInfo = request.getHeader("device-info");

        StringBuilder paramBuilder = new StringBuilder();
        if (parameters != null) {
            while (parameters.hasMoreElements()) {
                String name = parameters.nextElement();
                paramBuilder.append(name).append("=").append(request.getParameter(name).replaceAll("\r|\n", "")).append("&");
            }
        }
        //替换掉最后一个&字符
        if (paramBuilder.length() > 0) {
            paramBuilder.deleteCharAt(paramBuilder.length() - 1);
        }

        StringBuilder cookiesBuilder = new StringBuilder();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                cookiesBuilder.append(name).append("=").append(value).append("&");
            }
        }
        //替换掉最后一个&字符
        if (cookiesBuilder.length() > 0) {
            cookiesBuilder.deleteCharAt(cookiesBuilder.length() - 1);
        }

        if (deviceInfo != null && deviceInfo.length() > 0) {
            deviceInfo = deviceInfo.replaceAll(";", "&");
        }

        logger.info(String.format("%s|%s|%s|%s|%s|%s|%s|%s",
                srcIp,
                dstIp,
                uri,
                paramBuilder.toString(),
                cookiesBuilder.toString(),
                deviceInfo,
                responses.getStatus().getCode(),
                responses.getStatus().getDescription()));
    }
}
*/
