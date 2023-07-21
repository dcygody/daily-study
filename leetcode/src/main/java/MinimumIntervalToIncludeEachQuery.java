import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @description: 1851. 包含每个查询的最小区间
 * <p>
 * 给你一个二维整数数组 intervals ，其中 intervals[i] = [lefti, righti] 表示第 i 个区间开始于 lefti 、结束于 righti（包含两侧取值，闭区间）。
 * 区间的 长度 定义为区间中包含的整数数目，更正式地表达是 righti - lefti + 1 。
 * 再给你一个整数数组 queries 。第 j 个查询的答案是满足 lefti <= queries[j] <= righti 的 长度最小区间 i 的长度 。如果不存在这样的区间，那么答案是 -1 。
 * 以数组形式返回对应查询的所有答案。
 * <p>
 * 输入：intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
 * 输出：[3,3,1,4]
 * 解释：查询处理如下：
 * - Query = 2 ：区间 [2,4] 是包含 2 的最小区间，答案为 4 - 2 + 1 = 3 。
 * - Query = 3 ：区间 [2,4] 是包含 3 的最小区间，答案为 4 - 2 + 1 = 3 。
 * - Query = 4 ：区间 [4,4] 是包含 4 的最小区间，答案为 4 - 4 + 1 = 1 。
 * - Query = 5 ：区间 [3,6] 是包含 5 的最小区间，答案为 6 - 3 + 1 = 4 。
 * <p>
 * 输入：intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
 * 输出：[2,-1,4,6]
 * 解释：查询处理如下：
 * - Query = 2 ：区间 [2,3] 是包含 2 的最小区间，答案为 3 - 2 + 1 = 2 。
 * - Query = 19：不存在包含 19 的区间，答案为 -1 。
 * - Query = 5 ：区间 [2,5] 是包含 5 的最小区间，答案为 5 - 2 + 1 = 4 。
 * - Query = 22：区间 [20,25] 是包含 22 的最小区间，答案为 25 - 20 + 1 = 6 。
 * @author: dcy
 * @create: 2023-07-18 10:21
 */
public class MinimumIntervalToIncludeEachQuery {

    /**
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/minimum-interval-to-include-each-query/solutions/755628/bao-han-mei-ge-cha-xun-de-zui-xiao-qu-ji-e21j/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int[] minInterval(int[][] intervals, int[] queries) {
        Integer[] qindex = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            qindex[i] = i;
        }
        Arrays.sort(qindex, (i, j) -> queries[i] - queries[j]);
        Arrays.sort(intervals, (i, j) -> i[0] - j[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        int[] res = new int[queries.length];
        Arrays.fill(res, -1);
        int i = 0;
        for (int qi : qindex) {
            while (i < intervals.length && intervals[i][0] <= queries[qi]) {
                pq.offer(new int[]{intervals[i][1] - intervals[i][0] + 1, intervals[i][0], intervals[i][1]});
                i++;
            }
            while (!pq.isEmpty() && pq.peek()[2] < queries[qi]) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                res[qi] = pq.peek()[0];
            }
        }
        return res;
    }



    public int[] minInterval2(int[][] intervals, int[] queries) {
        int[] res = new int[queries.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < queries.length; i++) {
            for (int[] interval : intervals) {
                if (interval[0] > queries[i] || interval[1] < queries[i]) {
                    continue;
                }
                if (contains(interval, queries[i])) {
                    if (res[i] == -1) {
                        res[i] = interval[1] - interval[0] + 1;
                    } else {
                        res[i] = Math.min(res[i], (interval[1] - interval[0] + 1));
                    }
                }
            }
        }


        return res;
    }

    private boolean contains(int[] array, int target) {
        for (int i = array[0]; i <= array[1]; i++) {
            if (i == target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MinimumIntervalToIncludeEachQuery query = new MinimumIntervalToIncludeEachQuery();
//        int[][] intervals = {{1, 4}, {2, 4}, {3, 6}, {4, 4}};
//        int[] queries = {2, 3, 4, 5};

        int[][] intervals = {{2,3}, {2, 5}, {1,8}, {20,25}};
        int[] queries = {2,19,5,22};
//        Arrays.sort(intervals, (i, j) -> i[0] - j[0]);
        int[] ints = query.minInterval(intervals, queries);
//        System.out.println(Arrays.deepToString(intervals));
        System.out.println(Arrays.toString(ints));
    }


}


