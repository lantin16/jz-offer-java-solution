package decorateRecord_151;

import def.TreeNode;

import java.util.*;

/**
 * LCR 151. 彩灯装饰记录 III
 *
 * 一棵圣诞树记作根节点为 root 的二叉树，节点值为该位置装饰彩灯的颜色编号。请按照如下规则记录彩灯装饰结果：
 * - 第一层按照从左到右的顺序记录
 * - 除第一层外每一层的记录顺序均与上一层相反。即第一层为从左到右，第二层为从右到左。
 */

public class Solution {

    /**
     * 层序遍历 + 双端队列
     * @param root
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public List<List<Integer>> decorateRecord(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> dq = new LinkedList<>();
        boolean dire = true;    // 遍历方向，dire为true则该层需要从左往右遍历，反之从右往左
        dq.addLast(root);
        while(!dq.isEmpty()) {
            int size = dq.size();   // 当前层的节点数
            List<Integer> il = new ArrayList<>();
            if(dire) {  // 从左往右遍历
                while(size > 0) {
                    TreeNode cur = dq.removeFirst();    // 当前层的节点从队首出队
                    il.add(cur.val);
                    if(cur.left != null) {  // 先左孩子
                        dq.addLast(cur.left);   // 下一层的节点从队尾入队
                    }
                    if(cur.right != null) { // 再右孩子
                        dq.addLast(cur.right);  // 下一层的节点从队尾入队
                    }
                    size--;
                }
            } else {    // 从右往左遍历
                while(size > 0) {
                    TreeNode cur = dq.removeLast(); // 当前层的节点从队尾出队
                    il.add(cur.val);
                    if(cur.right != null) { // 先右孩子
                        dq.addFirst(cur.right); // 下一层的节点从队首入队
                    }
                    if(cur.left != null) {  // 再左孩子
                        dq.addFirst(cur.left);  // 下一层的节点从队首入队
                    }
                    size--;
                }
            }
            res.add(il);
            dire ^= true;   // 方向与true取亦或即方向取反
        }
        return res;
    }
}
