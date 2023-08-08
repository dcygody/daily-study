package august;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 560. 和为 K 的子数组  https://leetcode.cn/problems/subarray-sum-equals-k/
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
 * <p>
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * <p>
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * @author: dcy
 * @create: 2023-08-08 11:24
 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        //细节，这里需要预存前缀和为 0 的情况，会漏掉前几位就满足的情况
        //例如输入[1,1,0]，k = 2 如果没有这行代码，则会返回0,漏掉了1+1=2，和1+1+0=2的情况
        //输入：[3,1,1,0] k = 2时则不会漏掉
        //因为presum[3] - presum[0]表示前面 3 位的和，所以需要map.put(0,1),垫下底
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            //更新
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }


}


