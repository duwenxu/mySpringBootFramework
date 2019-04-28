package com.waytogalaxy.display.utils.rest

import com.waytogalaxy.display.common.config.cacheConfig.CacheObj
import com.waytogalaxy.display.common.result.ResultEnum
import com.waytogalaxy.display.exts.cutAt
import com.waytogalaxy.display.exts.toJson
import com.waytogalaxy.display.utils.rest.reqbpdy.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import java.lang.Exception

@Component
class HttpUtil {

    /**
     * 给静态类实例添加restTemplate 对象
     * @param restT restTemplate对象
     */
    @Autowired
    fun setRestTemplate(restT: RestTemplate) {
        restTemplate = restT
        restTemplate.errorHandler = CustomRespErrHandler()
    }

    /**
     * 给静态实例添加cacheObj对象
     * @param cacheObj 缓存类
     */
    @Autowired
    fun setCacheObj(cacheObj: CacheObj) {
        HttpUtil.cacheObj = cacheObj
    }


    companion object {

        lateinit var restTemplate: RestTemplate

        lateinit var cacheObj: CacheObj

        fun post(url: String) = PostBody(url, restTemplate, cacheObj)

        fun get(url: String) = GetBody(url, restTemplate, cacheObj)

        fun delete(url: String) = DeleteBody(url, restTemplate, cacheObj)

        fun put(url: String) = PutBody(url, restTemplate, cacheObj)

    }


}


class ReqExec<T>(val reqBody: ReqBody, val clazz: Class<T>) {

    /**
     * 执行请求方法
     */
    fun exec(): ReqResult<T> {
//        System.setProperty("http.proxyHost", "127.0.0.1")
//        System.setProperty("https.proxyHost", "127.0.0.1")
//        System.setProperty("http.proxyPort", "8888")
//        System.setProperty("https.proxyPort", "8888")
        val restTemplate = reqBody.restTemplate
        val url = reqBody.url
        val httpMethod = reqBody.httpMethod()
        if (reqBody.ifHalfErrorUrl() && reqBody.cacheObj.errorUrlCache.get(keyFromObj(reqBody)) != null) {  //如果配置了请求出错休息，并且有错误缓存不再请求
            return ReqResult(false, null, ResultEnum.SERVER_ERROR.code, "网络请求出错")
        }
        try {
            val respEntity = when (httpMethod) {
                HttpMethod.POST -> restTemplate.postForEntity(url, reqBody.httpEntity, clazz)
                HttpMethod.GET -> restTemplate.getForEntity(url, clazz, reqBody.paramMap)
                HttpMethod.PUT -> restTemplate.putForEntity(url, reqBody.httpEntity, clazz)
                HttpMethod.DELETE -> restTemplate.deleteForEntity(url, reqBody.httpEntity, clazz)
                else -> null
            } ?: return reqErrProcess(reqBody, "未知异常")

            return if (respEntity.statusCode == HttpStatus.OK) {
                ReqResult(true, respEntity.body, null, null)
            } else {
                reqErrProcess(reqBody, "请求失败 -> ${respEntity.statusCode.reasonPhrase}")
            }
        } catch (ex: CustomerException) {
            ex.printStackTrace()
            return reqErrProcess(reqBody, "请求失败 -> ${ex.message}  在请求 ${reqBody.url}时")
        } catch (ex: Exception) {
            ex.printStackTrace()
            return reqErrProcess(reqBody, "请求失败 -> ${ex.message}  在请求 ${reqBody.url}时")
        }

    }

    /**
     * 请求出错处理
     * @param reqBody 请求实体
     * @param msg 错误信息
     */
    private fun reqErrProcess(reqBody: ReqBody, msg: String): ReqResult<T> {
        if (reqBody.ifHalfErrorUrl()) {
            reqBody.cacheObj.errorUrlCache.put(keyFromObj(reqBody), true)
        }
        return ReqResult(false, null, ResultEnum.SERVER_ERROR.code, msg)
    }

    /**
     * 获取请求实体生成的key
     * @param reqBody 请求实体
     */
    private fun keyFromObj(reqBody: ReqBody): String {
        val hash = reqBody.httpEntity?.toJson()?.hashCode() ?: "NOHASH"
        val key = when (reqBody.httpMethod()) {
            HttpMethod.GET -> "GET:${reqBody.url}"
            HttpMethod.POST -> "POST:$hash:${reqBody.url}:${reqBody.httpEntity?.toJson() ?: ""}".cutAt(100)
            HttpMethod.PUT -> "PUT:$hash:${reqBody.url}:${reqBody.httpEntity?.toJson() ?: ""}".cutAt(100)
            HttpMethod.DELETE -> "DELETE:$hash:${reqBody.url}:${reqBody.httpEntity?.toJson() ?: ""}".cutAt(100)
            else -> "OTHER:$hash:${reqBody.url}:${reqBody.httpEntity?.toJson() ?: ""}".cutAt(100)
        }
        return key
    }
}

/**
 * 对req put方法的扩展,返回请求结果
 */
fun <T> RestTemplate.putForEntity(url: String, httpEntity: HttpEntity<*>?, clazz: Class<T>) =
        this.exchange(url, HttpMethod.PUT, httpEntity, clazz)

/**
 * 对req delete方法的扩展,返回请求结果
 */
fun <T> RestTemplate.deleteForEntity(url: String, httpEntity: HttpEntity<*>?, clazz: Class<T>) =
        this.exchange(url, HttpMethod.DELETE, httpEntity, clazz)
