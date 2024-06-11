package cuttingBamboo_131;


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
    // public int cuttingBamboo(int bamboo_len) {
    //     // dp[i]: 长度为i的竹子砍完后最大乘积（至少砍成两段）
    //     int[] dp = new int[bamboo_len + 1];
    //
    //     // 递推公式：
    //     // 对于总长度为i的一段，考虑最后一段的长度j（这一段不继续分），除去最后一段j，还剩下长度 i-j：
    //     // 1. 如果前面长度 i-j也不分了作为单独的一段，那么i就分成了j和i-j两段，乘积就为(i-j)*j
    //     // 2. 如果前面长度 i-j还继续分，那么乘积最大就为dp[i-j]*j，由于dp代表的就是把一个长度至少分两段的最大乘积，因此dp[i-j]代表i-j一定会再分
    //
    //     // 初始化：dp[0] = 0, dp[1] = 0，因为要求每一段长度都为正整数，且至少分两段，所以长度为1分不出来，乘积就为0
    //
    //     // 遍历顺序：从短往长遍历
    //     for(int i = 2; i <= bamboo_len; i++) {
    //         // 考虑最后一段的长度j
    //         for(int j = 1; j <= i / 2; j++) {
    //             dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i-j] * j));
    //         }
    //     }
    //
    //     return dp[bamboo_len];
    // }


    /**
     * 贪心，尽可能拆成长度为3的绳段最优
     * 基于以下两点推论：
     * - 当所有绳段长度相等时，乘积最大。
     * - 最优的绳段长度为 3
     *
     * 关于其数学证明可参考：https://leetcode.cn/problems/jian-sheng-zi-lcof/solutions/104809/mian-shi-ti-14-i-jian-sheng-zi-tan-xin-si-xiang-by
     *
     * @param bamboo_len
     * @return
     */
    // 时间复杂度：O(1)。分析：仅有求整、求余、次方运算
    // 空间复杂度：O(1)。
    public int cuttingBamboo(int bamboo_len) {
        if(bamboo_len <= 3) {   // 长度小于3的由于要求必须切分故乘积最大只能为 len - 1
            return bamboo_len - 1;
        }
        // bamboo_len = 3 * a + b
        int a = bamboo_len / 3;
        int b = bamboo_len % 3;
        if(b == 1) {    // 余数为1则从前面拿出一个3让凑一块拆成 2*2 乘积更大
            return (int)Math.pow(3, a - 1) * 4;
        } else if (b == 2) {    // 余数为2，不继续拆
            return (int)Math.pow(3, a) * 2;
        } else {    // 余数为0，即总长度为3的整数倍，最优
            return (int)Math.pow(3, a);
        }
    }



}
