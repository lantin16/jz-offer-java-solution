package trainningPlan_142;

import def.ListNode;

/**
 * LCR 142. 训练计划 IV
 *
 * 给定两个以 有序链表 形式记录的训练计划 l1、l2，分别记录了两套核心肌群训练项目编号，请合并这两个训练计划，按训练项目编号 升序 记录于链表并返回。
 * 注意：新链表是通过拼接给定的两个链表的所有节点组成的。
 */

public class Solution {

    /**
     * 依次比较两个链表的表头元素并将较小的连接到结果链表尾部，类似于归并排序的合并两个有序子数组
     * @param l1
     * @param l2
     * @return
     */
    // 时间复杂度：O(m + n)，其中m，n分别为l1，l2的长度。分析：最坏情况下，l1，l2的元素被交叉加入
    // 空间复杂度：O(1)
    public ListNode trainningPlan(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        // 较短的链表取完了，直接将较长的剩余部分加入到最后
        tail.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
