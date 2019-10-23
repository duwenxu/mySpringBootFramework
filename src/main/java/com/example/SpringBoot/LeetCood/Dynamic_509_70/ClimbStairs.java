package com.example.springboot.leetcood.Dynamic_509_70;

/**
 * 斐波那契系列问题------爬楼梯问题
 * 第N级阶梯：
 *          N-1级：跨1级
 *          N-2级：跨2级
 *     总方法：F(N)=F(n-1)+F(N-2)
 *     解法类似 斐波那契
 *
 * @author duwenxu
 * @create 2019-09-04 11:15
 */
public class ClimbStairs {

    /**
     * 递归
     * @param n
     * @return
     */
    public static int cliStairs1(int n){
        if (n<=2){
            return n;
        }
        return cliStairs1(n-1)+ cliStairs1(n-2);
    }

    /**
     *
     * @param n
     * @return
     */
    public static int cliStairs2(int n){
        if (n<=2){
            return n;
        }
        int fn1=1,fn2=2;
//        while (n-->1){
//            fn2=fn2+fn1;
//            fn1=fn2-fn1;
//        }
        int tmp;
        for (int i = 3; i <=n; i++) {
            tmp=fn2;
            fn2=fn1+fn2;
            fn1=tmp;
        }
//        return fn1;
        return fn2;
    }

    /**
     * 动态规划的 加速矩阵乘法
     * @param n
     * @return
     */
    public static int cliStairs3(int n){
        if (n<=2){
            return n;
        }
        int[][] base={{1,1},{1,0}};
        int[][] res=Fib.matrixPower(base, n-2);
        return res[0][0]*2+res[1][0];
    }


    public static void main(String[] args) {
        System.out.println(cliStairs1(44));
        System.out.println(cliStairs2(7));
        System.out.println(cliStairs3(7));
    }
}
