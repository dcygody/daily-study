package august;

import java.util.Arrays;

/**
 * @description: 833. 字符串中的查找与替换 https://leetcode.cn/problems/find-and-replace-in-string/
 * <p>
 * 你会得到一个字符串 s (索引从 0 开始)，你必须对它执行 k 个替换操作。替换操作以三个长度均为 k 的并行数组给出：indices, sources,  targets。
 * <p>
 * 要完成第 i 个替换操作:
 * <p>
 * 检查 子字符串  sources[i] 是否出现在 原字符串 s 的索引 indices[i] 处。
 * 如果没有出现， 什么也不做 。
 * 如果出现，则用 targets[i] 替换 该子字符串。
 * 例如，如果 s = "abcd" ， indices[i] = 0 , sources[i] = "ab"， targets[i] = "eee" ，那么替换的结果将是 "eeecd" 。
 * <p>
 * 所有替换操作必须 同时 发生，这意味着替换操作不应该影响彼此的索引。测试用例保证元素间不会重叠 。
 * <p>
 * 例如，一个 s = "abc" ，  indices = [0,1] ， sources = ["ab"，"bc"] 的测试用例将不会生成，因为 "ab" 和 "bc" 替换重叠。
 * 在对 s 执行所有替换操作后返回 结果字符串 。
 * <p>
 * 子字符串 是字符串中连续的字符序列。
 * <p>
 * 输入：s = "abcd", indices = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
 * 输出："eeebffff"
 * 解释：
 * "a" 从 s 中的索引 0 开始，所以它被替换为 "eee"。
 * "cd" 从 s 中的索引 2 开始，所以它被替换为 "ffff"。
 * <p>
 * <p>
 * 输入：s = "abcd", indices = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * 输出："eeecd"
 * 解释：
 * "ab" 从 s 中的索引 0 开始，所以它被替换为 "eee"。
 * "ec" 没有从原始的 S 中的索引 2 开始，所以它没有被替换。
 * @author: dcy
 * @create: 2023-08-15 14:03
 */
public class FindAndReplaceInString {

    /**
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/find-and-replace-in-string/solutions/2388853/xian-xing-zuo-fa-pythonjavacgojs-by-endl-uofo/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length();
        String[] replaceStr = new String[n]; // 替换后的字符串
        int[] replaceLen = new int[n];    // 被替换的长度
        Arrays.fill(replaceLen, 1);     // 无需替换时 i+=1
        for (int i = 0; i < indices.length; i++) {
            int idx = indices[i];
            if (s.startsWith(sources[i], idx)) {
                replaceStr[idx] = targets[i];
                replaceLen[idx] = sources[i].length();
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i += replaceLen[i]) { // 无需替换时 i+=1
            if (replaceStr[i] == null) {
                ans.append(s.charAt(i));
            } else {
                ans.append(replaceStr[i]);
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        //
        // 输入：s = "abcd", indices = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
        String s = "abcd";
        int[] indices = {0,2};
        String[] sources = {"a","cd"};
        String[] targets = {"eee","ffff"};

        System.out.println(new FindAndReplaceInString().findReplaceString(s, indices, sources, targets));

    }


}


