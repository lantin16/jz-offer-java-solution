package lowestCommonAncestor;

import def.TreeNode;

/**
 * LCR 193. 二叉搜索树的最近公共祖先
 *
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 */

public class Solution {

    /**
     * 从根往下同时搜索p，q，前面是它们公共祖先的一段，这一段它们俩肯定是往同一边继续搜索，因此找到分叉处就是最近的公共祖先，
     * 还有一种可能就是p，q有一个直接就是另一个的祖先，这种就返回先找到的那个
     * @param root
     * @param p
     * @param q
     * @return
     */
    // public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //     // p，q中有一个直接就是另一个的祖先，这种就返回先找到的那个
    //     if(root.val == p.val) {
    //         return p;
    //     }
    //     if(root.val == q.val) {
    //         return q;
    //     }
    //     // 判断p，q在root这里是否走同一边
    //     int diffp = root.val - p.val, diffq = root.val - q.val;   // 判断p，q，root的大小关系
    //     // 如果在root这里要分叉走了，那结果就是root
    //     if(diffp * diffq < 0) {
    //         return root;
    //     }
    //     // 如果在root这里还要往同一边走（diffp和diffq同号），那就继续往下搜索
    //     return diffp > 0 ? lowestCommonAncestor(root.left, p, q) : lowestCommonAncestor(root.right, p, q);
    // }


    /**
     * 优化写法
     * @param root
     * @param p
     * @param q
     * @return
     */
    // 时间复杂度：O(n)，其中n为节点个数。分析：每循环一轮排除一层，二叉搜索树的层数最小为 logn（满二叉树），最大为 n（退化为链表）。
    // 空间复杂度：O(1)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null) {
            if ((p.val - root.val) * (q.val - root.val) <= 0) { // p，q分叉或p，q有一个直接就是另一个祖先的情况都包括进去了
                return root;
            } else {    // 如果p，q还没分叉则继续向下搜索，题目保证一定能搜索到p，q
                root = p.val > root.val ? root.right : root.left;
            }
        }
        return root;
    }
}
