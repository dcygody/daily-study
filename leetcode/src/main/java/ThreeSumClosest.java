import java.util.Arrays;

/**
 * @description: 最接近的三数之和
 * <p>
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 * 返回这三个数的和。
 * 假定每组输入只存在恰好一个解
 * <p>
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * <p>
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 * @author: dcy
 * @create: 2023-07-10 09:42
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        // [-4, -1, 1, 2]
        Arrays.sort(nums);
        if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        }
        int res = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            int right = nums.length - 1;
            int left = i + 1;
            while (left < right) {
                if (nums[i] + nums[right] + nums[left] == target) {
                    return target;
                }
                res = Math.abs(nums[i] + nums[left] + nums[right] - target) > Math.abs(res - target) ? res : nums[i] + nums[left] + nums[right];
                if (nums[i] + nums[left] + nums[right] > target) {
                    right--;
                } else {
                    left++;
                }
            }

        }


        return res;

    }


    public static void main(String[] args) {
        int[] nums = {4, 0, 5, -5, 3, 3, 0, -4, -5};
        int target = -2;

        ThreeSumClosest threeSumClosest = new ThreeSumClosest();
        System.out.println(threeSumClosest.threeSumClosest(nums, target));

    }
}


