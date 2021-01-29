package org.starrier.common.utils.request;

import com.google.common.collect.Maps;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.starrier.common.constant.HttpConstant.HTTP_CLIENT_IP;
import static org.starrier.common.constant.HttpConstant.HTTP_X_FORWARDED_FOR;
import static org.starrier.common.constant.HttpConstant.POINT;
import static org.starrier.common.constant.HttpConstant.PROXY_CLIENT_IP;
import static org.starrier.common.constant.HttpConstant.UNKNOWN;
import static org.starrier.common.constant.HttpConstant.WL_PROXY_CLIENT_IP;
import static org.starrier.common.constant.HttpConstant.X_FORWARDED_FOR;

/**
 * @author Starrier
 * @date 018/10/31.
 */
public class HttpUtils {

    /**
     * 尝试获取当前请求 HttpServletRequest 实例
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        return Optional.of(((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest()).orElse(null);
    }

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Enumeration<String> enumeration = request.getHeaderNames();
        Map<String, String> map = Maps.newLinkedHashMap();
        while (enumeration.hasMoreElements()) {
            map.put(enumeration.nextElement(), request.getHeader(enumeration.nextElement()));
        }
        return map;
    }

    /**
     * 获取请求客户端的真实 IP 地址
     *
     * @return IP 地址
     */
    public static String getIpAddress() {
        return getIpAddress(getHttpServletRequest());
    }

    /**
     * 获取请求客户端的真实ip地址,如果通过代理进来，则透过防火墙获取真实IP地址
     *
     * @param request 请求对象
     * @return ip地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader(X_FORWARDED_FOR);
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader(PROXY_CLIENT_IP);
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader(WL_PROXY_CLIENT_IP);
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader(HTTP_CLIENT_IP);
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader(HTTP_X_FORWARDED_FOR);
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = POINT.split(ip);
            for (String s : ips) {
                if (!(s.equalsIgnoreCase(UNKNOWN))) {
                    ip = s;
                    break;
                }
            }
        }
        return ip;
    }
}
