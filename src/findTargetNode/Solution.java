package findTargetNode;

import def.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * LCR 174. 寻找二叉搜索树中的目标节点
 * 某公司组织架构以二叉搜索树形式记录，节点值为处于该职位的员工编号。请返回第 cnt 大的员工编号。
 */

public class Solution {

    /**
     * BST的中序遍历是升序排列，用List保存起来，然后倒数第从cnt个即为所求
     */
    // 时间复杂度：O(n)，其中n为节点个数
    // 空间复杂度：O(n)，其中n为节点个数
    // List<Integer> list;
    // public int findTargetNode(TreeNode root, int cnt) {
    //     list = new ArrayList<>();
    //     inorder(root);
    //     return list.get(list.size() - cnt);
    // }
    //
    // public void inorder(TreeNode root) {
    //     if(root == null) {
    //         return;
    //     }
    //     inorder(root.left);
    //     list.add(root.val);
    //     inorder(root.right);
    // }


    /**
     * BST按照 右子树 - 根 - 左子树 的顺序遍历是降序排列
     * 用一个变量计数，按此顺序访问到第cnt个节点即为所求
     */
    // 时间复杂度：O(n)，其中n为节点个数。分析：当树退化为链表时（全部为右子节点），无论 cnt 的值大小，递归深度都为 n ，占用 O(n) 时间。
    // 空间复杂度：O(n)，其中n为节点个数。分析：当树退化为链表时（全部为右子节点），系统使用 O(n) 大小的栈空间。
    int res = -1, n = 0;
    public int findTargetNode(TreeNode root, int cnt) {
        dfs(root, cnt);
        return res;
    }

    /**
     * 找到整棵BST中第cnt大的数
     * @param root
     * @param cnt
     */
    public void dfs(TreeNode root, int cnt) {
        // 越过了叶子节点或已经找到了结果就不继续递归了
        if(root == null || res != -1) {
            return;
        }
        dfs(root.right, cnt);   // 右子树
        // 访问根节点，每访问一个节点则计数n加一，一旦n到达cnt代表当前节点的val即为所求
        if(++n == cnt) {
            res = root.val;
        }
        dfs(root.left, cnt);    // 左子树
    }
}
