package lowestCommonAncestor_194;

import def.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * LCR 194. 二叉树的最近公共祖先
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 */

public class Solution {


    /**
     * 先序遍历
     * 参考：https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/solutions/217281/mian-shi-ti-68-ii-er-cha-shu-de-zui-jin-gong-gon-7
     * @param root
     * @param p
     * @param q
     * @return
     */
    // 时间复杂度：O(n)，其中n为节点数。分析：最差情况下需要遍历树的所有节点
    // 空间复杂度：O(n)，其中n为节点数。分析：最差情况下递归深度达到 n
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归中止：如果越过叶子节点返回null，若遇到p、q则返回root
        if (root == null || root == p || root == q) {
            return root;
        }

        // root不是p、q则递归左右子树
        TreeNode lRes = lowestCommonAncestor(root.left, p, q);
        TreeNode rRes = lowestCommonAncestor(root.right, p, q);

        // 左右返回的都是null，代表左右子树都不含p、q，返回null
        if (lRes == null && rRes == null) {
            return null;
        }

        // 右不为null，左为null，说明p、q至少有一个在右子树，先往上返回结果
        if (lRes == null) {
            return rRes;
        }

        // 左不为null，右为null，说明p、q至少有一个在左子树，先往上返回结果
        if (rRes == null) {
            return lRes;
        }

        // 左右都不为null说明p、q分列root两子树中，root就是最近的公共祖先
        return root;
    }

}
