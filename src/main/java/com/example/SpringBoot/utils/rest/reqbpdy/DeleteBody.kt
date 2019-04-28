package com.waytogalaxy.display.utils.rest.reqbpdy

import com.waytogalaxy.display.common.config.cacheConfig.CacheObj
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate

class DeleteBody(url:String, restTemplate: RestTemplate,cacheObj: CacheObj): ReqBody(url,restTemplate,cacheObj){

    override fun httpMethod() = HttpMethod.DELETE

}