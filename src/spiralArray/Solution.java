package spiralArray;

/**
 * LCR 146. 螺旋遍历二维数组
 * <p>
 * 给定一个二维数组 array，请返回「螺旋遍历」该数组的结果。
 * 螺旋遍历：从左上角开始，按照 向右、向下、向左、向上 的顺序 依次 提取元素，然后再进入内部一层重复相同的步骤，直到提取完所有元素。
 */

public class Solution {


    // public int[] spiralArray(int[][] array) {
    //     if (array.length == 0 || array[0].length == 0) {
    //         return new int[0];
    //     }
    //     int m = array.length, n = array[0].length;
    //     boolean[][] visited = new boolean[m][n];
    //     int[] res = new int[m * n];
    //     int[][] dire = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    //     int i = 0, j = 0;   // 访问的坐标
    //     int step = 0;   // 步数
    //     int k = 0;  // 方向标志（0~3分别代表右下左上）
    //
    //     while (step < m * n) {  // 遍历完的标志根据遍历的元素个数
    //         // 访问越界或访问到已访问过的格子就换方向
    //         if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) {
    //             // 要先回退
    //             i -= dire[k][0];
    //             j -= dire[k][1];
    //             // 再换方向尝试
    //             k = (k + 1) % 4;
    //             i += dire[k][0];
    //             j += dire[k][1];
    //             continue;
    //         }
    //         visited[i][j] = true;
    //         res[step++] = array[i][j];
    //         i += dire[k][0];
    //         j += dire[k][1];
    //     }
    //
    //     return res;
    // }


    /**
     * 模拟螺旋遍历过程
     *
     * @param array
     * @return
     */
    public int[] spiralArray(int[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            return new int[0];
        }
        int m = array.length, n = array[0].length;
        int[] res = new int[m * n];
        int l = 0, r = n - 1, t = 0, b = m - 1; // 上下左右边界
        int k = 0;
        while (true) {
            // 从左到右
            for (int i = l; i <= r; i++) {
                res[k++] = array[t][i]; // 由于从左到右是沿着上边界，因此横坐标为t
            }
            if (++t > b) {  // 从左到右遍历完一行则上边界下移一行，如果上边界超过了下边界则代表遍历完，跳出循环
                break;
            }
            // 从上到下
            for (int i = t; i <= b; i++) {
                res[k++] = array[i][r];
            }
            if (--r < l) {
                break;
            }
            // 从右到左
            for (int i = r; i >= l; i--) {
                res[k++] = array[b][i];
            }
            if (--b < t) {
                break;
            }
            // 从下到上
            for (int i = b; i >= t; i--) {
                res[k++] = array[i][l];
            }
            if (++l > r) {
                break;
            }
        }

        return res;
    }
}
