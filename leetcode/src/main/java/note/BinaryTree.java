package note;

/**
 * @description: 遍历二叉树
 * @author: dcy
 * @create: 2023-07-14 14:12
 */
public class BinaryTree {

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

    /**
     * 深度优先算法
     */
    private int dfs(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);

        return l > r ? (l + 1) : (r + 1);
    }

    public static void main(String[] args) {

        TreeNode leaf = new TreeNode();
        TreeNode left = new TreeNode(0, leaf, null);
        TreeNode right = new TreeNode(0);
        TreeNode root = new TreeNode(0, left, right);

        System.out.println(new BinaryTree().dfs(root));

    }

//    Lock



}


