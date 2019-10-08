package com.example.springboot.LeetCood.tencent;

/**
 * 顺时针螺旋遍历矩阵元素
 *
 * @author duwenxu
 * @create 2019-10-08 17:25
 */
public class SpiralMatrix {

    private static int[] getMatrixEle(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] res = new int[m * n];

    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 5}, {1, 5, 8}, {7, 2, 1}}; //m=3,n=3
        getMatrixEle(matrix);
    }

}

