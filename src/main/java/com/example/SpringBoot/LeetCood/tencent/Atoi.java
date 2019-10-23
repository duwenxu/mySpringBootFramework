package com.example.springboot.leetcood.tencent;


/**
 * 字符串转整数
 * 思路：
 * 1. 去空值
 * 2. 将数值分为 符号部分和数字部分，分别确定这两部分的值
 * 3. 逐次判断 +，-，其它字符，数字
 *
 * @author duwenxu
 * @create 2019-09-06 8:17
 */
public class Atoi {

    private static int myAtio(String str) {
        str = str.trim();
        if (str.isEmpty()) return 0;

        int sign = 1, start = 0;
        long res = 0;
        char firstChar = str.charAt(0);
        if (firstChar == '+') {
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }

        for (int i = start; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return (int) res * sign;
            }
            res = res * 10 + str.charAt(i) - '0';//转换为long来接收超出int范围的值
            if (sign==-1&& res>Integer.MAX_VALUE) return Integer.MIN_VALUE;
            if (sign==1 && res>Integer.MAX_VALUE) return Integer.MAX_VALUE;
        }
        return (int) res * sign;
    }

    public static void main(String[] args) {
        System.out.println(myAtio("  -1245 wx  124578"));
    }
}
