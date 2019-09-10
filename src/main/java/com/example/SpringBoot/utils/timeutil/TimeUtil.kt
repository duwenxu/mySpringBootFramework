package com.example.springboot.utils.timeutil


import org.springframework.stereotype.Component
import java.text.SimpleDateFormat

@Component
class TimeUtil {

    companion object {

        const val Y_S_NORMAL = "yyyy-MM-dd HH:mm:ss"
        const val Y_MS_NORMAL_30 = "yyyy-MM-dd HH:mm:ss.SSS"
        const val Y_MS_NORMAL_40 = "yyyy-MM-dd HH:mm:ss.SSSS"
        const val Y_MS_NORMAL_60 = "yyyy-MM-dd HH:mm:ss.SSSSSS"
        const val Y_D_NORMAL = "yyyy-MM-dd"
        const val Y_D_LINK="yyyyMMdd"
        const val Y_MS_SLASH_SCATTERED = "M/d/yyyy H:m:s.SSS"
        const val Y_MS_CN = "yyyy年MM月dd日HH时mm分ss.SSS秒"
        const val Y_S_CLOSE = "yyyyMMddHHmmss"
        const val Y_MS_SLASH = "yyyy-M-d H:m:s.SSSSSS"
        const val Y_MS3_EN_UNIT = "yyyy'Y'MM'M'dd'D'HH'H'mm'M'ss.SSS'S'"

    }


//    fun newDate() = System.currentTimeMillis() + cacheOrReq()


}

/**
 * 替换时间.0000为4位随机数
 * @receiver String yyyy-MM-dd hh:mm:dd.ssss 格式日期中.ssss替换为4位随机数
 * @return String yyyy-MM-dd hh:mm:dd.ssss 日期格式字符串
 */
fun String.nanoRandom():String{
   return when(this.substringAfterLast(".")){
       "000" -> this.replace(".000", ".${(100..999).shuffled().last()}")
       "0000" -> this.replace(".0000", ".${(1000..9999).shuffled().last()}")
       else -> this
    }
}

fun String.dateToStamp(format:String):Long{
    return SimpleDateFormat(format).parse(this).time.toString().toLong()
}

