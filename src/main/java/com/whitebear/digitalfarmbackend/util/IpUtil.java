package com.whitebear.digitalfarmbackend.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * IP地址工具类
 */
@Component
public class IpUtil {

    private static final String UNKNOWN = "unknown";
    private static final String[] IP_HEADERS = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR"
    };

    /**
     * 获取客户端真实IP地址
     *
     * @return IP地址
     */
    public String getClientIp() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return "";
        }

        var request = attributes.getRequest();

        // 尝试从各种HTTP头中获取真实IP
        for (String header : IP_HEADERS) {
            String ip = request.getHeader(header);
            if (isValidIp(ip)) {
                // 多次代理后会有多个IP值，第一个为真实IP
                int index = ip.indexOf(',');
                return index != -1 ? ip.substring(0, index).trim() : ip;
            }
        }

        // 如果没有通过代理，则直接获取IP
        return request.getRemoteAddr();
    }

    /**
     * 判断IP是否有效
     *
     * @param ip IP地址
     * @return 是否有效
     */
    private boolean isValidIp(String ip) {
        return ip != null && ip.length() > 0 && !UNKNOWN.equalsIgnoreCase(ip);
    }
}
