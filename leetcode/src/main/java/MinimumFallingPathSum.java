import java.util.Arrays;

/**
 * @description: 931. 下降路径最小和
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
 * 具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 * <p>
 * 输入：matrix = [[2,1,3],
 * [6,5,4],
 * [7,8,9]]
 * 输出：13
 * 解释：如图所示，为和最小的两条下降路径
 * <p>
 * 输入：matrix = [[-19,57],[-40,-5]]
 * 输出：-59
 * 解释：如图所示，为和最小的下降路径
 * @author: dcy
 * @create: 2023-07-13 10:00
 */
public class MinimumFallingPathSum {

    public int minFallingPathSum1(int[][] matrix) {
        int n = matrix.length;
        int[] f = new int[n];
        for (int[] row : matrix) {
            int[] g = f.clone();
            for (int j = 0; j < n; ++j) {
                if (j > 0) {
                    g[j] = Math.min(g[j], f[j - 1]);
                }
                if (j + 1 < n) {
                    g[j] = Math.min(g[j], f[j + 1]);
                }
                g[j] += row[j];
            }
            f = g;
        }
        // return Arrays.stream(f).min().getAsInt();
        int ans = 1 << 30;
        for (int x : f) {
            ans = Math.min(ans, x);
        }
        return ans;
    }


    public int minFallingPathSum2(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[] dp = new int[m];
        for (int i = 0; i < m; i++) {
            dp[i] = matrix[0][i];
        }
        System.out.println(Arrays.toString(dp));

        for (int i = 1; i < n; i++) {
            int[] temp = new int[m];
            for (int j = 0; j < m; j++) {
                int min = dp[j];
                if (j - 1 >= 0) {
                    min = Math.min(min, dp[j - 1]);
                }
                if (j + 1 < m) {
                    min = Math.min(min, dp[j + 1]);
                }
                temp[j] = min + matrix[i][j];
            }
            dp = temp;
        }

        int res = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            res = Math.min(res, dp[j]);
        }
        return res;
    }

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[n][m];
        for(int j = 0; j < m; j++){
            dp[0][j] = matrix[0][j];
        }

        for(int i = 1; i < n; i++){
            for(int j = 0; j < m; j++){
                int min = dp[i - 1][j];
                if(j - 1 >= 0){
                    min = Math.min(min, dp[i-1][j-1]);
                }
                if(j + 1 < m){
                    min = Math.min(min, dp[i-1][j+1]);
                }

                dp[i][j] = min + matrix[i][j];
            }
        }

        int res = Integer.MAX_VALUE;
        for(int j = 0; j < m; j++){
            res = Math.min(res, dp[n-1][j]);
        }
        return res;
    }


    public static void main(String[] args) {

        int[][] matrix = {{-19, 57},
                          {-40, -5}};
        System.out.println(new MinimumFallingPathSum().minFallingPathSum(matrix));
    }

}


