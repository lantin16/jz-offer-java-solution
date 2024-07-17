package jewelleryValue;

/**
 * LCR 166. 珠宝的最高价值
 *
 * 现有一个记作二维矩阵 frame 的珠宝架，其中 frame[i][j] 为该位置珠宝的价值。拿取珠宝的规则为：
 * - 只能从架子的左上角开始拿珠宝
 * - 每次可以移动到右侧或下侧的相邻位置
 * - 到达珠宝架子的右下角时，停止拿取
 * 注意：珠宝的价值都是大于 0 的。除非这个架子上没有任何珠宝，比如 frame = [[0]]。
 */

public class Solution {

    /**
     * 二维dp
     * @param frame
     * @return
     */
    // public int jewelleryValue(int[][] frame) {
    //     int m = frame.length, n = frame[0].length;
    //     // dp[i][j]：到达dp[i-1][j-1]位置拿到的最大价值
    //     int[][] dp = new int[m + 1][n + 1];
    //
    //     // 全初始化为0
    //
    //     for(int i = 1; i <= m; i++) {
    //         for(int j = 1; j <= n; j++) {
    //             dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + frame[i-1][j-1];  // 只可能从左边或上边来到这一格
    //         }
    //     }
    //     return dp[m][n];
    // }


    /**
     * 一维滚动数组
     * @param frame
     * @return
     */
    // 时间复杂度：O(mn)
    // 空间复杂度：O(n)
    public int jewelleryValue(int[][] frame) {
        int m = frame.length, n = frame[0].length;
        // dp[i][j]：到达dp[i-1][j-1]位置拿到的最大价值
        int[] dp = new int[n + 1];

        // 全初始化为0

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                dp[j] = Math.max(dp[j], dp[j-1]) + frame[i-1][j-1];  // 只可能从左边或上边来到这一格
            }
        }
        return dp[n];
    }
}
