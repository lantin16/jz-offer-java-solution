package decorateRecord_150;

import def.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LCR 150. 彩灯装饰记录 II
 *
 * 一棵圣诞树记作根节点为 root 的二叉树，节点值为该位置装饰彩灯的颜色编号。请按照从左到右的顺序返回每一层彩灯编号，每一层的结果记录于一行。
 */

public class Solution {

    /**
     * 层序遍历的基础上记录每层的节点数，以便将一层节点出队完后可以进行处理
     * @param root
     * @return
     */
    // 时间复杂度：O(n)，其中n为节点个数
    // 空间复杂度：O(n)，其中n为节点个数
    public List<List<Integer>> decorateRecord(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> l = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int size = q.size();    // 当前行的节点数
            List<Integer> il = new ArrayList<>();   // 用于记录当前行的节点val
            while(size > 0) {   // 将当前行的节点出队完
                TreeNode cur = q.poll();
                il.add(cur.val);
                if(cur.left != null) {
                    q.offer(cur.left);
                }
                if(cur.right != null) {
                    q.offer(cur.right);
                }
                size--;
            }
            l.add(il);  // 添加一行的数据
        }
        return l;
    }
}
