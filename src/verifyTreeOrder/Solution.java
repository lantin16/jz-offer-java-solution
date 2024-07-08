package verifyTreeOrder;

/**
 * LCR 152. 验证二叉搜索树的后序遍历序列
 *
 * 请实现一个函数来判断整数数组 postorder 是否为二叉搜索树的后序遍历结果。
 */

public class Solution {


    /**
     * 递归检查每个子树是否符合二叉搜索树（掌握）
     * 后序遍历的根节点就是postorder该子树对应部分的最后一个元素
     * @param postorder
     * @return
     */
    // 时间复杂度：O(n^2)，其中n为数组长度。分析：每次递归都会去掉一个根节点，且需要遍历剩余的子树节点，因此总共需要遍历 n + (n - 1) + ... + 2 + 1 = O(n^2)
    // 空间复杂度：O(n)，其中n为数组长度。分析：最差情况下，树退化成链表，递归深度达到 n。
    // public boolean verifyTreeOrder(int[] postorder) {
    //     return verifyPostOrder(postorder, 0, postorder.length - 1);
    // }

    /**
     * 检查子树是否符合二叉搜索树，重点是根据根节点的值找到左右子树的分界点
     * 即左子树的左子树中所有节点的值 < 根节点的值；右子树中所有节点的值 > 根节点的值；其左、右子树也分别为二叉搜索树。
     * @param postorder
     * @param ll 左子树的左边界
     * @param rootIdx   根节点索引
     * @return
     */
    // private boolean verifyPostOrder(int[] postorder, int ll, int rootIdx) {
    //     // 子树节点数量 <= 1，无需继续判断了，返回true
    //     if(ll >= rootIdx) {
    //         return true;
    //     }
    //
    //     int i = ll; // 遍历指针，从左子树的左边界开始向右移动
    //     while(postorder[i] < postorder[rootIdx]) {
    //         i++;
    //     }
    //     // 跳出循环时，i指向ll开始第一个大于等于根节点的数（最晚指向根节点），即右子树的左边界（若没有右子树则指向的是根节点）
    //
    //     int rl = i; // 右子树的左边界
    //     while(postorder[i] > postorder[rootIdx]) {
    //         i++;
    //     }
    //     // 跳出循环时，i指向rl开始第一个小于等于根节点的数（最晚指向根节点）
    //     // 右子树中所有节点都大于根节点才是正常情况，此时i应该最后走到rootIdx，如果没有走到说明已经不符合二叉搜索树了
    //
    //     return i == rootIdx && verifyPostOrder(postorder, ll, rl - 1) && verifyPostOrder(postorder, rl, rootIdx - 1);
    // }


    /**
     * 思路参考：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solutions/150225/mian-shi-ti-33-er-cha-sou-suo-shu-de-hou-xu-bian-6/comments/1247826
     * 模拟构造BST（二叉搜索树），并不是真的构造，而是按照 后序遍历的倒序，即 根 -> 右 -> 左，来按顺序判断是否符合BST的结构
     * 符合则移除根节点（表现为rootIdx前移），然后递归按顺序判断右子树和左子树，如果根节点不符合则直接返回
     * 最后如果所有节点全被移除（表现为rootIdx移到了-1）则说明所有节点都检查通过，一旦有节点不符合上下界，则不会执行到rootIdx--，最后rootIdx肯定>=0
     */
    // 时间复杂度：O(n)，其中n为数组长度。分析：最差情况所有节点都需要遍历一遍。
    // 空间复杂度：O(n)，其中n为数组长度。分析：递归的深度和树的深度相关，最差情况下，树退化成链表，递归深度达到 n。
    private int rootIdx;
    public boolean verifyTreeOrder(int[] postorder) {
        rootIdx = postorder.length - 1;
        build(postorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return rootIdx < 0;
    }

    /**
     * 检查子树是否符合上下界约束
     * @param postorder
     * @param lb 该子树节点的下界
     * @param ub 该子树节点的上界
     */
    private void build(int[] postorder, int lb, int ub) {
        // 所有节点都检查完了
        if (rootIdx < 0) {
            return;
        }
        int rootVal = postorder[rootIdx];   // 根节点的值
        // 不满足 lb < rootVal < ub 都直接返回，注意节点值均不相等，所以必须满足严格的大小于
        if (rootVal <= lb || rootVal >= ub) {
            return; // 如果检查到有不符合的在这里就提前返回了，不会执行下面的rootIdx前移代码，最终rootIdx会>=0
        }
        rootIdx--;  // 移除列表的rootIdx值（根节点）
        // 右子树
        build(postorder, rootVal, ub);
        // 左子树（只有当右子树的所有节点检查完后才会来检查左子树，所以到这里rootIdx一定指向的左子树的根节点）
        build(postorder, lb, rootVal);
    }
}
