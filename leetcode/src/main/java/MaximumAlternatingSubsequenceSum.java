/**
 * @description: 1911. 最大子序列交替和
 * 一个下标从 0 开始的数组的 交替和 定义为 偶数 下标处元素之 和 减去 奇数 下标处元素之 和 。
 * 比方说，数组 [4,2,5,3] 的交替和为 (4 + 5) - (2 + 3) = 4 。
 * 给你一个数组 nums ，请你返回 nums 中任意子序列的 最大交替和 （子序列的下标 重新 从 0 开始编号）。
 * 一个数组的 子序列 是从原数组中删除一些元素后（也可能一个也不删除）剩余元素不改变顺序组成的数组。比方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的一个子序列（加粗元素），但是 [2,4,2] 不是。
 * <p>
 * 输入：nums = [4,2,5,3]
 * 输出：7
 * 解释：最优子序列为 [4,2,5] ，交替和为 (4 + 5) - 2 = 7 。
 * <p>
 * 输入：nums = [5,6,7,8]
 * 输出：8
 * 解释：最优子序列为 [8] ，交替和为 8 。
 * <p>
 * 输入：nums = [6,2,1,2,4,5]
 * 输出：10
 * 解释：最优子序列为 [6,1,5] ，交替和为 (6 + 5) - 1 = 10 。
 * @author: dcy
 * @create: 2023-07-11 09:51
 */
public class MaximumAlternatingSubsequenceSum {

    /**
     * 设计两个长整数 evenDp 和 oddDp，分别记录上一元素为偶数下标、奇数下标时当前的最大交替和。根据是否添加当前元素，状态转移方程为
     */
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long evenDp = nums[0], oddDp = 0;//上一元素为偶数下标、奇数下标时的最大交替和
        for (int i = 1; i < n; i++) {
            long lastEven = evenDp, lastOdd = oddDp;
            evenDp = Math.max(lastEven, lastOdd + nums[i]);//偶数下标交替和转移
            oddDp = Math.max(lastOdd, lastEven - nums[i]);//奇数下标交替和转移
        }
        return evenDp;
    }


    public static void main(String[] args) {
        MaximumAlternatingSubsequenceSum subsequenceSum = new MaximumAlternatingSubsequenceSum();

//        int[] nums = {374, 126, 84, 237, 195, 139, 328, 353, 286, 113, 351, 167, 394, 398, 29, 118, 17, 162, 206, 138, 34, 109, 291, 368, 162, 109, 336, 256, 203, 330, 235, 74, 136, 72, 127, 382, 288, 276, 135, 383, 300, 220, 299, 205, 186, 113, 71, 261, 253, 47, 387, 25, 57, 79, 322, 82, 349, 217, 306, 33, 198, 196, 306, 240, 271, 129, 284, 6, 349, 370, 59, 350, 275, 385, 137, 394, 329, 175, 58, 151, 223, 81, 233, 2, 370, 369, 135, 257, 391, 92, 260, 55, 321, 153, 328, 260, 312, 102, 79, 192};
        int[] nums = {4,2,5,3};
        System.out.println(subsequenceSum.maxAlternatingSum(nums));
    }


}


