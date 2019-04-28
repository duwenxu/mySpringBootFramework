package com.example.SpringBoot.LeetCood.ContainerWithMostWater

/**
* @Description: 盛水最多的容器
* @Date: 2019/3/25
 *
 * 思想：指针对撞  只进行一次扫描
*/
class Solution{
    fun maxArea(height:IntArray):Int{
        var area=0
        var l=0
        var r=height.size-1
        while (l<r){
            area=Math.max(area,Math.min(height[l],height[r])*(r-l))
            when(height[l]<height[r]){
                true->l++
                else->r--
            }
        }
        return area
    }
}