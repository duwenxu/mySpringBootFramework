package com.example.springboot.LeetCood.tencent;

/**
 * 回文数字：判断一个数字是否是回文
 *
 * @author duwenxu
 * @create 2019-09-25 13:36
 */
public class PalindromeNumber {
    public static void main(String[] args) {
//        System.out.println(isPalindrome1(1112));
        System.out.println(isPalindrome2(12345));
    }

    /**
     * 解法一： 整数转字符串再判断    垃圾解法
     * @param x
     * @return
     */
    private static boolean isPalindrome1(int x) {
        String s = String.valueOf(x);
        int length = s.length();
        if (length%2==0){
            for (int i = 0; i < length/2; i++) {
                if (s.charAt(i)!=s.charAt(length-1-i)){
                    return false;
                }
            }
        }else {
            for (int i = 0; i < (length-1)/2; i++) {
                if (s.charAt(i)!=s.charAt(length-1-i)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 数学解法：
     * @param x
     * @return
     */
    private static boolean isPalindrome2(int x) {
        if (x<0||(x%10==0 && x!=0)){
            return false;
        }
        //1221
        int palindromeNum=0;
        while (x>palindromeNum){
            palindromeNum=x%10+palindromeNum*10;
            x/=10;
        }
        //当为奇数时，跳出while循环时，中间的那一位已经添加在palindromeNum最后了，所以/10处理
        return (x==palindromeNum)||(x==palindromeNum/10);
    }

}
