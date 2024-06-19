package twoSum;

import java.util.HashSet;
import java.util.Set;

/**
 * LCR 179. 查找总价格为目标值的两个商品
 *
 * 购物车内的商品价格按照 升序 记录于数组 price。请在购物车中找到两个商品的价格总和刚好是 target。若存在多种情况，返回任一结果即可。
 */

public class Solution {

    /**
     * 用Set记录出现过的数
     * （如果price数组无序可以这样做）
     * @param price
     * @param target
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    // public int[] twoSum(int[] price, int target) {
    //     Set<Integer> set = new HashSet<>();
    //     for(int p : price) {
    //         int expected = target - p;
    //         if(set.contains(expected)) {
    //             return new int[]{p, expected};
    //         } else {
    //             set.add(p);
    //         }
    //     }
    //     return null;
    // }


    /**
     * 题目已知price数组升序排列，因此不用暴力记录出现过的数字，可以根据这一特征缩小搜索的范围
     * @param price
     * @param target
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int[] twoSum(int[] price, int target) {
        int l = 0, r = price.length - 1;    // l只能右移，r只能左移
        // 由于price数组中的元素是商品的价格均为正数，要想和为target则两个价格都得小于target
        // 因此可以直接跳过右边大于等于target的数，这些数不可能成为和数之一
        while(price[r] >= target) {
            r--;
        }

        while(l < r) {
            int sum = price[l] + price[r];
            if(sum > target) {  // 和超过了target，需要减小某个和数
                r--;
            } else if(sum < target) {   // 和不足target，需要增大某个和数
                l++;
            } else {    // 找到符合条件的结果
                return new int[]{price[l], price[r]};
            }
        }
        return null;
    }

}
