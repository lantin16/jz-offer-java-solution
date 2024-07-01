package copyRandomList;


import def.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * LCR 154. 复杂链表的复制
 *
 * 请实现 copyRandomList 函数，复制一个复杂链表。
 * 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 *
 * 难点在于新链表中random指针的构建, 如何根据原链表中节点的random指针找到新链表中该指向哪个节点
 */

public class Solution {

    /**
     * 哈希表（好理解，掌握）
     * 建立旧链表中节点到新链表中对应节点的映射，以便快速根据旧链表节点找到复制链表中的对应节点
     * @param head
     * @return
     */
    // 时间复杂度：O(n)，其中n为链表长度。分析：共遍历两次链表
    // 空间复杂度：O(n)，其中n为链表长度。分析：哈希表中的键值对个数为n
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> old2new = new HashMap<>();  // 原链表节点 和 新链表对应节点 的键值对映射关系
        Node cur = head;   // 遍历指针
        // 第一次遍历，复制节点，并建立旧节点到新节点的映射关系
        while(cur != null) {
            old2new.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        // 第二次遍历，根据映射关系构建新链表的next和random指针
        cur = head;
        while(cur != null) {
            old2new.get(cur).next = old2new.get(cur.next);
            old2new.get(cur).random = old2new.get(cur.random);
            cur = cur.next;
        }
        return old2new.get(head);   // 返回新链表的头节点
    }


    /**
     * 拼接 + 拆分 （巧妙,但代码容易写错，掌握）
     * 构建 原节点 1 -> 新节点 1 -> 原节点 2 -> 新节点 2 -> …… 的拼接链表，如此便可在访问原节点的 random 指向节点的同时找到新对应新节点的 random 指向节点。
     *
     * @param head
     * @return
     */
    // 时间复杂度: O(n),其中n为链表长度。分析：共遍历三次链表，使用 O(n) 时间。
    // 空间复杂度: O(1)
    // public Node copyRandomList(Node head) {
    //     if (head == null) {
    //         return null;
    //     }
    //     Node cur = head;
    //     // 复制各节点并构建拼接链表
    //     while(cur != null) {
    //         Node node = new Node(cur.val);  // 复制cur
    //         node.next = cur.next;   // 将cur对应的复制节点插入到cur的后面并接上cur原来的next节点
    //         cur.next = node;
    //         cur = node.next;
    //     }
    //
    //     // 设置复制节点的random指针,在拼接链表中,原节点的random指针指向的节点的下一个节点就是新节点random指针应该指向的节点
    //     cur = head;
    //     while(cur != null) {
    //         if (cur.random != null) {
    //             cur.next.random = cur.random.next;
    //         }
    //         cur = cur.next.next;
    //     }
    //
    //     // 将原链表与新链表拆开
    //     cur = head;
    //     Node newHead = head.next;
    //     Node next = cur.next.next;
    //     while(next != null) {
    //         cur.next.next = next.next;
    //         cur.next = next;
    //         cur = next;
    //         next = next.next.next;
    //     }
    //     // 跳出循环后最后一个节点与其复制节点还需要单独分开
    //     cur.next = null;
    //     return newHead; // 返回新链表的头节点
    // }
}
