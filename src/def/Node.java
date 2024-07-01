package def;

/**
 * 带有random指针的复杂链表节点
 * Definition for a Node.
 */
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
