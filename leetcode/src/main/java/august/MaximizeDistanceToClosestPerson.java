package august;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 849. 到最近的人的最大距离 https://leetcode.cn/problems/maximize-distance-to-closest-person/
 * <p>
 * 给你一个数组 seats 表示一排座位，其中 seats[i] = 1 代表有人坐在第 i 个座位上，seats[i] = 0 代表座位 i 上是空的（下标从 0 开始）。
 * <p>
 * 至少有一个空座位，且至少有一人已经坐在座位上。
 * <p>
 * 亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。
 * <p>
 * 返回他到离他最近的人的最大距离。
 * <p> 0, 4, 6
 * 3, 2
 * 输入：seats = [1,0,0,0,1,0,1]
 * 输入：seats = [0,0,1,0,0,0,0]
 * 输入：seats = [0,0,0,0,1,0,0]
 * 输出：2
 * 解释：
 * 如果亚历克斯坐在第二个空位（seats[2]）上，他到离他最近的人的距离为 2 。
 * 如果亚历克斯坐在其它任何一个空位上，他到离他最近的人的距离为 1 。
 * 因此，他到离他最近的人的最大距离是 2 。
 * <p>
 * 输入：seats = [1,0,0,0]
 * 输出：3
 * 解释：
 * 如果亚历克斯坐在最后一个座位上，他离最近的人有 3 个座位远。
 * 这是可能的最大距离，所以答案是 3 。
 * <p>
 * 输入：seats = [0,1]
 * 输出：1
 * @author: dcy
 * @create: 2023-08-22 14:10
 */
public class MaximizeDistanceToClosestPerson {

    /**
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/maximize-distance-to-closest-person/solutions/2393766/dao-zui-jin-de-ren-de-zui-da-ju-chi-by-l-zboe/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int maxDistToClosest(int[] seats) {
        int res = 0;
        int l = 0;
        while (l < seats.length && seats[l] == 0) {
            ++l;
        }
        res = Math.max(res, l);
        while (l < seats.length) {
            int r = l + 1;
            while (r < seats.length && seats[r] == 0) {
                ++r;
            }
            if (r == seats.length) {
                res = Math.max(res, r - l - 1);
            } else {
                res = Math.max(res, (r - l) / 2);
            }
            l = r;
        }
        return res;
    }


}




