package trainingPlan_140;

import def.ListNode;

/**
 * LCR 140. 训练计划 II
 *
 * 给定一个头节点为 head 的链表用于记录一系列核心肌群训练项目编号，请查找并返回倒数第 cnt 个训练项目编号。
 */

public class Solution {

    /**
     * 快慢指针
     * 快、慢指针初始均指向head，快指针先走cnt步，慢指针不动。当快指针走第n+1步及之后，慢指针也跟着每次走一步
     * 这样当快指针指向null出界后慢指针就指向的倒数第cnt个节点
     * @param head
     * @param cnt
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public ListNode trainingPlan(ListNode head, int cnt) {
        ListNode f = head, s = head;
        while(f != null) {
            f = f.next;
            cnt--;
            if(cnt < 0) {
                s = s.next;
            }
        }
        return s;
    }
}
