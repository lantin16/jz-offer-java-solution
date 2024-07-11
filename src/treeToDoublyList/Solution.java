package treeToDoublyList;

import java.util.ArrayList;
import java.util.List;

/**
 * LCR 155. 将二叉搜索树转化为排序的双向链表
 *
 * 将一个 二叉搜索树 就地转化为一个 已排序的双向循环链表 。
 * 对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 * 特别地，我们希望可以 就地 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。
 */

public class Solution {


    /**
     * 先中序遍历BST并将节点按照顺序保存到List中，然后根据List中的前后关系连接前驱和后继指针
     * 中序遍历二叉搜索树得到升序排列
     */
    // 时间复杂度：O(n)，其中n为节点个数。分析：先序遍历要遍历所有节点，连接每个节点的左右指针也需要遍历所有节点
    // 空间复杂度：O(n)，其中n为节点个数。分析：辅助按序保存节点的List需要存储所有节点
    // List<Node> sortNodes;
    // public Node treeToDoublyList(Node root) {
    //     if(root == null) {
    //         return null;
    //     }
    //     sortNodes = new ArrayList<>();
    //     inorder(root);
    //     int size = sortNodes.size();
    //     Node head = sortNodes.get(0);
    //     Node tail = sortNodes.get(size - 1);
    //     head.left = tail;
    //     tail.right = head;
    //     if(size <= 2) {
    //         head.right = tail;
    //         tail.left = head;
    //     } else {
    //         // 至少3个节点
    //         head.right = sortNodes.get(1);
    //         tail.left = sortNodes.get(size - 2);
    //         for(int i = 1; i < size - 1; i++) {
    //             sortNodes.get(i).left = sortNodes.get(i - 1);
    //             sortNodes.get(i).right = sortNodes.get(i + 1);
    //         }
    //     }
    //     return head;
    //
    // }
    //
    // public void inorder(Node root) {
    //     if(root == null) {
    //         return;
    //     }
    //     inorder(root.left);
    //     sortNodes.add(root);
    //     inorder(root.right);
    // }


    /**
     * 中序遍历，每次计算子树序列的第一个和最后一个节点
     *
     * 设当前节点为root，则其前驱节点和后继节点为：
     * 1. 若root有左子树，则root的前驱节点是其左子树的最右下的节点（左子树升序排列的最后一个节点）
     *    若root没有左子树，则root的前驱节点就需要向父节点往上寻找
     * 2. 若root有右子树，则root的后继节点是其右子树的最左下的节点（右子树升序排列的第一个节点）
     *    若root没有右子树，则root的后继节点就需要向父节点往上寻找
     *
     * @param root
     * @return
     */
    // 时间复杂度：O(n)，其中n为节点个数。分析：先序遍历要遍历所有节点，连接每个节点的左右指针也需要遍历所有节点
    // 空间复杂度：O(n)。分析：递归栈最差需要O(n)空间
    // public Node treeToDoublyList(Node root) {
    //     if(root == null) {
    //         return null;
    //     }
    //     Node[] ht = inorder(root);  // 整个升序排序的首尾节点
    //     // 首尾互连
    //     ht[0].left = ht[1];
    //     ht[1].right = ht[0];
    //     return ht[0];
    // }
    //
    // /**
    //  * 每次中序遍历，返回以root为根的子树的中序遍历的第一个节点和最后一个节点，并且将root与其前驱和后继节点互连
    //  * @param root
    //  * @return 返回长度为2的Node数组，第一个元素为以root为根的子树的第一个节点，第二个元素为子树最后一个节点
    //  */
    // public Node[] inorder(Node root) {
    //     Node[] res = new Node[2];
    //     if(root == null) {
    //         return res;
    //     }
    //
    //     // 对左子树中序遍历，将左子树内部节点互连并返回左子树的第一和最后一个节点
    //     Node[] leftRes = inorder(root.left);
    //     // 如果root有左子树，则以root为根的子树的第一个节点就是其左子树的第一个节点，否则为root自己
    //     res[0] = leftRes[0] == null ? root : leftRes[0];
    //
    //     // 对右子树中序遍历，将右子树内部节点互连并返回右子树的第一和最后一个节点
    //     Node[] rightRes = inorder(root.right);
    //     // 如果root有右子树，则以root为根的子树的最后一个节点就是其右子树的最后一个节点，否则为root自己
    //     res[1] = rightRes[1] == null ? root : rightRes[1];
    //
    //     // 将root的左右指针分别连到其左子树的最后一个节点（前驱）和其右子树的第一个节点（后继）
    //     // 如果root没有左/右子树，则左/右指针先不管（因为可能其前驱/后继节点在上面的某层父节点，现在不确定所以连不了），就等到回溯到其真正的前驱/后继节点后交给它们来连
    //     if(leftRes[1] != null) {    // 如果root有左子树，则将root与左子树最后一个节点（前驱）互连
    //         root.left = leftRes[1];
    //         leftRes[1].right = root;    // root左子树有些前驱节点的右指针还没确定，所以在这里就连上
    //     }
    //     if(rightRes[0] != null) {   // 如果root有右子树，则将root与右子树第一个节点（后继）互连
    //         root.right = rightRes[0];
    //         rightRes[0].left = root;    // root右子树有些后继节点的左指针还没确定，所以在这里就连上
    //     }
    //
    //     return res;
    // }


    /**
     * 中序遍历，用pre来维护前驱节点，当pre为null时证明到了头节点
     */
    // 时间复杂度：O(n)，其中n为节点个数。分析：先序遍历要遍历所有节点，连接每个节点的左右指针也需要遍历所有节点
    // 空间复杂度：O(n)。分析：递归栈最差需要O(n)空间
    Node pre, head;
    public Node treeToDoublyList(Node root) {
        if(root == null) {
            return null;
        }

        inorder(root);
        // 结束时pre指向双向链表的尾节点，将首尾互连
        pre.right = head;
        head.left = pre;
        return head;
    }

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public void inorder(Node root) {
        if (root == null) {
            return;
        }

        inorder(root.left); // 左子树

        // pre是root的前驱节点，如果没有前驱则说明root就位于升序双向链表的头部
        if (pre == null) {
            head = root;
        } else {
            pre.right = root;
        }
        // 将root与pre互连
        root.left = pre;

        pre = root; // 更新pre，这样pre就按升序逐步遍历每个Node

        inorder(root.right);    // 右子树
    }
}
