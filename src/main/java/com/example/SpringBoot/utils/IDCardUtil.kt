package com.waytogalaxy.display.utils

import com.example.springboot.utils.timeutil.TimeUtil
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

/**
 * 身份证工具
 *
 * @author rankai
 * createTime 2017-04-2017/4/26 14:12
 */
object IDCardUtil {


    private val wi = intArrayOf(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1)
    private val vi = intArrayOf(1, 0, 'X'.toInt(), 9, 8, 7, 6, 5, 4, 3, 2)
    private val ai = IntArray(18)

    private const val MONTH = 2
    private const val YEAR = 1



    /**
     * 验证身份证格式是正确
     *
     * @param idCard 身份证号
     * @return 格式是否正确
     */
    fun verify(idCard: String?): Boolean {
        var idCardNotNull: String? = idCard ?: return false
        idCardNotNull = idCardNotNull?.trim()
        if (idCardNotNull?.length == 15) {
            idCardNotNull = upToEighteen(idCardNotNull)
        }
        if (idCardNotNull?.length != 18) {
            return false
        }
        val verify = idCardNotNull.substring(17, 18)
        return verify == getVerify(idCardNotNull)
    }

    /**
     * 获得生日
     *
     * @param idcard
     * @return
     */
    fun getBirthdayByIDcard(idcard: String): Date? {
        if (verify(idcard)) {
            val birthday = idcard.substring(6, 14)
            try {
                return SimpleDateFormat(TimeUtil.Y_D_NORMAL).parse(birthday)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

        }
        return null
    }

    /**
     * 获得年龄
     *
     * @param idCard
     * @return
     */
    fun getAgeByIDcard(idCard: String): Int {
        if (verify(idCard)) {
            val _cal = Calendar.getInstance()
            val today = Calendar.getInstance()
            _cal.set(Integer.parseInt(idCard.substring(6, 10)), Integer.parseInt(idCard.substring(10, 12)),
                    Integer.parseInt(idCard.substring(12, 14)))
            return getYearDiff(today, _cal)
        }
        return 0
    }

    /**
     * 获得性别
     *
     * @param idCard
     * @return
     */
    fun getSexByIDCard(idCard: String): SexEnum {
        if (verify(idCard)) {
            val lastValue = idCard.substring(idCard.length - 2, idCard.length - 1)
            val sex: Int
            sex = Integer.parseInt(lastValue) % 2
            return if (sex == 0) SexEnum.WOMAN else SexEnum.MAN
        }
        return SexEnum.MAN
    }

    private fun getYearDiff(cal: Calendar, _cal: Calendar): Int {
        val m = cal.get(MONTH) - _cal.get(MONTH)
        val y = cal.get(YEAR) - _cal.get(YEAR)
        return (y * 12 + m) / 12
    }

    private fun getVerify(idCard: String): String {
        var idCardModify = idCard
        var remaining = 0
        if (idCardModify.length == 18) {
            idCardModify = idCardModify.substring(0, 17)
        }
        if (idCardModify.length == 17) {
            var sum = 0
            for (i in 0..16) {
                val k = idCardModify.substring(i, i + 1)
                ai[i] = Integer.parseInt(k)
            }
            for (i in 0..16) {
                sum = sum + wi[i] * ai[i]
            }
            remaining = sum % 11
        }
        return if (remaining == 2) "X" else vi[remaining].toString()
    }

    private fun upToEighteen(idCard: String): String {
        var eightCardId = idCard.substring(0, 6)
        eightCardId = eightCardId + "19"
        eightCardId = eightCardId + idCard.substring(6, 15)
        eightCardId = eightCardId + getVerify(eightCardId)
        return eightCardId
    }

    enum class SexEnum private constructor(val code: Int, val sexSrt: String) {

        MAN(1, "男"),
        WOMAN(2, "女")
    }
}

