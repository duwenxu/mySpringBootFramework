package com.example.springboot.leetcood;


/**
 * 最长公共前缀
 *
 * @author duwenxu
 * @create 2019-04-02 18:23
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 */
public class LongestCommprefix {
    /**
     * 暴力循环法  循环遍历获取
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            char comStr = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].length() <= i || comStr != strs[j].charAt(i)) {
                    return sb.toString();
                }
                if (strs[j].charAt(i) == comStr && j == strs.length - 1) {
                    sb.append(comStr);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
    }
}


