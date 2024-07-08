package decorateRecord;

import def.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LCR 149. 彩灯装饰记录 I
 *
 * 一棵圣诞树记作根节点为 root 的二叉树，节点值为该位置装饰彩灯的颜色编号。请按照从 左 到 右 的顺序返回每一层彩灯编号。
 */

public class Solution {

    /**
     * 树的层序遍历，借助队列
     * List<Integer> 与 int[] 互转的写法：https://blog.csdn.net/qq_41969790/article/details/107827028
     * @param root
     * @return
     */
    // 时间复杂度：O(n)，其中n为树的节点数
    // 空间复杂度：O(n)，其中n为树的节点数。分析：最差情况下，即当树为平衡二叉树时，最多有 N/2 个树节点同时在 queue 中，使用 O(N) 大小的额外空间。
    public int[] decorateRecord(TreeNode root) {
        if(root == null) {
            return new int[0];
        }
        List<Integer> l = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);  // 根节点先入队
        // 开始层序遍历，节点出队时将val加入List
        while(!q.isEmpty()) {
            TreeNode cur = q.poll();
            l.add(cur.val);
            if(cur.left != null) {
                q.offer(cur.left);
            }
            if(cur.right != null) {
                q.offer(cur.right);
            }
        }
        // List<Integer> 转 int[]
        int[] res = new int[l.size()];
        for(int i = 0; i < l.size(); i++) {
            res[i] = l.get(i);
        }
        return res;

        // return l.stream().mapToInt(i -> i).toArray();    // 这种写法提交比上面这种逐个添加要慢一些，但代码简洁
    }
}
