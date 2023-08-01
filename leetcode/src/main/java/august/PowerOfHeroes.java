package august;

import java.util.Arrays;

/**
 * @description: 2681. 英雄的力量 https://leetcode.cn/problems/power-of-heroes/
 * <p>
 * 给你一个下标从 0 开始的整数数组 nums ，它表示英雄的能力值。如果我们选出一部分英雄，这组英雄的 力量 定义为：
 * i0 ，i1 ，... ik 表示这组英雄在数组中的下标。那么这组英雄的力量为 max(nums[i0],nums[i1] ... nums[ik])2 * min(nums[i0],nums[i1] ... nums[ik]) 。
 * 请你返回所有可能的 非空 英雄组的 力量 之和。由于答案可能非常大，请你将结果对 109 + 7 取余。
 * <p>
 * 输入：nums = [2,1,4]
 * 输出：141
 * 解释：
 * 第 1 组：[2] 的力量为 22 * 2 = 8 。
 * 第 2 组：[1] 的力量为 12 * 1 = 1 。
 * 第 3 组：[4] 的力量为 42 * 4 = 64 。
 * 第 4 组：[2,1] 的力量为 22 * 1 = 4 。
 * 第 5 组：[2,4] 的力量为 42 * 2 = 32 。
 * 第 6 组：[1,4] 的力量为 42 * 1 = 16 。
 * 第​ ​​​​​​7 组：[2,1,4] 的力量为 42​​​​​​​ * 1 = 16 。
 * 所有英雄组的力量之和为 8 + 1 + 64 + 4 + 32 + 16 + 16 = 141 。
 * <p>
 * 输入：nums = [1,1,1]
 * 输出：7
 * 解释：总共有 7 个英雄组，每一组的力量都是 1 。所以所有英雄组的力量之和为 7 。
 * @author: dcy
 * @create: 2023-08-01 11:08
 */
public class PowerOfHeroes {

    /**
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/power-of-heroes/solutions/2359660/ying-xiong-de-li-liang-by-leetcode-solut-9k1g/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int sumOfPower(int[] nums) {
        Arrays.sort(nums);
        int dp = 0, preSum = 0;
        int res = 0, mod = 1000000007;
        for (int num : nums) {
            dp = (num + preSum) % mod;
            preSum = (preSum + dp) % mod;
            res = (int) ((res + (long) num * num % mod * dp) % mod);
            if (res < 0) {
                res += mod;
            }
        }
        return res;
    }


}


