package mirrorTree;

import def.TreeNode;

/**
 * LCR 144. 翻转二叉树
 *
 * 给定一棵二叉树的根节点 root，请左右翻转这棵二叉树，并返回其根节点。
 */

public class Solution {
    /**
     * 递归翻转
     * @param root
     * @return
     */
    // 时间复杂度：O(n)，其中n为树的节点个数
    // 空间复杂度：O(h)，其中h为树的高度
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        /*
        // 翻转root的左右子树
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        // 递归翻转左右子树各自的内部
        root.left = mirrorTree(root.left);
        root.right = mirrorTree(root.right);
        */

        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }
}
