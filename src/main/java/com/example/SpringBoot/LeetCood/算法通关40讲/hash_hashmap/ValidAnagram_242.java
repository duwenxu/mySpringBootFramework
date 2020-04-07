package com.example.springboot.leetcood.算法通关40讲.hash_hashmap;

import java.util.Arrays;

/**
 * 有效的字母异位词
 *
 * @author duwenxu
 * @create 2020-04-06 18:46
 * <p>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * <p>
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 */
public class ValidAnagram_242 {

    /**
     * 解法一： 分别对两个字符串排序后进行比较是否相等
     *
     * @param s1
     * @param s2
     * @return
     */
    private static Boolean isAnagram1(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }

    /**
     * 解法二：  1.设置一个map集合分别映射26各字母及其各自在字符串中出现的数量
     * 2.用第一个String的数量减去第二个，如果最后映射数目均为0则相同，否则不同
     * <p>
     * a-z:97-122
     * A-Z:65-90
     */
    private static boolean isAnagram2(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            counter[s1.charAt(i) - 'a']++;
            counter[s2.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count!= 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 解法3
     * @param s1
     * @param s2
     * @return
     */
    private static boolean isAnagram3(String s1, String s2) {
        if (s1.length()!= s2.length()){
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0;i<s1.length();i++){
            counter[s1.charAt(i)-'a']++;
        }
        for (int i = 0;i<s2.length();i++){
            counter[s2.charAt(i) - 'a']--;
            //相当于已经存在s1中元素与s2中个数不同
            if (counter[s2.charAt(i) - 'a'] <0){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
//        String s1 = "anagram";
//        String s2 = "nagaramq";

        String s1 = "aacc";
        String s2 = "ccac";
        System.out.println(isAnagram1(s1, s2));
        System.out.println(isAnagram2(s1, s2));
        System.out.println(isAnagram3(s1, s2));
    }

}





