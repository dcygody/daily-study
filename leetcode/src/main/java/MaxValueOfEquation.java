import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @description: 1499. 满足不等式的最大值 https://leetcode.cn/problems/max-value-of-equation/
 * <p>
 * 给你一个数组 points 和一个整数 k 。数组中每个元素都表示二维平面上的点的坐标，并按照横坐标 x 的值从小到大排序。
 * 也就是说 points[i] = [xi, yi] ，并且在 1 <= i < j <= points.length 的前提下， xi < xj 总成立。
 * 请你找出 yi + yj + |xi - xj| 的 最大值，其中 |xi - xj| <= k 且 1 <= i < j <= points.length。
 * 题目测试数据保证至少存在一对能够满足 |xi - xj| <= k 的点。
 * <p>
 * 输入：points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
 * 输出：4
 * 解释：前两个点满足 |xi - xj| <= 1 ，代入方程计算，则得到值 3 + 0 + |1 - 2| = 4 。第三个和第四个点也满足条件，得到值 10 + -10 + |5 - 6| = 1 。
 * 没有其他满足条件的点，所以返回 4 和 1 中最大的那个。
 * <p>
 * 输入：points = [[0,0],[3,0],[9,2]], k = 3
 * 输出：3
 * 解释：只有前两个点满足 |xi - xj| <= 3 ，代入方程后得到值 0 + 0 + |0 - 3| = 3 。
 * @author: dcy
 * @create: 2023-07-21 10:39
 */
public class MaxValueOfEquation {


    /**
     * TODO 超时
     */
    public int findMaxValueOfEquation1(int[][] points, int k) {

        int right = points.length - 1;
        int max = Integer.MIN_VALUE;
        for (int left = 0; left < points.length; left++) {

            while (right > left) {
                if (points[right][0] - points[left][0] <= k) {
                    max = Math.max((points[left][1] + points[right][1] + points[right][0] - points[left][0]), max);
                }
                right--;
            }
            right = points.length - 1;

        }

        return max;

    }

    /**
     *    作者：力扣官方题解
     *     链接：https://leetcode.cn/problems/max-value-of-equation/solutions/2351324/man-zu-bu-deng-shi-de-zui-da-zhi-by-leet-5rbj/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int findMaxValueOfEquation(int[][] points, int k) {
        int res = Integer.MIN_VALUE;
        Deque<int[]> queue = new ArrayDeque<int[]>();
        for (int[] point : points) {
            int x = point[0], y = point[1];
            while (!queue.isEmpty() && x - queue.peekFirst()[1] > k) {
                queue.pollFirst();
            }
            if (!queue.isEmpty()) {
                res = Math.max(res, x + y + queue.peekFirst()[0]);
            }
            while (!queue.isEmpty() && y - x >= queue.peekLast()[0]) {
                queue.pollLast();
            }
            queue.offer(new int[]{y - x, x});
        }
        return res;
    }



    public static void main(String[] args) {
        MaxValueOfEquation equation = new MaxValueOfEquation();
        int k = 1;
        int[][] points = {{1,3},{2,0},{5,10},{6,-10}};
        System.out.println(equation.findMaxValueOfEquation(points, k));
    }
}


