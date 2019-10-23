package com.example.springboot.leetcood.Dynamic_509_70;

/**
 * 斐波那契数列问题
 * 符合 F(0)=0 F(1)=1
 * F(n)=F(n-1)+F(n-2)
 *
 * @author duwenxu
 * @create 2019-09-03 16:13
 */
public class Fib {

    /**
     * 递归法
     *
     * @param N
     * @return
     */
    public static int fib_result1(int N) {   //递归问题的定义域一定要覆盖全集
        if (N < 1) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        return fib_result1(N - 1) + fib_result1(N - 2);
    }


    /**
     * 动态规划1   时间复杂度 O(N)
     *
     * @param N
     * @return
     */
    public static int fib_result2(int N) {
        if (N<=0){
            return 0;
        }
        if (N == 2||N==1) {
            return 1;
        }
        int fn_1 = 1;
        int fn_2 = 1;
        int tmp = 0;
        for (int i = 3; i <= N; i++) {
            tmp = fn_1;  //存储F(n-1)，作为下一次的F(n-2)
            fn_1 = fn_1 + fn_2;  //新的F(n-1)
            fn_2 = tmp;
        }
        return fn_1;
    }

    /**
     * 动态规划2
     * @param N
     * @return
     */
    public static int fib_result4(int N) {
        int curr=0,next=1;
        while (N-->0){  //递减规划
            next=next+curr;  //相当于是省去了一个tmp变量
            curr=next-curr;
        }
        return curr;
    }

    /**
     * 加速矩阵乘法 进行动态规划
     * @param N
     * @return
     */
    public static int fib_result3(int N) {
        if (N<=0){
            return 0;
        }
        if (N == 2||N==1) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, N - 2);
        return res[0][0] + res[1][0];
    }

    /**
     * 求矩阵m的p次方
     *
     * @param m
     * @param p
     * @return
     */
    protected static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        int z = 1, t = 1;
        for (int i = 0; i < res.length; i++) {  //初始化为E
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for (; p != 0; p >>= 1) {
            /**
             * 每次右移一位，最低位为1时对应在次幂的二进制中该位为1，进行累乘到结果中
             *              最低位为0时不是二进制中需要的次幂，不累乘
             *              通过逐次的累乘和次幂P的右移，使位和tmp的值对应
             */
            if ((p & 1) == 1) {
                res = muliMatrix(res, tmp);
                z++;
            }
            tmp = muliMatrix(tmp, tmp);
            t++;
        }
        return res;
    }

    /**
     * 矩阵 a,b 相乘
     *
     * @param a
     * @param b
     * @return
     */
    private static int[][] muliMatrix(int[][] a, int[][] b) {
        int[][] res = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(fib_result1(7));
        System.out.println(fib_result2(7));
        System.out.println(fib_result3(16));
        System.out.println(fib_result4(7));
    }
}


