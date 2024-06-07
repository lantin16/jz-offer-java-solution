package cuttingBamboo;


/**
 * LCR 131. 砍竹子 I
 * 现需要将一根长为正整数 bamboo_len 的竹子砍为若干段，每段长度均为正整数。请返回每段竹子长度的最大乘积是多少。
 * 必须得砍成至少两段，不能不砍
 */

public class Solution {

    /**
     * 动态规划
     *
     * 优化：
     * 因为拆分一个数 n 使之乘积最大，那么一定是拆分成 m 个近似相同的子数相乘才是最大的。（数学可证明）
     * 因此dp[i]的最大值一定出现在相近的子数相乘，而题目规定最少要拆成2个数的和，所以j取到 i/2 就可以了，j取后面更大的数不可能是更大的dp[i]
     *
     * @param bamboo_len
     * @return
     */
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(n)
    public int cuttingBamboo(int bamboo_len) {
        // dp[i]: 长度为i的竹子砍完后最大乘积（至少砍成两段）
        int[] dp = new int[bamboo_len + 1];

        // 递推公式：
        // 对于总长度为i的一段，考虑最后一段的长度j（这一段不继续分），除去最后一段j，还剩下长度 i-j：
        // 1. 如果前面长度 i-j也不分了作为单独的一段，那么i就分成了j和i-j两段，乘积就为(i-j)*j
        // 2. 如果前面长度 i-j还继续分，那么乘积最大就为dp[i-j]*j，由于dp代表的就是把一个长度至少分两段的最大乘积，因此dp[i-j]代表i-j一定会再分

        // 初始化：dp[0] = 0, dp[1] = 0，因为要求每一段长度都为正整数，且至少分两段，所以长度为1分不出来，乘积就为0

        // 遍历顺序：从短往长遍历
        for(int i = 2; i <= bamboo_len; i++) {
            // 考虑最后一段的长度j
            for(int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i-j] * j));
            }
        }

        return dp[bamboo_len];
    }
}
