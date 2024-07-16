package trainningPlan_178;

import def.ListNode;

/**
 * LCR 178. 训练计划 VI
 * <p>
 * 教学过程中，教练示范一次，学员跟做三次。该过程被混乱剪辑后，记录于数组 actions，其中 actions[i] 表示做出该动作的人员编号。请返回教练的编号。
 */

public class Solution {

    /**
     * 统计二进制各位1出现的次数（容易理解）
     * 参考：https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solutions/215895/mian-shi-ti-56-ii-shu-zu-zhong-shu-zi-chu-xian-d-4
     * 考虑数字的二进制形式，对于出现三次的数字，各 二进制位 出现的次数都是 3 的倍数。
     * 因此，统计所有数字的各二进制位中 1 的出现次数，并对 3 求余，结果则为只出现一次的数字。
     *
     * @param actions
     * @return
     */
    // 时间复杂度：O(n)，其中n为数组长度。分析：遍历数组用O(n)，统计每个数中各位1的次数最多不超过32次，占用O(1)
    // 空间复杂度：O(1)。分析：数组count长度为32，占O(1)
    public int trainingPlan(int[] actions) {
        int[] count = new int[32];  // 记录二进制各位1出现的次数
        for(int num : actions) {
            // 统计num各位的数字中1的次数并加到count数组中（掌握）
            for(int i = 0; i < 32; i++) {
                count[i] += num & 1;
                num >>= 1;
            }
        }

        int res = 0;
        // 将二进制各位对3取模后恢复成十进制结果（掌握）
        for(int i = 31; i >= 0; i--) {
            res <<= 1;
            res |= (count[i] % 3);  // 用 | 代替 +
        }
        return res;
    }


    /**
     * 有限状态机（比较难想到）
     * 参考：参考：https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solutions/215895/mian-shi-ti-56-ii-shu-zu-zhong-shu-zi-chu-xian-d-4
     * @param actions
     * @return
     */
    // 时间复杂度：O(n)，其中n为数组长度
    // 时间复杂度：O(1)
    // public int trainingPlan(int[] actions) {
    //     int ones = 0, twos = 0;
    //     for (int action : actions) {
    //         ones = ones ^ action & ~twos;
    //         twos = twos ^ action & ~ones;
    //     }
    //     return ones;
    // }


}
