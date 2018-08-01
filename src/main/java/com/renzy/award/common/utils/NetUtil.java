/*
package com.renzy.award.common.utils;

import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

*/
/**
 * tcp/ip协议中，专门保留了三个IP地址区域作为私有地址，其地址范围如下：
 * A类 10.0.0.0 - 10.255.255.255
 * B类 172.16.0.0 - 172.31.255.255
 * C类 192.168.0.0 - 192.168.255.255
 * 环回地址：127.0.0.1
 *
 * @author renzhiyong
 * @description:
 * @date 2018-03-29 16:30
 *//*

public class NetUtil {

    private static final String PATTERN_STR_255 = "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)";

    */
/**
     * IPv4
     *//*

    private static final Pattern PATTERN_IP = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

    */
/**
     * A类：10.0.0.0 - 10.255.255.255
     *//*

    private static final Pattern PATTERN_INNER_IP_A = Pattern.compile("10(\\." + PATTERN_STR_255 + "){3}");

    */
/**
     * B类：172.16.0.0 - 172.31.255.255
     *//*

    private static final Pattern PATTERN_INNER_IP_B = Pattern.compile("172\\.(3[01]|2\\d|1[6-9])(\\." + PATTERN_STR_255 + "){2}");

    */
/**
     * C类：192.168.0.0 - 192.168.255.255
     *//*

    private static final Pattern PATTERN_INNER_IP_C = Pattern.compile("192\\.168(\\." + PATTERN_STR_255 + "){2}");

    */
/**
     * 环回地址：127.0.0.1
     *//*

    private static final String LOOPBACK_IP = "127.0.0.1";


    */
/**
     * 获取第一个读取到的公网ip
     *
     * @return
     *//*

    public static String getFirstExternalIp() throws SocketException {
        List<InetAddress> addresses = getNonLoopbackIPv4Address();

        for (InetAddress address : addresses) {
            String hostAddress = address.getHostAddress();

            if (!isInnerIp(hostAddress)) {
                return hostAddress;
            }
        }

        return null;
    }

    */
/**
     * 获取第一个读取到的内网ip
     *
     * @return
     *//*

    public static String getFirstInnerIp() throws SocketException {
        List<InetAddress> addresses = getNonLoopbackIPv4Address();

        for (InetAddress address : addresses) {
            String hostAddress = address.getHostAddress();

            if (isInnerIp(hostAddress)) {
                return hostAddress;
            }
        }

        return null;
    }

    */
/**
     * 是否为内网ip
     *
     * @param ip
     * @return
     *//*

    public static boolean isInnerIp(String ip) {
        if (StringUtils.isEmpty(ip)) {
            return false;
        }

        if (PATTERN_INNER_IP_A.matcher(ip).matches()) {
            return true;
        }

        if (PATTERN_INNER_IP_B.matcher(ip).matches()) {
            return true;
        }

        if (PATTERN_INNER_IP_C.matcher(ip).matches()) {
            return true;
        }

        if (LOOPBACK_IP.equals(ip)) {
            return true;
        }

        return false;
    }

    */
/**
     * 判断是否为合法ip
     *
     * @param ip
     * @return
     *//*

    public static boolean isIp(String ip) {
        return PATTERN_IP.matcher(ip).matches();
    }

    */
/**
     * 获取所有网卡地址（不含环回地址）
     *
     * @return
     * @throws SocketException
     *//*

    public static List<InetAddress> getNonLoopbackIPv4Address() throws SocketException {
        List<InetAddress> addresses = new ArrayList<>();

        Enumeration en = NetworkInterface.getNetworkInterfaces();
        while (en.hasMoreElements()) {
            NetworkInterface i = (NetworkInterface) en.nextElement();
            for (Enumeration en2 = i.getInetAddresses(); en2.hasMoreElements(); ) {
                InetAddress addr = (InetAddress) en2.nextElement();

                if (!addr.isLoopbackAddress()) {
                    if (addr instanceof Inet4Address) {
                        addresses.add(addr);
                    }
                }
            }
        }

        return addresses;
    }

    */
/**
     * 获取url中的域名
     *
     * @param httpServletRequest
     * @return
     *//*

    public static String getDomain(HttpServletRequest httpServletRequest) {
        String url = httpServletRequest.getRequestURL().toString();

        int begin = "http://".length();
        int end = url.indexOf("/", begin);

        String domain = url.substring(begin, end);

        if (domain.contains(":")) {
            domain = domain.substring(0, domain.indexOf(":"));
        }

        return domain;
    }

    */
/**
     * 解析HttpServletRequest中获取到的参数并将其格式化为可读性较强的格式
     *
     * @param request
     * @return
     *//*

    public static String formatRequestParams(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();

        JsonObject json = new JsonObject();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            String key = entry.getKey();
            String[] multiValues = entry.getValue();

            for (String value : multiValues) {
                json.addProperty(key, value);
            }
        }

        return json.toString();
    }
}
*/
