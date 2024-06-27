package deleteNode;

import def.ListNode;

/**
 * LCR 136. 删除链表的节点
 *
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * 返回删除后的链表的头节点。
 *
 * 题目保证链表中节点的值互不相同
 */

public class Solution {

    /**
     * 从前往后查找val，然后将该节点的前一个节点的next指向后一个节点即可
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy, p = head;
        while(p != null) {
            if(p.val == val) {
                pre.next = p.next;
                break;
            }
            pre = p;
            p = p.next;
        }
        return dummy.next;
    }
}
