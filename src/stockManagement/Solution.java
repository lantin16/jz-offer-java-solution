package stockManagement;

/**
 * LCR 128. 库存管理 I
 *
 * 仓库管理员以数组 stock 形式记录商品库存表。stock[i] 表示商品 id，可能存在重复。原库存表按商品 id 升序排列。现因突发情况需要进行商品紧急调拨，
 * 管理员将这批商品 id 提前依次整理至库存表最后。请你找到并返回库存表中编号的 最小的元素 以便及时记录本次调拨。
 */

public class Solution {


    /**
     * 遍历找到第一个递减的元素，如果没有递减的元素代表所有商品都进了紧急调拨
     * @param stock
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    // public int stockManagement(int[] stock) {
    //     int pre = stock[0];
    //     int res = stock[0]; // 如果所有商品都进行了紧急调拨，则第一个就是所求
    //     for(int i = 1; i < stock.length; i++) {
    //         if(stock[i] < pre) {
    //             res = stock[i];
    //             break;
    //         }
    //         pre = stock[i];
    //     }
    //     return res;
    // }


    /**
     * 二分查找 + 局部遍历
     * 纯二分查找的写法和证明参考：https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solutions/102826/mian-shi-ti-11-xuan-zhuan-shu-zu-de-zui-xiao-shu-3
     * 由于纯二分查找的证明和处理技巧比较难想到，因此这里只在前期使用二分查找缩小范围，当无法安全缩小区间时则对该区间内采用局部遍历找到结果
     *
     * 原数组被分成了两段排序数组（旋转点即为本题所求）
     * 左排序数组的任一元素 >= 右排序数组的任一元素
     * 局部有序，排序数组的查找问题首先考虑使用 二分法 解决，其可将 遍历法 的 线性级别 时间复杂度降低至 对数级别 。
     * 对于本题，二分法是为了快速缩小旋转点所在的区间，加速定位过程
     *
     *
     * @param stock
     * @return
     */
    // 时间复杂度：O(logn)。分析：平均情况下二分法在前期能快速缩小区间，最后局部采用线性遍历搜索也耗时不大，最差情况下所有数都相等则退化成线性搜索
    // 空间复杂度：O(1)
    public int stockManagement(int[] stock) {
        int l = 0, r = stock.length - 1;

        while(l < r) {  // 当 l=r 时跳出二分循环，并返回 旋转点的值 stock[l] 即可。
            int mid = (l + r) / 2;  // 向下取整故 l <= mid < r
            if(stock[mid] < stock[r]) { // 旋转点一定在mid左边（可能为mid）
                r = mid;    // [l, mid]
            } else if(stock[mid] > stock[r]) {  // 旋转点一定在mid右边（不可能为mid）
                l = mid + 1;    // (mid, r]
            } else {    // stock[mid] == stock[r]的情况无法判断旋转点在mid左边还是右边，也就无法安全地缩小区间（参考题解中证明了 j = j - 1缩小区间的正确性，但比较难想到）
                // 因此在这一段内用直接用遍历搜索，找到第一个递减的数
                for(int i = l + 1; i <= r; i++) {
                    if(stock[i] < stock[i-1]) {
                        return stock[i];
                    }
                }
                return stock[l];    // 如果都不递减则说明开始的那个即为所求
            }
        }
        return stock[l];
    }
}
