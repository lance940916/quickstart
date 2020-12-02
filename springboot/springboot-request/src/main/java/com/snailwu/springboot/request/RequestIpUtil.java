package com.snailwu.springboot.request;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * @author 吴庆龙
 * @date 2020/10/19 3:50 下午
 */
public class RequestIpUtil {

    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST = "127.0.0.1";
    private static final String SEPARATOR = ",";

    /**
     * 获取客户端的IP地址
     */
    public static String ipAddress(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");

            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }

            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }

            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteHost();

                // 本地请求
                if (LOCALHOST.equals(ipAddress)) {
                    ipAddress = InetAddress.getLocalHost().getHostAddress();
                }
            }

            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            // "***.***.***.***".length()
            if (ipAddress != null && ipAddress.length() > 15) {
                int index = ipAddress.indexOf(SEPARATOR);
                if (index > 0) {
                    ipAddress = ipAddress.substring(0, index);
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

}
