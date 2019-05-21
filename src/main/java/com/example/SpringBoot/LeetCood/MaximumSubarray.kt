package com.example.SpringBoot.LeetCood

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
进阶:

如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
class MaximumSubarray {
    /**
     * 暴力法   循环得到 每一个子序列的和 与 最大序列和 比较得出
     *  效率低，耗时长，耗资源多  时间复杂度O（n^2）
     * @param nums Array<Int>
     * @return Int
     */
    fun maxSubArray(nums: Array<Int>): Int {
        if (nums.isEmpty()) return 0
        var max = nums[0]
        var sum: Int
        for (i in 0 until nums.size) {
            sum = 0
            for (j in i until nums.size) {
                sum += nums[j]
                if (max < sum) max = sum
            }
        }
        return max
    }

    /**
     * 时间复杂度(O(N))的算法:
    该算法更为简便之处是忽略了对子序列的寻找比较,而是根据规律直接找出最佳答案.
    对于含有正数的序列而言,最大子序列肯定是正数,所以头尾肯定都是正数.我们可以从第一个正数开始算起,每往后加一个数便更新一次和的最大值;当当前和成为负数时,则表明此前序列无法为后面提供最大子序列和,因此必须重新确定序列首项.
    ！！！！！！---该算法忽略了数组中全为负数的情况，需要补充
     * @param nums Array<Int>
     * @return Int
     */
    fun maxSubArray1(nums: Array<Int>): Int {
        var max = 0   //最大子序列的和
        var current = 0   //当前子序列的和
        for (i in 0 until nums.size) {
            current += nums[i]
            if (current > max) {
                max = current
            } else if (current < 0) {   //由含正数序列其最大子序列和必为正数
                current = 0
            }
        }
        return max
    }


    /**
     * 动态规划
     * 1. 逐步求解，以连续数组的结束位置为每一步的解，sum记录了上一步的解
     * 2. 上一步骤解<0 舍弃 ，并与之前的最大值相比较
     *                        对于含正数序列，直接舍弃负数和
     *                        对于全负数序列，相当于逐个比较值大小
     *                        对于全正数序列，相当于累加
     *   时间复杂度 O（n）
     * @param nums Array<Int>
     */
    fun maxSubArray2(nums: Array<Int>): Int {
        var sum = 0
        var res = nums[0]
        for (num in nums) {
            sum = if (sum > 0) sum + num else num
            if (res < sum) {
                res = sum
            }
        }
        return res
    }




}

fun main(args: Array<String>) {
    val nums = arrayOf(-6, -7, -3, -4, -3, -6, -3, -5, -1)
//    println(MaximumSubarray().maxSubArray(nums))
    println(MaximumSubarray().maxSubArray1(nums))
//    println(MaximumSubarray().maxSubArray2(nums))
//    println(MaximumSubarray().maxSubArray2(nums))
}