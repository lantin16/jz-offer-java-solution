package pathTarget;

import def.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * LCR 153. 二叉树中和为目标值的路径
 *
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 */

public class Solution {

    /**
     * 回溯法/dfs
     * 先序遍历 + 路径记录
     * 由于节点值可能有负数，因此必须遍历到叶子节点才知道是否满足和为target
     */
    // 时间复杂度：O(n)，其中n为节点个数。分析：先序遍历所有节点都会访问到
    // 时间复杂度：O(h)，其中h为树高。分析：递归深度取决于树的高度，最差情况下树退化成链表，使用O(n)栈空间
    List<List<Integer>> res;
    List<Integer> path;
    public List<List<Integer>> pathTarget(TreeNode root, int target) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        dfs(root, target);
        return res;
    }

    /**
     * 先序遍历
     * @param cur
     * @param target 当前目标值
     */
    public void dfs(TreeNode cur, int target) {
        if (cur == null) {  // 此处不一定是叶子节点，因此不要在这里判断target
            return;
        }

        // 尝试将当前节点加入路径
        path.add(cur.val);
        target -= cur.val;

        // 如果当前节点是叶子节点且到此刚好满足路径和为target
        if(target == 0 && cur.left == null && cur.right == null) {
            // 注意这里不要将 path 直接加入了 res，因为后续回溯 path 会改变，res 中的 path 对象也会随之改变
            // 因此这里要加入的是path的拷贝
            res.add(new ArrayList<>(path));
        }
        // 继续向左右子树深入
        dfs(cur.left, target);
        dfs(cur.right, target);
        // 回溯，将当前节点从path中删除，尝试其他路径
        path.remove(path.size() - 1);
    }
}
