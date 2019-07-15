package com.example.springboot.superdog.DogCheck

import com.example.springboot.common.result.ResultTo
import com.example.springboot.superdog.DogStatus
import com.waytogalaxy.display.utils.timeutil.TimeUtil
import com.waytogalaxy.display.utils.timeutil.dateToStamp
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@Api(tags = ["登录权限的时间验证"])
@RequestMapping("/api/timeCheck")
@RestController
class TimeCheckController {

    /**
     * 验证登录时间是否在指定期限之前
     * @return Boolean
     */
    @ApiOperation("时间验证")
    @PostMapping("/getCheck")
    fun isAuthor(): ResultTo {
        val deadTime = Cons.DEAD_TIME.dateToStamp(TimeUtil.Y_S_CLOSE)
        return ResultTo(Date().time <= deadTime)
    }

    /**
     * 加密狗授权验证
     * @return ResultTo
     */
    @ApiOperation("superDog授权认证")
    @GetMapping("/dogStatus")
    fun dogStatus(): ResultTo {
        val feature = FeatureEnum.BY_FREQUENCY.featureCode
        val vendorCode = Cons.VENDOR_CODE
        val handle= intArrayOf(0)
        val status = getLinkMeth.CLibrary.INSTANCE_DOG.dog_login(feature, vendorCode, handle)
        if (DogStatus.DOG_STATUS_OK == status) {
            return ResultTo("授权成功！")
        }
        return ResultTo(status, DogStatusEnum.getMessageByCode(status))
    }



}