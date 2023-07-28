package gaopin;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 1. 两数之和 https://leetcode.cn/problems/two-sum/
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 *
 * @author: dcy
 * @create: 2023-07-28 13:42
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {

        //nums的值做key, 索引做Value
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        int[] res = new int[2];
        //遍历数组
        for (int i = 0; i < nums.length; i++) {
            //如果map中有可构成target的另一个值, 找到, 直接返回
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            // 否则, 就把当前值加入map中
            map.put(nums[i], i);
        }
        return res;
    }


}


