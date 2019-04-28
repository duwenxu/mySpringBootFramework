package com.waytogalaxy.display.utils.rest

class ReqResult<T>(val success:Boolean, val t:T?, val errorCode: Int?, val errorResp:String?)

