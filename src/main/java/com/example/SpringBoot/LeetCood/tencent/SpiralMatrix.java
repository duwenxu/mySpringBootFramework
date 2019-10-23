package com.example.springboot.leetcood.tencent;


import java.util.ArrayList;

/**
 * 顺时针螺旋遍历矩阵元素
 *
 * @author duwenxu
 * @create 2019-10-08 17:25
 */
public class SpiralMatrix {

    private static int sign = Integer.MAX_VALUE;

    /**
     * 1. 每次读取一个元素后进行标记该元素为-1
     * 2. 对下一个元素遍历的四种方式做判断，设定边界(判断条件)
     * 3. 循环结束的条件：    行数>=高 或 列数>=宽
     * 一次循环遍历后下一次向右遍历时右侧元素已遍历过
     *
     * @param matrix
     * @return
     */
    private static ArrayList getMatrixEle(int[][] matrix) {
        ArrayList resList = new ArrayList<Integer>();
        if (matrix.length == 0) {
            return resList;
        }
        int len = matrix.length, hei = matrix[0].length, i = 0, j = 0;
        resList.add(matrix[i][j]);
        matrix[i][j] = sign;

        while (true) {
            //向右
            while (j + 1 < len && matrix[i][j + 1] != sign) {
                resList.add(matrix[i][++j]);
                matrix[i][j] = sign;
            }
            //向下
            while (i + 1 < hei && matrix[i + 1][j] != sign) {
                resList.add(matrix[++i][j]);
                matrix[i][j] = sign;
            }
            //向左
            while (j - 1 > 0 && matrix[i][j - 1] != sign) {
                resList.add(matrix[i][--j]);
                matrix[i][j] = sign;
            }
            //向上
            while (i - 1 > 0 && matrix[i - 1][j] != sign) {
                resList.add(matrix[--i][j]);
                matrix[i][j] = sign;
            }
            if (j + 1 >= len || matrix[i][j + 1] == sign) {
                break;
            }
        }
        return resList;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 5}, {1, 5, 8}, {7, 2, 1}}; //m=3,n=3
        getMatrixEle(matrix).forEach(it->System.out.println(it));
    }

}

