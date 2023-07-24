import java.util.ArrayList;
import java.util.List;

/**
 * @description: 771. 宝石与石头 https://leetcode.cn/problems/jewels-and-stones/
 *
 *  给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。 stones 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * 字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
 *
 * 输入：jewels = "aA", stones = "aAAbbbb"
 * 输出：3
 *
 * 输入：jewels = "z", stones = "ZZ"
 * 输出：0
 *
 *
 * @author: dcy
 * @create: 2023-07-24 12:55
 */
public class JewelsAndStones {


    public int numJewelsInStones2(String jewels, String stones) {

        int oldLen = stones.length();
        for (int i = 0; i < jewels.length(); i++) {
            char c = jewels.charAt(i);
            stones = stones.replace(c+"", "");
        }


        return oldLen - stones.length();

    }

    public int numJewelsInStones(String jewels, String stones) {

        int res = 0;
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < jewels.length(); i++) {
            list.add(jewels.charAt(i));
        }
        for (int i = 0; i < stones.length(); i++) {
            if (list.contains(stones.charAt(i))) {
                res++;
            }
        }
        return res;

    }


    public static void main(String[] args) {
        JewelsAndStones jewelsAndStones = new JewelsAndStones();
        String jewels = "aA";
        String stones = "aAAbbbb";
        System.out.println(jewelsAndStones.numJewelsInStones(jewels, stones));
    }

}


