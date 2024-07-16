package fib;

/**
 * LCR 126. 斐波那契数
 *
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * - F(0) = 0，F(1) = 1
 * - F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 * 答案需要取模 1e9+7(1000000007) ，如计算初始结果为：1000000008，请返回 1。
 */

public class Solution {

    int mod = 1000000007;

    /**
     * 一维dp
     * 求余运算规则：(x + y) % p = (x % p + y % p) % p
     * 因此可以在循环过程中每次计算 sum=(a+b)⊙1000000007 ，此操作与最终返回前取余等价。
     * 但这里不能只在返回结果前取余，因为在中间计算时可能就越界了导致结果错误
     * @param n
     * @return
     */
    // public int fib(int n) {
    //     if(n <= 1) {
    //         return n;
    //     }
    //
    //     int[] dp = new int[n + 1];
    //     dp[0] = 0;
    //     dp[1] = 1;
    //     for(int i = 2; i <= n; i++) {
    //         dp[i] = (dp[i-1] + dp[i-2]) % mod;  // 注意这里为了防止相加后超过mod，因此也要取模再存入
    //     }
    //     return dp[n];
    // }


    /**
     * dp + 滚动变量
     * @param n
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int fib(int n) {
        if(n <= 1) {
            return n;
        }

        int pre2 = 0, pre1 = 1; // pre2 = f(n-2), pre1 = f(n-1)
        for(int i = 2; i <= n; i++) {
            int cur = (pre2 + pre1) % mod;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
