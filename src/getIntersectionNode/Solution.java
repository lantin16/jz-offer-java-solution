package getIntersectionNode;

import def.ListNode;

/**
 * LCR 171. 训练计划 V
 * <p>
 * 某教练同时带教两位学员，分别以链表 l1、l2 记录了两套核心肌群训练计划，节点值为训练项目编号。
 * 两套计划仅有前半部分热身项目不同，后续正式训练项目相同。请设计一个程序找出并返回第一个正式训练项目编号。如果两个链表不存在相交节点，返回 null 。
 */

public class Solution {

    /**
     * 先计算长度差，然后长的先走相差步数，然后一起走，如果有相交会同时到达
     * @param headA
     * @param headB
     * @return
     */
    // 时间复杂度：O(m + n)，其中 m, n 分别为链表的长度
    // 空间复杂度：O(1)
    // ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //     int l1 = 0, l2 = 0; // 记录两条链表的长度
    //     ListNode p = headA, q = headB;
    //     // 计算两条链表的长度
    //     while (p != null) {
    //         l1++;
    //         p = p.next;
    //     }
    //     while (q != null) {
    //         l2++;
    //         q = q.next;
    //     }
    //
    //     // p指向长的，q指向短的
    //     if (l1 > l2) {
    //         p = headA;
    //         q = headB;
    //     } else {
    //         p = headB;
    //         q = headA;
    //     }
    //
    //     // p先走 |l1 - l2| 步
    //     int step = Math.abs(l1 - l2);
    //     while (p != null) {
    //         if (step > 0) {
    //             p = p.next;
    //             step--;
    //         } else {    // 先走完|l1-l2|步后p，q再一起走
    //             if (p == q) {
    //                 return p;
    //             } else {
    //                 p = p.next;
    //                 q = q.next;
    //             }
    //         }
    //     }
    //     return null;
    // }


    /**
     * 双指针（妙极！）
     * https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/solutions/627084/jian-zhi-offer-52-liang-ge-lian-biao-de-gcruu
     * @param headA
     * @param headB
     * @return
     */
    // 时间复杂度：O(m + n)，其中 m, n 分别为链表的长度。分析：最差情况下p，q需要将两个链表全遍历完
    // 空间复杂度：O(1)
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA, q = headB;
        while(p != q) {
            // p，q如果遍历完了自己的链表，再去遍历另一条
            p = p != null ? p.next : headB;
            q = q != null ? q.next : headA;
        }
        // 退出循环时p，q要么都指向第一个公共节点，要么都为null代表没有公共节点
        return p;
    }
}
