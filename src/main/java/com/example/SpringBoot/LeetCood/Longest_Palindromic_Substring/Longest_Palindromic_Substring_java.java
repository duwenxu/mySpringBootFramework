package com.example.SpringBoot.LeetCood.Longest_Palindromic_Substring;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 最长回文子串
 *
 * @author duwenxu
 * @create 2019-05-23 9:28
 */
public class Longest_Palindromic_Substring_java {

    /**
     * 思路：暴力法  超时
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        //存放所有的回文串
        Set<String> palindromes = new HashSet<>();
        s = s.trim();
        if(s.equals(new StringBuilder(s).reverse().toString())) {
            return s;
        }else {
            int len = s.length();
            for(int i=0;i<len;i++) {
                for(int j=i+1;j<=len;j++) {
                    String str = s.substring(i, j);
                    if(str.equals(new StringBuilder(str).reverse().toString())) {
                        palindromes.add(str);
                    }
                }
            }
            Iterator<String> iterator =  palindromes.iterator();
            String candidate = iterator.next();
            while(iterator.hasNext()) {
                String second = iterator.next();
                if(candidate.length()<second.length()) {
                    candidate = second;
                }
            }
            return candidate;
        }
    }
}
