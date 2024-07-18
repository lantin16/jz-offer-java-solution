package statisticsProbability;

import java.util.Arrays;

/**
 * LCR 185. 统计结果概率
 *
 * 你选择掷出 num 个色子，请返回所有点数总和的概率。
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 num 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 */

public class Solution {

    /**
     * 二维dp
     * @param num
     * @return
     */
    // 时间复杂度：O(n^2)，其中 n=num。分析：外层循环 n 次，内层循环 不超过 5n+1 次，再内层6次
    // 空间复杂度：O(n^2)，其中 n=num。
    public double[] statisticsProbability(int num) {
        // dp[i][j]：用i个骰子摇出总和为j的概率
        double[][] dp = new double[num + 1][num * 6 + 1];

        // 全初始化：初始化i=1，其他全为0
        for(int j = 1; j <= 6; j++) {
            dp[1][j] = 1.0 / 6;
        }

        // 先遍历骰子数，再遍历总点数
        for(int i = 2; i <= num; i++) {
            for(int j = i; j <= 6 * i; j++) { // 对于骰子数i，总和只会为[i, 6i]，最小i个1，最大i个6
                // 考虑最后一个骰子的点数
                for(int k = 1; k <= 6; k++) {
                    if(j >= k) {
                        dp[i][j] += dp[i-1][j-k];
                    }
                }
                dp[i][j] /= 6;
            }
        }

        return Arrays.copyOfRange(dp[num], num, num * 6 + 1);   // 拷贝原数组[from, to)作为新数组
    }
}
