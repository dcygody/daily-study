/**
 * @description: 979. 在二叉树中分配硬币
 * 给定一个有 N 个结点的二叉树的根结点 root，树中的每个结点上都对应有 node.val 枚硬币，并且总共有 N 枚硬币。
 * 在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。(移动可以是从父结点到子结点，或者从子结点移动到父结点。)。
 * 返回使每个结点上只有一枚硬币所需的移动次数。
 * <p>
 * 输入：[3,0,0]
 * 输出：2
 * 解释：从树的根结点开始，我们将一枚硬币移到它的左子结点上，一枚硬币移到它的右子结点上。
 * <p>
 * 输入：[0,3,0]
 * 输出：3
 * 解释：从根结点的左子结点开始，我们将两枚硬币移到根结点上 [移动两次]。然后，我们把一枚硬币从根结点移到右子结点上。
 * <p>
 * 输入：[1,0,2]
 * 输出：2
 * <p>
 * 输入：[1,0,0,null,3]
 * 输出：4
 * @author: dcy
 * @create: 2023-07-14 12:34
 */
public class DistributeCoinsInBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    int ans = 0;

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return ans;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);
        ans += Math.abs(l + r + root.val - 1);
        int temp = l + r + root.val - 1;
        return l + r + root.val - 1;
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(0);
        TreeNode right = new TreeNode(0);
        TreeNode root = new TreeNode(3, left, right);

        DistributeCoinsInBinaryTree binaryTree = new DistributeCoinsInBinaryTree();
        System.out.println(binaryTree.distributeCoins(root));


    }
}


