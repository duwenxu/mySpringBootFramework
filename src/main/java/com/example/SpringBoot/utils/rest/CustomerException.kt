package com.waytogalaxy.display.utils.rest

import org.springframework.web.client.RestClientException

class CustomerException(val restClientException: RestClientException,val body:String,msg:String):RestClientException(msg)