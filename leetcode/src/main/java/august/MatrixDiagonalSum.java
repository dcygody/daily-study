package august;

/**
 * @description: 1572. 矩阵对角线元素的和 https://leetcode.cn/problems/matrix-diagonal-sum/
 *
 * 给你一个正方形矩阵 mat，请你返回矩阵对角线元素的和。
 * 请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。
 *
 * 输入：mat = [[1,2,3],
 *             [4,5,6],
 *             [7,8,9]]
 * 输出：25
 * 解释：对角线的和为：1 + 5 + 9 + 3 + 7 = 25
 * 请注意，元素 mat[1][1] = 5 只会被计算一次。
 *
 * 输入：mat = [[1,1,1,1],
 *             [1,1,1,1],
 *             [1,1,1,1],
 *             [1,1,1,1]]
 * 输出：8
 *
 * 输入：mat = [[5]]
 * 输出：5
 *
 * @author: dcy
 * @create: 2023-08-11 20:03
 */
public class MatrixDiagonalSum {

    public int diagonalSum(int[][] mat) {
        int rowLen = mat.length;
        int colLen = mat[0].length;
        int res = 0;

        for (int i = 0; i < rowLen; i++) {
            int n1 = mat[i][i];
            int n2 = mat[i][colLen - i - 1];
            if (colLen - i - 1 == i) {
                res += n1;
            } else {
                res = res + n1 + n2;

            }
        }
        return res;
    }
}


