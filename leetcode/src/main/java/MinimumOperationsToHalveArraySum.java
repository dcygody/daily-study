import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @description: 2208. 将数组和减半的最少操作次数 https://leetcode.cn/problems/minimum-operations-to-halve-array-sum/
 * <p>
 * 给你一个正整数数组 nums 。每一次操作中，你可以从 nums 中选择 任意 一个数并将它减小到 恰好 一半。（注意，在后续操作中你可以对减半过的数继续执行操作）
 * 请你返回将 nums 数组和 至少 减少一半的 最少 操作数。
 * <p>
 * 输入：nums = [5,19,8,1]
 * 输出：3
 * 解释：初始 nums 的和为 5 + 19 + 8 + 1 = 33 。
 * 以下是将数组和减少至少一半的一种方法：
 * 选择数字 19 并减小为 9.5 。
 * 选择数字 9.5 并减小为 4.75 。
 * 选择数字 8 并减小为 4 。
 * 最终数组为 [5, 4.75, 4, 1] ，和为 5 + 4.75 + 4 + 1 = 14.75 。
 * nums 的和减小了 33 - 14.75 = 18.25 ，减小的部分超过了初始数组和的一半，18.25 >= 33/2 = 16.5 。
 * 我们需要 3 个操作实现题目要求，所以返回 3 。
 * 可以证明，无法通过少于 3 个操作使数组和减少至少一半。
 * <p>
 * 输入：nums = [3,8,20]
 * 输出：3
 * 解释：初始 nums 的和为 3 + 8 + 20 = 31 。
 * 以下是将数组和减少至少一半的一种方法：
 * 选择数字 20 并减小为 10 。
 * 选择数字 10 并减小为 5 。
 * 选择数字 3 并减小为 1.5 。
 * 最终数组为 [1.5, 8, 5] ，和为 1.5 + 8 + 5 = 14.5 。
 * nums 的和减小了 31 - 14.5 = 16.5 ，减小的部分超过了初始数组和的一半， 16.5 >= 31/2 = 16.5 。
 * 我们需要 3 个操作实现题目要求，所以返回 3 。
 * 可以证明，无法通过少于 3 个操作使数组和减少至少一半。
 * @author: dcy
 * @create: 2023-07-25 12:06
 */
public class MinimumOperationsToHalveArraySum {


    /**
     * 超时
     */
    public int halveArray2(int[] nums) {
        int res = 0;
        float sum = 0;
        float[] floatNums = new float[nums.length];
        for (int i = 0; i < nums.length; i++) {
            floatNums[i] = (float) nums[i];
            sum += floatNums[i];
        }
        float totalTemp = 0f;

        while (totalTemp < sum / 2) {
            int maxIdx = max(floatNums);
            totalTemp += floatNums[maxIdx] / 2;
            res++;
            floatNums[maxIdx] = floatNums[maxIdx] / 2;
        }
        return res;
    }

    private int max(float[] arr) {
        float max = arr[0];
        int maxIdx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxIdx = i;
            }
        }

        return maxIdx;
    }

    /**
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/minimum-operations-to-halve-array-sum/solutions/1374499/jiang-shu-zu-he-jian-ban-de-zui-shao-cao-4lej/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p>
     * 时间复杂度：O(nlogn)
     * <p>
     * 空间复杂度：O(n)
     */
    public int halveArray(int[] nums) {
        PriorityQueue<Double> pq = new PriorityQueue<>(Comparator.reverseOrder());
        double sum = 0;
        for (int num : nums) {
            pq.offer((double) num);
            sum += num;
        }
        int res = 0;
        double sum2 = 0.0;
        while (sum2 < sum / 2) {
            double x = pq.poll();
            sum2 += x / 2;
            pq.offer(x / 2);
            res++;
        }
        return res;
    }


    public static void main(String[] args) {
//        MinimumOperationsToHalveArraySum arraySum = new MinimumOperationsToHalveArraySum();
//        int[] nums = {3, 8, 20};
//        System.out.println(arraySum.halveArray(nums));

        int a = 5;
        float b = a;
        System.out.println(b);

    }


}


