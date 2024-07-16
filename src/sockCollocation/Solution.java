package sockCollocation;

/**
 * LCR 177. 撞色搭配
 *
 * 整数数组 sockets 记录了一个袜子礼盒的颜色分布情况，其中 sockets[i] 表示该袜子的颜色编号。礼盒中除了一款撞色搭配的袜子，每种颜色的袜子均有两只。
 * 请设计一个程序，在时间复杂度 O(n)，空间复杂度O(1) 内找到这双撞色搭配袜子的两个颜色编号。
 */

public class Solution {

    /**
     * 分组 + 异或
     * 用异或可以求出一组成对出现的数中唯一只出现一次的数，但本题数组 sockets 有 两个 只出现一次的数字，因此无法通过异或直接得到这两个数字。
     *
     * 设两个只出现一次的数字为 x , y ，由于 x !=y ，则 x 和 y 二进制至少有一位不同（即分别为 0 和 1 ），根据此位可以将 sockets 拆分为分别包含 x 和 y 的两个子数组。
     * 易知两子数组都满足 「除一个数字之外，其他数字都出现了两次」。因此，仿照以上简化问题的思路，分别对两子数组遍历执行异或操作，即可得到两个只出现一次的数字 x, y 。
     *
     * 两组数分别包括x,y，除它俩之外的其他数一定也被成对地分到这两组内，不过分到那一组并不影响，因为是成对存在，所以最后在组内求异或都会消去
     *
     * @param sockets
     * @return
     */
    // 时间复杂度：O(n)，其中n为数组的长度。分析：总共需要遍历两遍数组
    // 空间复杂度：O(1)
    public int[] sockCollocation(int[] sockets) {
        int x = 0, y = 0, n = 0, m = 1;
        // 对所有数求异或，最后结果留下 x^y，这个结果中二进制为1的位置就是x与y不同的位
        for(int num : sockets) {
            n ^= num;
        }

        // 对 n 求出从右到左第一个1并用 m 记录（之后就根据这一位将所有数分为两组）
        while((n & m) == 0) {
            m <<= 1;
        }

        // 对所有数按照该1的位置进行分组，该位为0的为一组，为1的在另一组（除x，y以外的其他成对的数肯定会被两两分到同一组）
        for(int num : sockets) {
            if((num & m) == 0) {  // 该位为0的数就与x求异或，最后留下的就是x
                x ^= num;
            } else {    // 该位为1的数就与y求异或，最后留下的就是y
                y ^= num;
            }
        }

        return new int[]{x, y};
    }
}