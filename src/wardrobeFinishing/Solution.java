package wardrobeFinishing;

/**
 * LCR 130. 衣橱整理
 *
 * 家居整理师将待整理衣橱划分为 m x n 的二维矩阵 grid，其中 grid[i][j] 代表一个需要整理的格子。整理师自 grid[0][0] 开始 逐行逐列 地整理每个格子。
 * 整理规则为：在整理过程中，可以选择 向右移动一格 或 向下移动一格，但不能移动到衣柜之外。同时，不需要整理 digit(i) + digit(j) > cnt 的格子，其中 digit(x) 表示数字 x 的各数位之和。
 * 请返回整理师 总共需要整理多少个格子。
 *
 * 注意：本题并不是求一趟整理多少个格子，因为整理师只能向右向下走，因此不能倒转，一趟势必会有格子没整理到
 * 本题实际上求的是多趟整理的话一共需要整理多少格子，也就是说所有能到达的整理过的格子都要算上
 * 简化问题：向下向右走可以到达且需要整理的格子看作可以走的位置，不需要整理的看作障碍物，求总共可以到达的格子数
 */

public class Solution {

    int res = 0;

    /**
     * dfs
     * @param m
     * @param n
     * @param cnt
     * @return
     */
    // 时间复杂度：O(mn)。分析：当cnt足够大时，所有格子都需要整理且能到达
    // 时间复杂度：O(mn)。分析：递归深度为O(mn)，访问标志矩阵占O(mn)
    public int wardrobeFinishing(int m, int n, int cnt) {
        boolean[][] visited = new boolean[m][n];
        dfs(visited, 0, 0, cnt);
        return res;
    }

    public void dfs(boolean[][] visited, int i, int j, int cnt) {
        // 访问越界或已经访问过或该格子不需要整理都直接返回
        if(i < 0 || i >= visited.length || j < 0 || j >= visited[0].length || visited[i][j] || digit(i) + digit(j) > cnt) {
            return;
        }
        visited[i][j] = true;   // 整理该格子
        res++;
        dfs(visited, i, j + 1, cnt);    // 尝试向右走
        dfs(visited, i + 1, j, cnt);    // 尝试向左走
        // 注意这里不要撤销访问操作，因为尝试了这条路就是访问过了，如果又把访问标志置为false则后续别的路径走到这时又会重复访问导致结果错误
    }

    /**
     * 求 x 的数位之和
     * @param x
     * @return
     */
    public int digit(int x) {
        int dig = 0;
        while (x != 0){
            dig += x % 10;
            x /= 10;
        }
        return dig;
    }
}
