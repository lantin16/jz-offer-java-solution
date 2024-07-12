package isBalanced;

import def.TreeNode;

/**
 * LCR 176. 判断是否为平衡二叉树
 *
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 */

public class Solution {

    /**
     * 后序遍历 + 剪枝
     * 树的深度 等于 左子树的深度 与 右子树的深度 中的 最大值 + 1 。
     * 根据平衡二叉树的定义：任意节点的左右子树的深度相差不超过1。因此只要检测到有某个节点的左右子树高度差已经不满足了就不用再检查其他的了（剪枝）
     */
    // 时间复杂度：O(n)，其中n为节点数。分析：最差情况下需要遍历所有节点
    // 空间复杂度：O(n)，其中n为节点数。分析：最差情况下树退化成链表，递归深度为n
    public boolean isBalanced(TreeNode root) {
        return postorder(root) != -1;
    }

    /**
     * 返回以root为根的子树的高度，如果该子树已经不是平衡二叉树了则返回-1（剪枝用）
     * @param root
     * @return
     */
    public int postorder(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int lh = postorder(root.left);
        if (lh == -1) { // 如果左子树已经不是平衡二叉树了则直接返回-1（剪枝）
            return -1;
        }

        int rh = postorder(root.right);
        if (rh == -1) { // 如果右子树已经不是平衡二叉树了则直接返回-1（剪枝）
            return -1;
        }

        return Math.abs(lh - rh) > 1 ? -1 : 1 + Math.max(lh, rh);    // 再判断左右子树之间的高度差
    }
}
