package iceBreakingGame;

/**
 * LCR 187. 破冰游戏
 *
 * 社团共有 num 位成员参与破冰游戏，编号为 0 ~ num-1。成员们按照编号顺序围绕圆桌而坐。
 * 社长抽取一个数字 target，从 0 号成员起开始计数，排在第 target 位的成员离开圆桌，且成员离开后从下一个成员开始计数。请返回游戏结束时最后一位成员的编号。
 */

public class Solution {

    /**
     * 约瑟夫环问题
     * 反推：最终成活的人编号会变为0（因为只有它一个），从编号0逐层向前反推每一轮淘汰前他的编号，直至推导出首轮他初始时的编号
     * f(n,m)表示有n个人，每个m个人进行环形删除，最后剩下那个人的索引号
     * 递推公式：f(n,m) = [f(n-1,m) + m] % n
     * 参考：https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solutions/178427/huan-ge-jiao-du-ju-li-jie-jue-yue-se-fu-huan-by-as
     * @param n
     * @param m
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int iceBreakingGame(int n, int m) {
        int pos = 0;    // 每轮最终成活者在该轮的编号（由于反推所以最终1个人时存活者下标肯定是0，即 f(1,m) = 0，反推结束pos会为他的初始编号）
        for (int i = 2; i <= n; i++) {  // 从最后一个人往前推，2个人时，幸存者的下标；3个人时，幸存者的下标； …… n个人时，幸存者的下标
            pos = (pos + m) % i;
        }
        return pos;
    }
}
