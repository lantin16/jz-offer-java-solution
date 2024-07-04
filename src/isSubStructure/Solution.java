package isSubStructure;

import def.TreeNode;

/**
 * LCR 143. 子结构判断
 *
 * 给定两棵二叉树 tree1 和 tree2，判断 tree2 是否以 tree1 的某个节点为根的子树具有 相同的结构和节点值 。
 * 注意，空树 不会是以 tree1 的某个节点为根的子树具有 相同的结构和节点值 。
 */

public class Solution {

    /**
     * 先序遍历A找到val与B的根节点val相等的节点node（从node开始的子树可能包含B的结构），
     * 然后开始检查B是否是以node为根的子树的子结构，只要找到一个满足的即可
     * @param A
     * @param B
     * @return
     */
    // 时间复杂度：O(mn)，其中m，n分别为A，B的节点数。分析：先序遍历A需要O(m)时间，每次尝试从node节点开始匹配B需要O(n)时间
    // 空间复杂度：O(m)。分析：当树A和树B都退化为链表时，递归调用深度最大，m≤n 时，遍历树 A 与递归判断的总递归深度为 m ；当 m>n 时，最差情况为遍历至树 A 的叶节点，此时总递归深度为 m。
    // public boolean isSubStructure(TreeNode A, TreeNode B) {
    //     // 如果一开始A或B有null直接返回false
    //     if(A == null || B == null) {
    //         return false;
    //     }
    //     // A,B都不为null开始对A进行先序遍历
    //     return inorder(A, B);
    // }
    //
    // // 此函数中的参数B一直为最初的整个非空B
    // private boolean inorder(TreeNode root, TreeNode B) {
    //     if(root == null) {
    //         return false;
    //     }
    //     // 对A进行先序遍历的过程中找到与B根节点值相同的节点，尝试以该节点为跟与B开始匹配，如果匹配上了，则直接返回true
    //     if(root.val == B.val && isSub(root, B)) {
    //         return true;
    //     }
    //     // 如果没匹配上就尝试到root的左右子树中匹配B，结果取或
    //     return inorder(root.left, B) || inorder(root.right, B);
    // }
    //
    // // 从A开始匹配B，判断B是否是A为根的子树的子结构。注意此函数中由于递归会传入子树，可能B为null
    // private boolean isSub(TreeNode A, TreeNode B) {
    //     // B匹配完了则返回true
    //     if(B == null) {
    //         return true;
    //     }
    //     // 如果B还没匹配完A却为null了则匹配失败，返回false
    //     if(A == null && B != null) {
    //         return false;
    //     }
    //     // 如果A，B都不为null则比较值以及左右子树是否都能匹配上
    //     return A.val == B.val && isSub(A.left, B.left) && isSub(A.right, B.right);
    // }


    /**
     * 中序遍历A的同时以各个节点node为根尝试对B进行匹配
     * 此函数是判断B是否在A中，但不一定是从节点A开始匹配，注意与isSub方法区分
     * @param A
     * @param B
     * @return
     */
    // 时间复杂度：O(mn)，其中m，n分别为A，B的节点数。
    // 空间复杂度：O(m)，其中m为A的节点数。
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        // A，B都不为null
        // 从A开始对B进行匹配，或者B位于A的左右子树中（注意这里哪些是调isSub，哪些是调isSub，哪些是调isSubStructure）
        return isSub(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    /**
     * 判断以A为根的子树是否包含B的结构，注意此函数一定是从A开始判断，如果子树A的其他节点为根包含了B也返回false
     * @param A
     * @param B
     * @return
     */
    private boolean isSub(TreeNode A, TreeNode B) {
        // 在isSubStructure方法中调isSub传入的B都是非空的，因此这里如果 B==null 一定以由于isSub自己的递归调用时B已经被匹配完了，因此返回true
        if (B == null) {
            return true;
        }
        // 如果B还没匹配完，A却先结束了，则匹配失败，返回false
        if (A == null) {
            return false;
        }
        // 如果A，B都不为null，则B要能匹配上，必须当前节点值、左右子树都能匹配上
        return A.val == B.val && isSub(A.left, B.left) && isSub(A.right, B.right);
    }
}
