package com.example.springboot.common.commonutils

import java.text.SimpleDateFormat

class TimeUtils {
    companion object {
        const val Y_S_NORMAL = "yyyy-MM-dd HH:mm:ss"
        const val Y_MS_NORMAL_30 = "yyyy-MM-dd HH:mm:ss.SSS"
        const val Y_MS_NORMAL_40 = "yyyy-MM-dd HH:mm:ss.SSSS"
        const val Y_D_NORMAL = "yyyy-MM-dd"
        const val Y_MS_SLASH_SCATTERED = "M/d/yyyy H:m:s.SSS"
        const val Y_MS_CN = "yyyy年MM月dd日HH时mm分ss.SSS秒"
        const val Y_S_CLOSE = "yyyyMMddHHmmss"
        const val Y_MS_SLASH = "yyyy-M-d H:m:s.SSSSSS"
        const val Y_MS3_EN_UNIT = "yyyy'Y'MM'M'dd'D'HH'H'mm'M'ss.SSS'S'"
    }

    fun String.dateToStamp(format: String): Long {
        return SimpleDateFormat(format).parse(this).time.toString().toLong()
    }
}
