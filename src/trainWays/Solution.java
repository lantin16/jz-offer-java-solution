package trainWays;

/**
 * LCR 127. 跳跃训练
 *
 * 天的有氧运动训练内容是在一个长条形的平台上跳跃。平台有 num 个小格子，每次可以选择跳 一个格子 或者 两个格子。请返回在训练过程中，学员们共有多少种不同的跳跃方式。
 *
 * 结果可能过大，因此结果需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 */

public class Solution {

    int mod = 1000000007;

    /**
     * 一维dp
     * @param num
     * @return
     */
    // public int trainWays(int num) {
    //     if(num <= 1) {
    //         return 1;
    //     }
    //     int[] dp = new int[num + 1];
    //
    //     dp[0] = 1;
    //     dp[1] = 1;
    //     for(int i = 2; i <= num; i++) {
    //         dp[i] = (dp[i-2] + dp[i-1]) % mod;
    //     }
    //     return dp[num];
    // }


    /**
     * dp + 滚动变量
     * @param num
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int trainWays(int num) {
        if(num <= 1) {
            return 1;
        }

        int pre2 = 1, pre1 = 1, cur = 0;
        for(int i = 2; i <= num; i++) {
            cur = (pre2 + pre1) % mod;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
