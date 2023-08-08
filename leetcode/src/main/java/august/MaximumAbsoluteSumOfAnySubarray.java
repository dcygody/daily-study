package august;

/**
 * @description: 1749. 任意子数组和的绝对值的最大值 https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray/
 *
 * 给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
 * 请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
 * abs(x) 定义如下：
 * 如果 x 是负整数，那么 abs(x) = -x 。
 * 如果 x 是非负整数，那么 abs(x) = x 。
 *
 * 输入：nums = [1,-3,2,3,-4]
 * 输出：5
 * 解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
 *
 * 输入：nums = [2,-5,1,-4,3,-2]
 * 输出：8
 * 解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
 *
 *
 * @author: dcy
 * @create: 2023-08-08 10:42
 */
public class MaximumAbsoluteSumOfAnySubarray {

    /**
     * 由于子数组和等于两个前缀和的差，那么取前缀和中的最大值与最小值，它俩的差就是答案。
     * 如果最大值在最小值右边，那么算的是最大子数组和。
     * 如果最大值在最小值左边，那么算的是最小子数组和的绝对值（相反数）。
     */
    public int maxAbsoluteSum(int[] nums) {
        int pre=0;
        int max=0;
        int min=0;
        for (int num : nums) {
            pre += num;
            max = Math.max(max, pre);
            min = Math.min(min, pre);
        }
        return max-min;
    }

    public static void main(String[] args) {
        int[] nums = {2,-5,1,-4,3,-2};
        System.out.println(new MaximumAbsoluteSumOfAnySubarray().maxAbsoluteSum(nums));
    }
}


