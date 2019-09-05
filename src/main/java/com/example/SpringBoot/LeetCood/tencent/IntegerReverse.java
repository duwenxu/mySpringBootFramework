package com.example.springboot.LeetCood.tencent;



/**
 * 整数反转
 *
 * @author duwenxu
 * @create 2019-09-05 17:05
 */
public class IntegerReverse {

    public static int reverse(int x){
        int res=0;
        int length = String.valueOf(x).length();
        for (;x>0;res=res*10+x%10,x/=10);
        return
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
    }
}
