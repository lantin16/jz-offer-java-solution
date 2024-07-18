package wordPuzzle;

/**
 * LCR 129. 字母迷宫
 *
 * 字母迷宫游戏初始界面记作 m x n 二维字符串数组 grid，请判断玩家是否能在 grid 中找到目标单词 target。
 * 注意：寻找单词时 必须 按照字母顺序，通过水平或垂直方向相邻的单元格内的字母构成，同时，同一个单元格内的字母 不允许被重复使用 。
 */

public class Solution {

    /**
     * dfs + 剪枝
     */
    // boolean[][] visited;
    // int[][] dire = new int[][]{{-1,0}, {0,1}, {1,0}, {0,-1}};
    // public boolean wordPuzzle(char[][] grid, String target) {
    //     int m = grid.length, n = grid[0].length;
    //     visited = new boolean[m][n];
    //     boolean res = false;
    //     for(int i = 0; i < m; i++) {
    //         for(int j = 0; j < n; j++) {
    //             // 找到起点再开始尝试
    //             if(grid[i][j] == target.charAt(0)) {
    //                 if(dfs(grid, i, j, target, 0)) {
    //                     res = true;
    //                     break;
    //                 }
    //             }
    //         }
    //     }
    //     return res;
    // }
    //
    // // 从grid[i][j]开始找target[idx]及之后的字符串
    // public boolean dfs(char[][] grid, int r, int c, String target, int idx) {
    //     // 当前字母不匹配，这条路走不通
    //     if(grid[r][c] != target.charAt(idx)) {
    //         return false;
    //     }
    //     // 已经匹配完了
    //     if(idx == target.length() - 1) {
    //         return true;
    //     }
    //
    //     // 当前字母匹配，向四周继续匹配
    //     visited[r][c] = true;
    //     for(int k = 0; k < 4; k++) {
    //         int nr = r + dire[k][0], nc = c + dire[k][1];
    //         if(nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && !visited[nr][nc] && dfs(grid, nr, nc, target, idx+1)) {
    //             return true;
    //         }
    //     }
    //
    //     visited[r][c] = false;  // 撤销操作
    //     return false;   // 所有方向都尝试过了如果还不行则此路不通
    // }




    /**
     * dfs + 剪枝
     * 优化写法：
     * 1. 对于越界的判断放到递归开头也作为递归中止条件，而不是由调用方判断后再调用dfs
     * 2. 不用二维visited数组记录格子的访问情况，而是选择将格子置为空字符代表访问过（格子中的有效字符均为英文字母），这样可以节省空间
     * 3. 也不用方向数组了，只有四个方向直接手写也可以
     * @param grid
     * @param target
     * @return
     */
    // 时间复杂度：O(3^k mn)，其中 m,n 分别为矩阵行列大小， k 为字符串 target 长度。分析：最差情况下，需要遍历矩阵中长度为 k 字符串的所有方案，
    //  每次匹配一个字符，然后可以向三个方向继续匹配（因为回头路肯定可以排除），故每次搜索时间复杂度为 O(3^k)；矩阵中共有 mn 个起点，时间复杂度为 O(mn)。
    // 空间复杂度：O(k)，其中k 为字符串 target 长度。分析：搜索过程中的递归深度不超过 k，因此系统因函数调用累计使用的栈空间占用 O(k)（因为函数返回后，系统调用的栈空间会释放）。
    public boolean wordPuzzle(char[][] grid, String target) {
        int m = grid.length, n = grid[0].length;
        // 尝试以各个各自为起点，只要有能全部匹配的就返回true
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(dfs(grid, i, j, target, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 从grid[i][j]开始匹配target[idx]及之后的字符串
     * @param grid
     * @param r
     * @param c
     * @param target
     * @param idx
     * @return
     */
    public boolean dfs(char[][] grid, int r, int c, String target, int idx) {
        // 递归终止条件：索引越界或当前格子已经访问过或当前字母不匹配
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == ' ' || grid[r][c] != target.charAt(idx)) {
            return false;
        }
        // 当前字符匹配且是最后一个需要匹配的（已经匹配完了）则返回true
        if(idx == target.length() - 1) {
            return true;
        }

        // 当前字母匹配，且还需要向四周继续匹配
        grid[r][c] = ' ';   // 将格子置为空串代表已访问
        boolean res = dfs(grid, r - 1, c, target, idx + 1) || dfs(grid, r, c + 1, target, idx + 1) ||
                dfs(grid, r + 1, c, target, idx + 1) || dfs(grid, r, c - 1, target, idx + 1);
        grid[r][c] = target.charAt(idx);  // 撤销操作，将格子恢复成原来的字符
        return res;
    }
}
