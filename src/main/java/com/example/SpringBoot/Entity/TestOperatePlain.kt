package com.example.SpringBoot.Entity

import java.text.SimpleDateFormat

data class OperatingPlan(
        val cnMissionType: String,
        val missionType: String,
        val operId: Int,
        val startTime: String,
        val formatStartTime:String= SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(SimpleDateFormat("yyyyMMddHHmmss").parse(startTime)).toString()
)


fun main(args: Array<String>) {
    val OperatingPlan=OperatingPlan("111","222",1,"20180802002834")
    println(OperatingPlan.formatStartTime)
}