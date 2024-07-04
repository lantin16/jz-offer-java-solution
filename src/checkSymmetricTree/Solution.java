package checkSymmetricTree;

import def.TreeNode;

/**
 * LCR 145. 判断对称二叉树
 *
 * 请设计一个函数判断一棵二叉树是否 轴对称 。
 */

public class Solution {

    /**
     * 递归
     * @param root
     * @return
     */
    // 时间复杂度：O(n)，其中n为节点个数
    // 空间复杂度：O(h)，其中h为树的高度
    public boolean checkSymmetricTree(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode lt, TreeNode rt){
        if(lt == null && rt == null) {
            return true;
        }
        // 只有一方为null，另一方不为null则肯定不对称
        if(lt == null || rt == null) {
            return false;
        }
        return lt.val == rt.val && isSymmetric(lt.left, rt.right) && isSymmetric(lt.right, rt.left);    // 注意比较的对应关系
    }
}
