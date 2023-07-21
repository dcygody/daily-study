/**
 * @description: 415. 字符串相加 https://leetcode.cn/problems/add-strings/
 *
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 *
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 *
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 *
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 * @author: dcy
 * @create: 2023-07-17 18:25
 */
public class AddStrings {

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuilder ans = new StringBuilder();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }


    public static void main(String[] args) {
        AddStrings addStrings = new AddStrings();
        String num1 = "456", num2 = "77";
        // 输出："533"
        System.out.println(addStrings.addStrings(num1, num2));
    }

}


