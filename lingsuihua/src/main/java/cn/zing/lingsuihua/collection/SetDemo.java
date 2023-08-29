package cn.zing.lingsuihua.collection;

import java.util.BitSet;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-29 11:01
 */
public class SetDemo {

    public static void main(String[] args) {
        String str1 = "qwertyu";
        String str2 = "uytrewq";
        System.out.println(checkSameChars(str1, str2));

    }

    /**
     * bitset 的使用
     * BitSet是Java中的一个用于存储二进制位的集合类。它有很多实用的方法，例如可以用来检查或设置特定位的值，进行逻辑位操作，或者进行集合的操作（交、并、补）等
     * <p>
     * 假设我们有两个字符串str1和str2，希望判断它们是否由相同的字符组成。
     * 我们可以使用BitSet来解决这个问题。具体做法是，对于每个字符串中的字符，将其对应的ASCII码值减去'a'（或者'A'），
     * 然后将这个值作为BitSet的下标，将该位置的值设置为1。最后，我们再将两个BitSet对象进行and操作，如果其结果为0，则说明这两个字符串由不同的字符组成。
     */
    public static boolean checkSameChars(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        BitSet bs1 = str2BitSet(str1);
        BitSet bs2 = str2BitSet(str2);
        bs1.and(bs2);

        return bs1.cardinality() == bs2.cardinality();
    }

    public static BitSet str2BitSet(String str) {

        // 默认都是 0
        BitSet bitSet = new BitSet();

        for (int i = 0; i < str.length(); i++) {
            int j = str.charAt(i) - 'a';
            bitSet.set(j);
        }

        return bitSet;
    }

}

/**
 * //a = a & b
 * void and(BitSet set);
 *
 * //a = a & !b
 * void andNot(BitSet set);
 *
 * //a = a^b
 * void xor(BitSet set);
 *
 * //a = a | b
 * void or(BitSet set);
 *
 * //将指定索引处的位设置为 true
 * void set(int bitIndex)
 *
 * //将指定索引处的位设置为指定的值
 * void set(int bitIndex, boolean value);
 *
 * //将指定的 fromIndex（包括）到指定的 toIndex（不包括）范围内的位设置为 true
 * void set(int fromIndex, int toIndex);
 *
 * //将指定的 fromIndex（包括）到指定的 toIndex（不包括）范围内的位设置为指定的值
 * void set(int fromIndex, int toIndex, boolean value);
 *
 * //返回指定索引处的位值
 * boolean get(int bitIndex);
 *
 * //返回一个新的 BitSet，它由 fromIndex（包括）到 toIndex（不包括）范围内的位组成
 * BitSet get(int fromIndex, int toIndex);
 *
 * //返回此 BitSet 的“逻辑大小”，即实际使用的位数
 * int length();
 *
 * //返回此 BitSet 表示位值时实际使用空间的位数，即 words.length * 64
 * int size();
 *
 * //将此 BitSet 中的所有位设置为 false
 * void clear();
 *
 * //将索引指定处的位设置为 false
 * void clear(int bitIndex);
 *
 * //将指定的 fromIndex（包括）到指定的 toIndex（不包括）范围内的位设置为 false
 * void clear(int fromIndex, int toIndex);
 *
 * //将指定索引处的值设置为其当前值的补码
 * void flip(int bitIndex);
 *
 * //将 fromIndex（包括）到指定的 toIndex（不包括）范围内的每个位设置为其当前值的补码
 * void flip(int fromIndex, int toIndex);
 *
 * //返回此 BitSet 中设置为 true 的位数
 * int cardinality();
 *
 * //如果指定 BitSet 中设置为 true 的位，在此 BitSet 中也为 true，则返回 ture
 * boolean intersects(BitSet set);
 *
 * //如果此 BitSet 中没有包含任何设置为 true 的位，则返回 ture
 * boolean isEmpty();
 *
 * //返回 fromIndex（包括）之后第一个设置为 false 的位的索引
 * int nextClearBit(int fromIndex);
 *
 * //返回 fromIndex（包括）之后的第一个设置为 true 的位的索引
 * int nextSetBit(int fromIndex);
 *
 * //返回该 BitSet 中为 true 的索引的字符串拼接形式
 * String toString();
 *
 * //返回 hashcode 值
 * int hashcode();
 *
 * //复制此 BitSet，生成一个与之相等的新 BitSet。
 * Object clone();
 *
 * //将此对象与指定的对象进行比较。
 * boolean equals(Object obj);

 */


