package findTargetIn2DPlants;

/**
 * LCR 121. 寻找目标值 - 二维数组
 * m*n 的二维数组 plants 记录了园林景观的植物排布情况，具有以下特性：
 * - 每行中，每棵植物的右侧相邻植物不矮于该植物；
 * - 每列中，每棵植物的下侧相邻植物不矮于该植物。
 * 请判断 plants 中是否存在目标高度值 target。
 */

public class Solution {


    /**
     * 蛇形搜索法
     * @param plants
     * @param target
     * @return
     */
    // 时间复杂度：O(m+n)
    // 空间复杂度：O(1)
    // public boolean findTargetIn2DPlants(int[][] plants, int target) {
    //     // 对m, n 有0的情况进行特判，有一方为0就说明矩阵不存在
    //     if(plants.length == 0 || plants[0].length == 0) {
    //         return false;
    //     }
    //     int m = plants.length, n = plants[0].length;
    //     int i = 0, j = 0;
    //     while(i < m && j < n) {
    //         if(plants[i][j] == target) {    // 找到target直接结束
    //             return true;
    //         }
    //
    //         // 当前元素比target小
    //         if(plants[i][j] < target) {
    //             if(j == n-1 || plants[i][j+1] > target) {  // 已经到该排的最右边了或者右边比target还大，该向下走了
    //                 i++;
    //             } else {    // 继续向右
    //                 j++;
    //             }
    //             continue;
    //         }
    //
    //         // 当前元素比target大
    //         if(plants[i][j] > target) {
    //             if (j == 0) {   // 已经到本行最左边了还没找到，就说明不存在（因为target还要小的话，下面的行只可能比当前元素更大，上面的行之前已经搜索过了，没路可走了）
    //                 return false;
    //             } else {    // 继续向左
    //                 j--;
    //             }
    //         }
    //     }
    //     return false;
    // }


    /**
     * 巧妙！
     * 将矩阵逆时针旋转 45° ，并将其转化为图形式，发现其类似于 二叉搜索树 ，即对于每个元素，其左分支元素更小、右分支元素更大。
     * 那么从 “根节点”（原矩阵右上角元素） 开始搜索，遇到比 target 大的元素就向左，反之向右，即可找到目标值 target 。
     * 参考：https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/solutions/95306/mian-shi-ti-04-er-wei-shu-zu-zhong-de-cha-zhao-zuo
     * @param plants
     * @param target
     * @return
     */
    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        if(plants.length == 0 || plants[0].length == 0) {
            return false;
        }
        int m = plants.length, n = plants[0].length;
        int i = 0, j = n - 1;
        while(i < m && j >= 0) {
            if(target == plants[i][j]) {    // 找到target
                return true;
            }

            if(target < plants[i][j]) { // target小于当前元素，往左分支搜索，体现在矩阵上就是列号-1
                j--;
            } else {    // target大于当前元素，往右分支搜索，体现在矩阵上就是行号+1
                i++;
            }
        }
        return false;
    }
}
