package com.example.springboot.LeetCood.Dynamic_509;

/**
 * 斐波那契数列问题
 *
 * @author duwenxu
 * @create 2019-09-03 16:13
 */
public class fib {


    /**
     * 累加法   时间复杂度 O(N)
     *
     * @param N
     * @return
     */
    public static int fib_result2(int N) {
        if (N == 2) {
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


    public static int fib_result3(int N) {
        if (N == 2) {
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
    private static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {  //初始化为E
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {   //判断是否需要累乘
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
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
        System.out.println(fib_result2(6));
    }
}


