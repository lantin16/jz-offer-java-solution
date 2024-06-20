package checkDynasty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LCR 186. 文物朝代判断
 *
 * 展览馆展出来自 13 个朝代的文物，每排展柜展出 5 个文物。某排文物的摆放情况记录于数组 places，其中 places[i] 表示处于第 i 位文物的所属朝代编号。
 * 其中，编号为 0 的朝代表示未知朝代。请判断并返回这排文物的所属朝代编号是否连续（如遇未知朝代可算作连续情况）。有重复朝代不能算连续
 */

public class Solution {

    /**
     * 辅助哈希表帮助判断重复
     * 已知如果有重复朝代必不连续，在此前提下，如果5个中最大的朝代 max 与最小的朝代 min 满足：max - min < 5，则5个朝代必连续（中间缺的朝代必可以用0补齐，剩下有多的0往两边补）
     * @param places
     * @return
     */
    // 时间复杂度：O(1)。分析：本题给定places的长度 n=5，遍历数组O(n) = O(5) = O(1)
    // 空间复杂度：O(1)。分析：辅助Set使用 O(n) = O(1) 额外空间
    // public boolean checkDynasty(int[] places) {
    //     Set<Integer> set  = new HashSet<>();
    //     int max = 0, min = 14;  // 除0以外的最大值和最小值
    //
    //     for (int place : places) {
    //         if (place == 0) {   // 遇到0就跳过
    //             continue;
    //         }
    //         max = Math.max(max, place);
    //         min = Math.min(min, place);
    //         if (set.contains(place)) {  // 有重复直接return false，因为重复就已经不连续了
    //             return false;
    //         } else {
    //             set.add(place);
    //         }
    //     }
    //     return max - min < 5;
    // }


    /**
     * 排序 + 遍历
     * 可以先排序，这样相同的元素相邻，在遍历数组时就可以判断重复的非零元素
     * @param places
     * @return
     */
    // 时间复杂度：O(1)
    // 空间复杂度：O(1)
    public boolean checkDynasty(int[] places) {
        // 先排序
        Arrays.sort(places);
        // 跳过前面的0
        int i = 0;
        while(places[i] == 0) {
            i++;
        }
        int min = places[i];    // 除0外的最小数
        // 判断重复
        while(i < places.length - 1) {
            if (places[i] == places[i + 1]) {
                return false;
            }
            i++;
        }
        int max = places[places.length - 1];    // 除0外的最大数
        return max - min < 5;
    }
}
