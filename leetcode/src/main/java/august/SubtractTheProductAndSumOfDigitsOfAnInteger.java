package august;

/**
 * @description: 1281. 整数的各位积和之差 https://leetcode.cn/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
 * <p>
 * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
 * <p>
 * 输入：n = 234
 * 输出：15
 * 解释：
 * 各位数之积 = 2 * 3 * 4 = 24
 * 各位数之和 = 2 + 3 + 4 = 9
 * 结果 = 24 - 9 = 15
 * <p>
 * 输入：n = 4421
 * 输出：21
 * 解释：
 * 各位数之积 = 4 * 4 * 2 * 1 = 32
 * 各位数之和 = 4 + 4 + 2 + 1 = 11
 * 结果 = 32 - 11 = 21
 * @author: dcy
 * @create: 2023-08-09 21:52
 */
public class SubtractTheProductAndSumOfDigitsOfAnInteger {


    public int subtractProductAndSum(int n) {
        int sum = 0;
        int ji = 1;

        while (n >= 10) {
            int t = n % 10;
            sum += t;
            ji *= t;
            n /= 10;
        }
        sum += n;
        ji *= n;

        return ji - sum;
    }

}


