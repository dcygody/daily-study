/**
 * @description: 42. 接雨水 https://leetcode.cn/problems/trapping-rain-water/
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * @author: dcy
 * @create: 2023-07-23 11:12
 */
public class TrappingRainWater {

    /**
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/trapping-rain-water/solutions/692342/jie-yu-shui-by-leetcode-solution-tuvc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int trap2(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    /**
     * 一层一层遍历 每层有几个0就是几 2264ms 时间较长
     */
    public int trap(int[] height) {
        int res = 0;
        // 遍历次数
        int iter = arrMax(height) - 1;
        int left = 0, right = height.length - 1;
        int idx = 0;
        while (idx <= iter) {

            while ((height[left] - idx) <= 0) {
                left++;
            }
            while ((height[right] - idx) <= 0) {
                right--;
            }
            if (left < right) {
                for (int i = left; i <= right; i++) {
                    if ((height[i] - idx) <= 0) {
                        res += 1;
                    }
                }
                idx += 1;
                left = 0;
                right = height.length - 1;
            } else {
                break;
            }

        }
        return res;
    }

    public int arrMax(int[] arr) {

        // 1. 假设数组中的第一个元素为值
        int max = arr[0];
        // 2. 遍历数组, 获取每一个元素, 准备进行比较
        for (int i = 1; i < arr.length; i++) {
            // 3. 如果比较的过程中, 出现了比max更大的, 让max记录更大的值
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }


    public static void main(String[] args) {
        TrappingRainWater water = new TrappingRainWater();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = {4, 2, 0, 3, 2, 5};
//        int[] height = {0,2,0};
//        int[] height = {2,0,2};
        System.out.println(water.trap(height));

    }
}


