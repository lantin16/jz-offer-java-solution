package maxSales;

/**
 * LCR 161. 连续天数的最高销售额
 *
 * 某公司每日销售额记于整数数组 sales，请返回所有 连续 一或多天销售额总和的最大值。
 * 要求实现时间复杂度为 O(n) 的算法。
 */

public class Solution {
    /**
     * 一维dp
     * @param sales
     * @return
     */
    // public int maxSales(int[] sales) {
    //     int n = sales.length;
    //     // dp[i]：以sales[i]结尾的连续天数最大销售额
    //     int[] dp = new int[n];
    //     int res = sales[0];
    //     dp[0] = sales[0];
    //     for(int i = 1; i < n; i++) {
    //         dp[i] = Math.max(dp[i-1] + sales[i], sales[i]);
    //         res = Math.max(res, dp[i]);
    //     }
    //     return res;
    // }


    /**
     * dp + 滚动变量
     * @param sales
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int maxSales(int[] sales) {
        int n = sales.length;
        int pre = 0;
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            int cur = Math.max(pre + sales[i], sales[i]);
            res = Math.max(res, cur);
            pre = cur;
        }
        return res;
    }
}
