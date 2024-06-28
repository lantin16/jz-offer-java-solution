package trainningPlan_141;

import def.ListNode;

/**
 * LCR 141. 训练计划 III
 *
 * 给定一个头节点为 head 的单链表用于记录一系列核心肌群训练编号，请将该系列训练编号 倒序 记录于链表并返回。
 */

public class Solution {

    /**
     * 反转链表
     * @param head
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public ListNode trainningPlan(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode pre = null, p = head, q = head.next;
        while(q != null) {
            p.next = pre;
            pre = p;
            p = q;
            q = q.next;
        }
        p.next = pre;   // 退出循环时还需要将p的next
        return p;
    }
}
