package reverseBookList;

import def.ListNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LCR 123. 图书整理 I
 * 书店店员有一张链表形式的书单，每个节点代表一本书，节点中的值表示书的编号。为更方便整理书架，店员需要将书单倒过来排列，
 * 就可以从最后一本书开始整理，逐一将书放回到书架上。请倒序返回这个书单链表。
 */

public class Solution {

    /**
     * 辅助栈
     * 从头向后遍历单链表的同时将节点值加入栈中，然后从栈中弹出放入结果数组中
     * @param head
     * @return
     */
    // 时间复杂度：O(n)，其中n为链表节点个数。
    // 空间复杂度：O(n)。分析：使用了O(n)额外空间的栈
    // public int[] reverseBookList(ListNode head) {
    //     Deque<Integer> stk = new LinkedList<>();
    //     ListNode p = head;
    //     while(p != null) {
    //         stk.push(p.val);
    //         p = p.next;
    //     }
    //     int size = stk.size();
    //     int[] res = new int[size];
    //     for(int i = 0; i < size; i++) { // 注意这里不能写 i < stk.size()，因为随着栈不断弹出元素，size是在变化的
    //         res[i] = stk.pop();
    //     }
    //     return res;
    // }


    /**
     * 先反转链表，再添加到结果数组
     * @param head
     * @return
     */
    // 时间复杂度：O(n)。分析：总共遍历两次所有链表节点
    // 空间复杂度：O(1)
    // public int[] reverseBookList(ListNode head) {
    //     if(head == null) {
    //         return new int[0];
    //     }
    //     ListNode pre = null, p = head, q = head.next;   // 三者的顺序是 pre -> p -> q
    //     int cnt = 1;    // 计算节点数
    //     // 反转链表
    //     while(q != null) {
    //         p.next = pre;
    //         pre = p;
    //         p = q;
    //         q = q.next;
    //         cnt++;
    //     }
    //     p.next = pre;   // 退出循环时pre和p分别指向倒数第二、第一个节点，因此还需要将p的next指向pre
    //
    //     // 从新的头节点p开始遍历反转后的链表并依次加入结果数组
    //     int[] res = new int[cnt];
    //     int i = 0;
    //     while(p != null) {
    //         res[i++] = p.val;
    //         p = p.next;
    //     }
    //     return res;
    // }


    /**
     * 递归
     * 利用递归，先递推至链表末端；回溯时，依次将节点值加入列表，即可实现链表值的倒序输出。
     * @param head
     * @return
     */
    // 时间复杂度：O(n)。分析：遍历链表需要递归n次
    // 空间复杂度：O(n).分析：系统递归需要使用O(n)的栈空间
    public int[] reverseBookList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        addVal(head, list);
        // List<Integer>转int[]
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    private void addVal(ListNode p, List<Integer> list) {
        if(p == null) {
            return;
        }
        // 先往后递归，在回溯时才加入本节点的val
        addVal(p.next, list);
        list.add(p.val);
    }


}
