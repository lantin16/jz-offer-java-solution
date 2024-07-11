package codec;

/**
 * LCR 156. 序列化与反序列化二叉树
 *
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 */

import def.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS 层序遍历
 * 为完整表示二叉树，考虑将叶节点下的 null 也记录。在此基础上，对于列表中任意某节点 node ，其左子节点 node.left 和右子节点 node.right 在序列中的位置都是 唯一确定 的。
 */
public class Codec {
    private Queue<TreeNode> nq; // 存储节点的队列

    public Codec() {
        nq = new LinkedList<>();
    }

    // Encodes a tree to a single string.
    // 时间复杂度：O(n)，其中n为节点个数。分析：层序遍历需要遍历所有节点
    // 空间复杂度：O(n)，其中n为节点个数。分析：辅助队列中最多同时存储 n+1 个null节点
    public String serialize(TreeNode root) {
        if (root == null) {
            return "n";
        }
        StringBuilder sb = new StringBuilder();
        // 层序遍历
        nq.offer(root);
        while(!nq.isEmpty()) {
            TreeNode cur = nq.poll();
            if (cur == null) {
                sb.append("n,");
                continue;
            }
            // 将当前节点值加入sb并以逗号分隔，同时将左右孩子入队（无论是不是null）
            sb.append(cur.val).append(",");
            nq.offer(cur.left);
            nq.offer(cur.right);
        }
        // 去掉最后一个值后面的逗号
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();   // 输出形式：1,2,3,n,n,4,5
    }

    // Decodes your encoded data to tree.
    // 时间复杂度：O(n)，其中n为节点个数。分析：按层构建二叉树需要遍历整个strs ，其长度最大为 2n+1 （n个有效节点和n+1个null）。
    // 空间复杂度：O(n)，其中n为节点个数。分析：辅助队列中最多同时存储一层中最多的有效节点个数
    public TreeNode deserialize(String data) {
        if (data == null || data.equals("n")) {
            return null;
        }
        String[] strs = data.split(",");    // 按照逗号分割字符串
        TreeNode head = new TreeNode(Integer.parseInt(strs[0]));    // 记录头节点并入队
        nq.add(head);
        int i = 0;
        // 只要是按照上面定义的序列化规则生成的字符串就一定能发序列化成功，这里节点与左右孩子都是对应的，不存在nq还有节点，i却越界的情况
        while(!nq.isEmpty()) {
            TreeNode cur = nq.poll();
            // 从strs中顺位找到弹出节点的左右孩子（可能为null）
            if (strs[++i].equals("n")) {
                cur.left = null;    // 反序列化过程中null不会入队
            } else {    // 左孩子不为null，则创建左孩子节点并入队
                TreeNode ln = new TreeNode(Integer.parseInt(strs[i]));
                cur.left = ln;
                nq.offer(ln);
            }
            if (strs[++i].equals("n")) {
                cur.right = null;
            } else {    // 右孩子不为null，则创建右孩子节点并入队
                TreeNode rn = new TreeNode(Integer.parseInt(strs[i]));
                cur.right = rn;
                nq.offer(rn);
            }
        }
        // 后面可能还有一些null没读，不过不影响，因为读了也只是将左右指针设为null，而默认就是null了
        return head;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));