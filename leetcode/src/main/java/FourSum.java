import java.util.*;

/**
 * @description: 18. 四数之和
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组
 * [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * <p>
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * <p>
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * <p>
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 * @author: dcy
 * @create: 2023-07-15 12:01
 */
public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> resSet = new HashSet<>();
        if (nums.length < 4) {
            return res;
        }
        Arrays.sort(nums);
//        System.out.println(Arrays.toString(nums));
//        System.out.println("===================================");
        int len = nums.length;
        int right = len - 1;
        for (int i = 0; i < len; i++) {
            int f = nums[i];
            for (int j = i + 1; j < len - 1; j++) {
                int s = nums[j];
                int left = j + 1;
                List<Integer> list = new ArrayList<>();
                while (left < right) {
                    long sum = (long)f + s + nums[left] + nums[right];
                    if (target == sum) {
                        list = Arrays.asList(f, s, nums[left], nums[right]);
//                        System.out.println(list);
                        resSet.add(list);
                        right--;
                        left++;
                    } else if (f + s + nums[left] + nums[right] > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
                right = len - 1;
            }
        }

        res.addAll(resSet);
        return res;
    }

    public static void main(String[] args) {
        FourSum fourSum = new FourSum();
        int[] nums = {2,2,2,2,2};
        nums = new int[]{1000000000, 1000000000, 1000000000, 1000000000};
        int target = -294967296;
        List<List<Integer>> lists = fourSum.fourSum(nums, target);

        System.out.println(lists);

    }


}


