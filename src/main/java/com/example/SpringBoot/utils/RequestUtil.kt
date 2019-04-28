package com.waytogalaxy.display.utils

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

import javax.servlet.http.HttpServletRequest
import java.util.HashMap

/**
 * @author rankai
 * createTime 2017-09-2017/9/26 9:25
 */
object RequestUtil {

    /**
     * 获取HttpServletRequest,需要在web.xml中配置
     *
     * @return
     */
    val request: HttpServletRequest
        get() = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request

    /**
     * 获得当前工程全路径
     * D:\Tomcat 6.0\webapps\test\
     *
     * @return
     */
    val realPath: String
        get() = getRealPath("")

    /**
     * 获得当前工程全路径+频率拼接路径
     * http://localhost:8080/test/
     *
     * @return
     */
    val urlPath: String
        get() {
            val request = request
            return request.scheme + "://" + request.serverName + ":" + request.serverPort + "/"
        }


    /**
     * 获取客户端IP地址.<br></br>
     * 支持多级反向代理
     *
     * @param request HttpServletRequest
     * @return 客户端真实IP地址
     */
    fun getRemoteAddr(request: HttpServletRequest): String {
        try {
            var remoteAddr = request.getHeader("X-Forwarded-For")
            // 如果通过多级反向代理，X-Forwarded-For的值不止一个，而是一串用逗号分隔的IP值，此时取X-Forwarded-For中第一个非unknown的有效IP字符串
            if (isEffective(remoteAddr) && remoteAddr.indexOf(",") > -1) {
                val array = remoteAddr.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                for (element in array) {
                    if (isEffective(element)) {
                        remoteAddr = element
                        break
                    }
                }
            }
            if (!isEffective(remoteAddr)) {
                remoteAddr = request.getHeader("X-Real-IP")
            }
            if (!isEffective(remoteAddr)) {
                remoteAddr = request.remoteAddr
            }
            return remoteAddr
        } catch (e: Exception) {
            return ""
        }

    }

    /**
     * 获取客户端源端口
     *
     * @param request HttpServletRequest
     * @return 客户端真实端口号
     */
    fun getRemotePort(request: HttpServletRequest): Long {
        try {
            val port = request.getHeader("remote-port")
            //字符串不为空
            return if (port != null) {
                try {
                    java.lang.Long.parseLong(port)
                } catch (ex: NumberFormatException) {
                    0L
                }

            } else {
                0L
            }
        } catch (e: Exception) {
            return 0L
        }

    }

    /**
     * 远程地址是否有效.
     *
     * @param remoteAddr 远程地址
     * @return true代表远程地址有效，false代表远程地址无效
     */
    private fun isEffective(remoteAddr: String?): Boolean {
        var isEffective = false
        if (null != remoteAddr && "" != remoteAddr.trim { it <= ' ' }
                && !"unknown".equals(remoteAddr.trim { it <= ' ' }, ignoreCase = true)) {
            isEffective = true
        }
        return isEffective
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     *
     * 用户真实IP为： 192.168.1.110
     *
     * @param request HttpServletRequest
     * @return 客户端真实IP地址
     */
    fun getIpAddress(request: HttpServletRequest): String?{
        var ip: String? = request.getHeader("x-forwarded-for")
        if (ip == null || ip.length == 0 || "unknown".equals(ip, ignoreCase = true)) {
            ip = request.getHeader("Proxy-Client-IP")
        }
        if (ip == null || ip.length == 0 || "unknown".equals(ip, ignoreCase = true)) {
            ip = request.getHeader("WL-Proxy-Client-IP")
        }
        if (ip == null || ip.length == 0 || "unknown".equals(ip, ignoreCase = true)) {
            ip = request.getHeader("HTTP_CLIENT_IP")
        }
        if (ip == null || ip.length == 0 || "unknown".equals(ip, ignoreCase = true)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR")
        }
        if (ip == null || ip.length == 0 || "unknown".equals(ip, ignoreCase = true)) {
            ip = request.remoteAddr
        }
        return ip
    }

    /**
     * 获得当前工程全路径+拼接路径
     * D:\Tomcat 6.0\webapps\test\
     *
     * @param path
     * @return
     */
    fun getRealPath(path: String): String {
        return request.session.servletContext.getRealPath("/") + path
    }

}
