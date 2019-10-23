package com.example.springboot.leetcood.tencent;



/**
 * 整数反转
 *
 * @author duwenxu
 * @create 2019-09-05 17:05
 */
public class IntegerReverse {

    public static int reverse(int x){
        int min=Integer.MIN_VALUE,max=Integer.MAX_VALUE;
        Long res=0L;        //用Long类型接收转换后的数据判断是否越界
        for (;x!=0;res=res*10+x%10,x/=10);  //从数学的角度进行逐次的累加
        return res>max||res<min?0:res.intValue();
    }

    public static void main(String[] args) {
        System.out.println(reverse(-124855));
    }
}
