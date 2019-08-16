package com.example.springboot.common.result

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.example.springboot.common.commonutils.ext.toJson
import java.io.Serializable
import java.util.*

/**
 * 返回结果
 *
 * @author rankai
 * createTime 2017-04-2017/4/20 14:47
 */
class ResultTo : HashMap<String, Any>, Serializable {

    constructor() {
        this[RESULT_CODE] = ResultEnum.SUCCESS.code
        this[RESULT_MESSAGE] = ResultEnum.SUCCESS.message
    }

    constructor(any: Any) {
        if (any is ResultEnum) {
            this[RESULT_CODE] = any.code
            this[RESULT_MESSAGE] = any.message
        } else {
            this[RESULT_CODE] = ResultEnum.SUCCESS.code
            this[RESULT_MESSAGE] = ResultEnum.SUCCESS.message
            this[RESULT_DATA] = any
        }
    }


    constructor(code: Int, message: String) {
        this[RESULT_CODE] = code
        this[RESULT_MESSAGE] = message
    }

    constructor(resultEnum: ResultEnum, message: String?) {
        this[RESULT_CODE] = resultEnum.code
        message?.let { this[RESULT_MESSAGE] = message }
    }

    constructor(resultEnum: ResultEnum, message: String?, errorUrl: String) {
        this[RESULT_CODE] = resultEnum.code
        message?.let { this[RESULT_MESSAGE] = message }
        this["errorUrl"] = errorUrl
    }


    fun code() = this[RESULT_CODE] as Int?

    fun message() = this[RESULT_MESSAGE] as String?

    fun data() = this[RESULT_DATA]

    fun <T> dataArray(clazz: Class<T>): List<T>? {
        val data = data()
        return if (data is JSONArray) {
            JSONArray.parseArray(data.toString(), clazz)
        } else {
            null
        }
    }

    fun <T> data(clazz: Class<T>): T? {
        val data = data()
        return if (data is JSONObject) {
            JSONObject.parseObject(data.toString(), clazz)
        } else {
            null
        }

    }


    fun setErrorUrl(url: String): ResultTo {
        this["errorUrl"] = url
        return this
    }

    fun setData(`object`: Any?): ResultTo {
        `object`?.let { this[RESULT_DATA] = it }
        return this
    }

    fun setData(dataName: String, `object`: Any?): ResultTo {
        this.put(dataName, `object` ?: "")
        return this
    }

    fun setData(map: Map<String, Any>): ResultTo {
        this.putAll(map)
        return this
    }

    fun setPerms(`object`: Any) {
        this[RESULT_PERMS] = `object`
    }

    fun toJsonStr() = this.toJson()


    companion object {

        private const val serialVersionUID = -6125210369527938613L

        // 返回结果编码
        val RESULT_CODE = "code"
        // 返回结果信息
        val RESULT_MESSAGE = "message"
        // 返回结果数据
        val RESULT_DATA = "data"

        val RESULT_PERMS = "perms"


        fun oneLineResult(i: Int, success: Any, fail: String) = if (i == 1) {
            ResultTo(success)
        } else {
            ResultTo(ResultEnum.OPERATION_FAILED, fail)
        }


        fun notNullResult(any: Any?, fail: String) = if (any != null) {
            ResultTo(any)
        } else {
            ResultTo(ResultEnum.OPERATION_FAILED, fail)
        }

        fun notNullEmptyResult(anyList: List<*>?,failNull: String,failEmpty:String) = when {
            anyList == null -> ResultTo(ResultEnum.SERVER_ERROR, failNull)
            anyList.isEmpty() -> ResultTo(ResultEnum.OPERATION_FAILED,failEmpty)
            else -> ResultTo(anyList)
        }


        fun assertResult(success: Boolean, successMsg: String, failMsg: String) = if (success) {
            ResultTo(successMsg)
        } else {
            ResultTo(ResultEnum.OPERATION_FAILED, failMsg)
        }


    }
}
