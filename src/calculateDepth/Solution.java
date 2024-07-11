package calculateDepth;

import def.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LCR 175. 计算二叉树的深度
 *
 * 某公司架构以二叉树形式记录，请返回该公司的层级数。
 */

public class Solution {

    /**
     * BFS（广度优先）
     * 层序遍历记录层数
     * @param root
     * @return
     */
    // 时间复杂度：O(n)，其中n为节点个数
    // 时间复杂度：O(n)，其中n为节点个数。分析：队列中最多存储（一层最多节点数）(n + 1) / 2 个节点。（按完全二叉树算，层数为h的情况，总结点个数 n = 2^h - 1，最下层最多 2^(h-1)个节点）
    // public int calculateDepth(TreeNode root) {
    //     if(root == null) {
    //         return 0;
    //     }
    //     int depth = 0;
    //     Queue<TreeNode> queue = new LinkedList<>();
    //     queue.offer(root);
    //     while(!queue.isEmpty()) {
    //         depth++;
    //         int size = queue.size();
    //         while(size > 0) {
    //             TreeNode cur = queue.poll();
    //             if(cur.left != null) {
    //                 queue.offer(cur.left);
    //             }
    //             if(cur.right != null) {
    //                 queue.offer(cur.right);
    //             }
    //             size--;
    //         }
    //     }
    //     return depth;
    // }


    /**
     * DFS（深度优先）
     * 代码简洁！
     * @param root
     * @return
     */
    // 时间复杂度：O(n)，其中n为节点个数
    // 空间复杂度：O(h)，其中h为树的高度。分析：递归栈空间占O(h)，最差为O(n)
    public int calculateDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(calculateDepth(root.left) + 1, calculateDepth(root.right) + 1);
    }
}
