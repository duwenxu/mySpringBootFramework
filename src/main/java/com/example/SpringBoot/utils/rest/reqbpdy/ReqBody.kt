package com.waytogalaxy.display.utils.rest.reqbpdy

import com.fasterxml.jackson.databind.ObjectMapper
import com.waytogalaxy.display.common.config.cacheConfig.CacheObj
import com.waytogalaxy.display.utils.rest.ReqExec
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

abstract class ReqBody(
        /**
         * 请求的url
         */
        var url: String,
        /**
         * 调用请求使用的 RestTemplate
         */
        val restTemplate: RestTemplate,
        /**
         * @see halfErrorUrl 使用的缓存类
         */
        val cacheObj: CacheObj) {

    /**
     * 请求的方法
     */
    abstract fun httpMethod(): HttpMethod

    /**
     * 参数map
     */
    val paramMap = LinkedMultiValueMap<String, Any?>()

    /**
     * 如果设置为true ，在请求出错的时候保存错误信息，在一段时间内不再请求
     * @see CacheObj.errorUrlCache
     */
    private var halfErrorUrl = false

    /**
     * 请求headers
     */
    private val headers = HttpHeaders()

    private var initedContentType = false

    var jsonBody:String? = null

    /**
     * 如果设置了contentType 将会替换掉默认的contentType
     * @see returnClass
     */
    fun withContentType(contentType: MediaType): ReqBody {
        headers.contentType = contentType
        initedContentType = true
        return this
    }


    var httpEntity: HttpEntity<*>? = null

    /**
     * 添加请求header
     * @param key header key
     * @param value header value
     */
    fun withHeader(key: String, value: String): ReqBody {
        headers[key] = value
        return this
    }

    /**
     * 添加请求参数
     * @param key 请求key
     * @param value 请求value
     */
    fun withParam(key: String, value: String?): ReqBody {
        paramMap[key] = value
        return this
    }

    fun withJsonBody(jsonBody:String):ReqBody {
        this.jsonBody = jsonBody
        return this
    }


    fun ifHalfErrorUrl() = halfErrorUrl

    /**
     * 如果请求出错，不会连续请求，等待一段时间再次请求
     * @see ReqExec.exec
     * @see CacheObj.errorUrlCache
     */
    fun halfErrorUrl(): ReqBody {
        this.halfErrorUrl = true
        return this
    }

    /**
     * 请求返回的类型,应该在 请求实体拼装完成后调用,之后调用
     * @see ReqExec.exec() 同步执行
     */
    fun <T> returnClass(clazz: Class<T>): ReqExec<T> {
        if (!initedContentType) {
            headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        }
        if (headers.contentType!!.equals( MediaType.APPLICATION_JSON_UTF8) && jsonBody != null){
            httpEntity = HttpEntity(jsonBody,headers)
        }else {
            httpEntity = HttpEntity(paramMap, headers)
        }
        return ReqExec(this, clazz)
    }



}
