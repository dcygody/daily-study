import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 834. 树中距离之和 https://leetcode.cn/problems/sum-of-distances-in-tree/
 * 给定一个无向、连通的树。树中有 n 个标记为 0...n-1 的节点以及 n-1 条边 。
 * 给定整数 n 和数组 edges ， edges[i] = [ai, bi]表示树中的节点 ai 和 bi 之间有一条边。
 * 返回长度为 n 的数组 answer ，其中 answer[i] 是树中第 i 个节点与所有其他节点之间的距离之和。
 *
 * 输入: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * 输出: [8,12,6,10,10,10]
 * 解释: 树如图所示。
 * 我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * 也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
 *
 * 输入: n = 1, edges = []
 * 输出: [0]
 *
 * 输入: n = 2, edges = [[1,0]]
 * 输出: [1,1]
 *
 *
 * @author: dcy
 * @create: 2023-07-16 11:19
 */
public class SumOfDistancesInTree {

    List<Integer>[] g;
    int[] ans, size;    // size是各个节点作为根节点的子树大小
    int n;
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<Integer>());
        for (int[] edge: edges) {
            int x = edge[0], y = edge[1];
            g[x].add(y);
            g[y].add(x);
        }
        this.n = n;
        ans = new int[n];
        size = new int[n];
        Arrays.fill(size, 1);

        dfs(0, -1, 0);
        reroot(0, -1);
        return ans;
    }

    // 求ans[0]和各个size[i]
    void dfs(int x, int fa, int depth) {
        ans[0] += depth;                    // depth 是 0 到 x 的距离
        for (int y: g[x]) {
            if (y != fa) {
                dfs(y, x, depth + 1);
                size[x] += size[y];         // 累加 x 的儿子 y 的子树大小
            }
        }
    }

    // 求答案
    void reroot(int x, int fa) {
        for (int y: g[x]) {
            if (y != fa) {
                ans[y] = ans[x] + n - 2 * size[y];
                reroot(y, x);
            }
        }
    }

    public static void main(String[] args) {
        SumOfDistancesInTree sumOfDistancesInTree = new SumOfDistancesInTree();
        int n = 6;
        int[][] edges = {{0,1},{0,2},{2,3},{2,4},{2,5}};

        int[] ints = sumOfDistancesInTree.sumOfDistancesInTree(n, edges);
        System.out.println(Arrays.toString(ints));
    }
}


