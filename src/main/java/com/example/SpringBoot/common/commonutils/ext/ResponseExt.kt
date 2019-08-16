package com.example.springboot.common.commonutils.ext

import org.springframework.http.MediaType
import org.springframework.http.server.ServletServerHttpResponse
import javax.servlet.http.HttpServletResponse

/**
 * HttpServletResponse 响应返回的封装配置
 * @receiver HttpServletResponse
 * @param content String
 */
fun HttpServletResponse.resp(content:String){
    this.contentType=MediaType.APPLICATION_JSON_UTF8_VALUE
    val bytes=content.toByteArray()
    this.setContentLength(bytes.size)
    this.outputStream.write(bytes)
    this.flushBuffer()
}

fun ServletServerHttpResponse.resp(context:String){
    this.servletResponse.resp(context)
}