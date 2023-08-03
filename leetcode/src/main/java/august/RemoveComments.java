package august;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description: 722. 删除注释 https://leetcode.cn/problems/remove-comments/
 * @author: dcy
 * @create: 2023-08-03 16:25
 */
public class RemoveComments {

    public List<String> removeComments(String[] source) {

        return Arrays.stream(String.join("\n", source)
                .replaceAll("//.*|/\\*(.|\n)*?\\*/", "")
                .split("\n")).filter(e -> (e.length() > 0))
                .collect(Collectors.toList());


    }

    /**
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/remove-comments/solutions/2365861/shan-chu-zhu-shi-by-leetcode-solution-lb9x/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param source
     * @return
     */
    public List<String> removeComments2(String[] source) {
        List<String> res = new ArrayList<String>();
        StringBuilder newLine = new StringBuilder();
        boolean inBlock = false;
        for (String line : source) {
            for (int i = 0; i < line.length(); i++) {
                if (inBlock) {
                    if (i + 1 < line.length() && line.charAt(i) == '*' && line.charAt(i + 1) == '/') {
                        inBlock = false;
                        i++;
                    }
                } else {
                    if (i + 1 < line.length() && line.charAt(i) == '/' && line.charAt(i + 1) == '*') {
                        inBlock = true;
                        i++;
                    } else if (i + 1 < line.length() && line.charAt(i) == '/' && line.charAt(i + 1) == '/') {
                        break;
                    } else {
                        newLine.append(line.charAt(i));
                    }
                }
            }
            if (!inBlock && newLine.length() > 0) {
                res.add(newLine.toString());
                newLine.setLength(0);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String[] source = {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
        System.out.println(new RemoveComments().removeComments(source));
//        String s = "/*Test program */";
//        System.out.println(s.startsWith("/*"));
    }

}


