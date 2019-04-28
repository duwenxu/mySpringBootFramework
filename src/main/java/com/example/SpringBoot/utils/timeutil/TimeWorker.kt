package com.waytogalaxy.display.utils.timeutil

import com.alicp.jetcache.anno.CacheType
import com.alicp.jetcache.anno.Cached
import com.waytogalaxy.display.api.reqentity.OffsetTime
import com.waytogalaxy.display.common.FastestCache
import com.waytogalaxy.display.common.config.cacheConfig.CacheKey
import com.waytogalaxy.display.exts.get
import com.waytogalaxy.display.okhttpUtils.ApiUtil
import com.waytogalaxy.display.okhttpUtils.enums.ApiStore
import com.waytogalaxy.display.utils.rest.HttpUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TimeWorker {

    companion object {
        const val JUMP_TIME_MS = "JUMP_TIME_MS_"
    }


    @Autowired
    lateinit var fastestCache: FastestCache

    /**
     * @todo 临时使用航天器id 随后统一称为航天器code
     */
    @Cached(name = CacheKey.OFFSET_TIME,key = "args[0]",cacheType = CacheType.LOCAL)
    fun getOffset(spacecraftId: Int): Long? {
        val reqResult = HttpUtil.get("${ApiStore.OFFSET_TIME}$spacecraftId")
                .halfErrorUrl()
                .returnClass(OffsetTime::class.java)
                .exec()
        if(reqResult.success && reqResult.t?.code == 200){
            return reqResult.t.data
        }else {
            return null
        }
    }


}