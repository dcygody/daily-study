package august;

/**
 * @description: 344. 反转字符串 https://leetcode.cn/problems/reverse-string/
 *
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 *
 * 输入：s = ["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 *
 *
 * @author: dcy
 * @create: 2023-08-07 20:56
 */
public class ProblemsReverseString {


    public void reverseString(char[] s) {

        int left = 0;
        int right = s.length - 1;
        while (left <= right) {
            char l = s[left];
            char r = s[right];
            s[left] = r;
            s[right] = l;
            left++;
            right--;
        }
    }

}


