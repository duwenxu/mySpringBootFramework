
import Longest_Palindromic_Substring.longestPalindrome1


/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"

 方法：  中心扩展
        马拉车
 */

object Longest_Palindromic_Substring {

    /**
     * 暴力法  可通过
     * 耗时长 资源消耗多  O（n^2）
     * @param s String
     * @return String
     */
    fun longestPalindrome1(s: String): String {
        var longStr = ""
            for (i in 0 until s.length) {
                for (j in i until s.length) {
                    if (s[i] == s[j]) {
                        val str = s.substring(i,j+1)
                        print("$i-------->${j + 1}------>$str\n")
                        if (str.toCharArray().let { it.contentEquals(it.reversedArray())}) {    //String反转函数： reversed   效率差不多
                            if (longStr.length < str.length) {
                                longStr = str
                            }
                        }
                    }
                }
            }
        return longStr
    }



    /**
     * 动态规划
     * 回文子串的条件    两端元素相等且子串回文
     * @param s String
     * @return String
     */
//    fun longestPalindrome2(s: String): String {
//        val temp:String
//        val longStr:String
//        for (i in 0 until s.length){
//
//        }
//    }



}


fun main(args: Array<String>) {
    val s = "abcdfgjjjfdjksgfiwjewrpgreoighdsjgvjwejgwejg"
    println(longestPalindrome1(s))
//    s.substring(2,0)
}
