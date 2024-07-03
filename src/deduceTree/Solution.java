package deduceTree;


import def.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * LCR 124. 推理二叉树
 * 某二叉树的先序遍历结果记录于整数数组 preorder，它的中序遍历结果记录于整数数组 inorder。请根据 preorder 和 inorder 的提示构造出这棵二叉树并返回其根节点。
 * 注意：preorder 和 inorder 中均 [不含重复数字]。
 */

public class Solution {

    /**
     * 递归
     * 根据一棵二叉树的先序和中序遍历可以唯一确定这棵树
     * 1. 先在先序遍历中找到根节点
     * 2. 再拿着根节点去中序遍历中找到对应位置，然后中序遍历结果就被分成了两边，分别是该根节点的左右子树中的节点
     * 3. 重复上述两步不断推理出子树
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode deduceTree(int[] preorder, int[] inorder) {
        // return deduce(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);

        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            idxMap.put(inorder[i], i);
        }
        return deduce(preorder, 0, idxMap, 0, inorder.length - 1);
    }

    /**
     * 根据中序遍历和先序遍历推理二叉树结构
     * 这种写法每次都要遍历中序遍历的对应部分来找出根节点在其中的下标以便分开左右子树，比较耗时
     * @param preorder
     * @param pl 目前待推理的子树部分在先序遍历中的左边界
     * @param pr 目前待推理的子树部分在先序遍历中的右边界
     * @param inorder
     * @param il 目前待推理的子树部分在中序遍历中的左边界
     * @param ir 目前待推理的子树部分在中序遍历中的右边界
     * @return
     */
    // private TreeNode deduce(int[] preorder, int pl, int pr, int[] inorder, int il, int ir) {
    //     if(pl > pr) {
    //         return null;
    //     }
    //     int rv = preorder[pl];   // 在先序遍历中找到子树的根节点val
    //     int rIdx = -1;   // 根节点在inorder中的下标
    //     // 遍历inorder对应部分找到根节点在其中的下标
    //     for(int i = il; i <= ir; i++) {
    //         if(inorder[i] == rv) {
    //             rIdx = i;
    //             break;
    //         }
    //     }
    //     int lLen = rIdx - il, rLen = ir - rIdx; // 中序遍历被根节点分开的左右两部分的长度（对应左右子树中的节点）
    //     TreeNode root = new TreeNode(rv);
    //     // 递归生成左右子树
    //     root.left = deduce(preorder, pl + 1, pl + lLen, inorder, il, rIdx - 1);
    //     root.right = deduce(preorder, pr - rLen + 1, pr, inorder, rIdx + 1, ir);
    //     return root;
    // }


    /**
     * 这种写法为了提高在中序遍历中查找根节点位置的效率，采用哈希表存储中序遍历的值与索引的映射，查找操作的时间复杂度为 O(1) ，空间换时间
     * @param preorder
     * @param rootIdx  根节点在前序遍历的索引
     * @param idxMap 中序遍历的值到索引的映射
     * @param il 当前子树在中序遍历的左边界
     * @param ir 当前子树在中序遍历的右边界
     * @return
     */
    // 时间复杂度：O(n)，其中n为节点个数。分析：建立哈希表遍历inorder时间O(n)，每层递归建立节点占用O(1)，共建立n个节点，故时间也为O(n)
    // 空间复杂度：O(n)，其中n为节点个数。分析：每次递归确定一个节点，总共确定n个，故递归深度为n，需要栈空间为O(n)，辅助哈希表占用空间O(n)
    private TreeNode deduce(int[] preorder, int rootIdx, Map<Integer, Integer> idxMap, int il, int ir) {
        if(il > ir) {
            return null;
        }
        int rv = preorder[rootIdx];   // 在先序遍历中找到子树的根节点val
        int rIdx = idxMap.get(rv);   // 根据map快速找到根节点在inorder中的索引
        TreeNode root = new TreeNode(rv);
        // 递归生成左右子树
        root.left = deduce(preorder, rootIdx + 1, idxMap, il, rIdx - 1);
        root.right = deduce(preorder, rootIdx + rIdx - il + 1, idxMap, rIdx + 1, ir);   // 划分出的右子树在先序遍历中的索引为上一个根的索引 + 左子树的长度 + 1
        return root;
    }
}
