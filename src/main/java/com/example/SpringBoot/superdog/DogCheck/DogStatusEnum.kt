package com.example.springboot.superdog.DogCheck


enum class DogStatusEnum(val code: Int, val message: String) {
    DOG_STATUS_OK(0, "请求已成功完成"),

    DOG_DEVICE_ERR(43, "与超级狗通信中出现USB通信错误"),

    DOG_NOT_FOUND(7, "未找到所需的超级狗"),

    DOG_FEATURE_EXPIRED(41, "特征已失效"),

    DOG_FEATURE_NOT_FOUND(31, "特征不可用"),

    DOG_INSUF_MEM(3, "系统内存不足"),

    DOG_UNKNOWN_VCODE(34, "API不识别开发商代码"),

    DOG_INV_VCODE(22, "输入的开发商代码无效"),

    DOG_TIME_ERR(45, "系统时钟已被篡改"),

    DOG_TMOF(4, "打开的登陆会话数目过多"),

    DOG_TS_DETECTED(27, "程序在终端服务器上运行"),

    DOG_LOCAL_COMM_ERR(33, "Api和超级狗运行环境错误"),

    DOG_OLD_LM(42, "超级狗运行环境的版本太旧");

    companion object {

        fun getMessageByCode(code: Int): String {
            values().forEach {
                if (it.code == code) {
                    return it.message
                }
            }
            return ""
        }
    }

}