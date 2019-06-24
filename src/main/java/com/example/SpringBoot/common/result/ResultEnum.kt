package com.example.springboot.common.result

/**
 * 返回结果枚举
 *
 * @author rankai
 * createTime 2017-04-2017/4/20 14:47
 */
enum class ResultEnum private constructor(val code: Int, val message: String) {

    SUCCESS(200, "操作成功"),

    SERVER_ERROR(500, "服务器错误"),

    OPERATION_FAILED(5000, "操作失败"),

    PARAM_ERROR(5001, "参数错误"),

    UNLOGIN(5002,"需要登录"),

    PERMISSION_DENIED(5003, "权限不足，拒绝访问")
}
