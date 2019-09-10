package com.example.springboot.LeetCood.tencent;


/**
 * 字符串转整数
 * 1. 寻找第一个非空元素
 * 2. 第一个字符：
 *
 *
 * ****************************************************抄的**********************
 * @author duwenxu
 * @create 2019-09-06 8:17
 */
public class Atio {

    public static int str2Int(String s) {
        s = s.trim();
        if (s.isEmpty()) return 0;

        char firstChar = s.charAt(0);
        int sign = 1, start = 0;   //符号位  第一个数字的位置
        long res = 0L;

        if (firstChar == '+') {
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }
        for (int i = start; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(start))) {  //遍历至非数字字符时，直接return当前res  覆盖了第一个非空字符为其他字符的情况
                return (int) res * sign;
            }
            res = res * 10 + s.charAt(start)-'0';
            if (sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && res > Integer.MAX_VALUE) return Integer.MIN_VALUE;
        }
        return (int) res * sign;
    }

    public static void main(String[] args) {
        System.out.println(str2Int("  -1245 wx  124578"));
    }
}
