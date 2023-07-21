/**
 * @description: 2544. 交替数字和
 * 给你一个正整数 n 。n 中的每一位数字都会按下述规则分配一个符号：
 * <p>
 * 最高有效位 上的数字分配到 正 号。
 * 剩余每位上数字的符号都与其相邻数字相反。
 * 返回所有数字及其对应符号的和。
 * <p>
 * 输入：n = 521
 * 输出：4
 * 解释：(+5) + (-2) + (+1) = 4
 * <p>
 * 输入：n = 111
 * 输出：1
 * 解释：(+1) + (-1) + (+1) = 1
 * <p>
 * 输入：n = 886996
 * 输出：0 
 * 解释：(+8) + (-8) + (+6) + (-9) + (+9) + (-6) = 0
 * @author: dcy
 * @create: 2023-07-12 08:56
 */
public class AlternatingDigitSum {

    /**
     * 转成字符串遍历
     */
    public int alternateDigitSum2(int n) {
        int res = 0;
        String s = n + "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int temp = c - '0';
            System.out.println(c);

            if (i % 2 == 0) {
                res += temp;
            } else {
                res -= temp;
            }

        }
        return res;

    }

    /**
     * 官方
     * 给你一个正整数 n，要求计算 n 的数字交替和。
     * <p>
     * 我们用 sign\textit{sign}sign 来表示数字的正负，并初始化为 1。每一步中，我们将 nnn 对 101010 取模，得到个位数字，把它和 sign\textit{sign}sign 相乘求和，将 sign\textit{sign}sign 取相反数，再把 nnn 除以 101010。 不断重复这一步骤，直到 nnn 为零。这样我们就得到了数字交替和。
     * <p>
     * 最后，因为最高有效位上的数字分配到正号，我们将结果乘以 −sign-\textit{sign}−sign 后返回。
     * <p>
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/alternating-digit-sum/solutions/2319524/jiao-ti-shu-zi-he-by-leetcode-solution-uz50/
     */
    public int alternateDigitSum1(int n) {
        int res = 0, sign = 1;
        while (n > 0) {
            res += n % 10 * sign;
            sign = -sign;
            n /= 10;
        }
        return -sign * res;
    }


    public int alternateDigitSum(int n) {
        int res = 0;
        while (n > 0) {
            res = n % 10 - res;
            n = n / 10;
        }

        return res;

    }

    public static void main(String[] args) {
        AlternatingDigitSum alternatingDigitSum = new AlternatingDigitSum();
//        (+8) + (-8) + (+6) + (-9) + (+9) + (-6) = 0
//        6-0=6  9-6=3 3-9=-6 -6-(-6)=0 8-0=8 8-8=0
        int n = 886996;
//        alternatingDigitSum.alternateDigitSum(n);
        System.out.println(alternatingDigitSum.alternateDigitSum(n));
    }

}


