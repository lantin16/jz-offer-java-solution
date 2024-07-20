package bestTiming;

/**
 * LCR 188. 买卖芯片的最佳时机
 *
 * 数组 prices 记录了某芯片近期的交易价格，其中 prices[i] 表示的 i 天该芯片的价格。你只能选择 某一天 买入芯片，并选择在 未来的某一个不同的日子 卖出该芯片。
 * 请设计一个算法计算并返回你从这笔交易中能获取的最大利润。
 *
 * 如果你不能获取任何利润，返回 0。
 */

public class Solution {

    /**
     * 一维dp
     * @param prices
     * @return
     */
    // public int bestTiming(int[] prices) {
    //     int n = prices.length;
    //     if(n == 0) {
    //         return 0;
    //     }
    //     // dp[i]：在第i天及之前卖出的最大利润（i从0开始）
    //     int[] dp = new int[n];
    //     dp[0] = 0;  // 按理说第0天不能卖出，这里设为0方便比较
    //     int preMin = prices[0]; // 记录第i天前的最低价格
    //
    //     for(int i = 1; i < n; i++) {
    //         dp[i] = Math.max(dp[i-1], prices[i] - preMin);
    //         preMin = Math.min(preMin, prices[i]);
    //     }
    //     return dp[n-1];
    // }


    /**
     * dp + 滚动变量
     * @param prices
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int bestTiming(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int preMin = prices[0]; // 记录第i天前的最低价格
        int preMaxProfit = 0;   // 记录第i天前卖出的最大利润

        for(int i = 1; i < prices.length; i++) {
            preMaxProfit = Math.max(preMaxProfit, prices[i] - preMin);  // 第i天卖出的最大利润等于prices[i] - preMin
            preMin = Math.min(preMin, prices[i]);
        }
        return preMaxProfit;
    }
}
