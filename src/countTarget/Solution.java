package countTarget;

/**
 * LCR 172. 统计目标成绩的出现次数
 *
 * 某班级考试成绩按 非严格递增 顺序记录于整数数组 scores，请返回目标成绩 target 的出现次数。
 */

public class Solution {

    /**
     * 暴力遍历
     * @param scores
     * @param target
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    // public int countTarget(int[] scores, int target) {
    //     int res = 0;
    //     for(int score : scores) {
    //         if(score == target) {
    //             res++;
    //         }
    //     }
    //     return res;
    // }


    /**
     * 二分查找
     * 排序数组中的搜索问题，首先想到 二分查找 解决
     * @param scores
     * @param target
     * @return
     */
    // 时间复杂度：O(logn)。分析：平均情况下二分查找的时间复杂度为O(logn)，极端情况下如果scores中全为target则二分查找一次就能找到target，但是向两端搜索其余target时将遍历整个数组
    // 空间复杂度：O(1)
    public int countTarget(int[] scores, int target) {
        int res = 0;
        int l = 0, r = scores.length - 1;
        int mid = 0;

        // 二分查找target（找到就行）
        while(l <= r) {
            mid = (l + r) / 2;
            if(scores[mid] == target) {
                res = 1;    // 已经找到了一个target
                break;
            } else if(scores[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        if(res > 0) {   // 退出循环时mid指向target，向两边继续搜索是否有其他的target
            l = mid - 1;
            r = mid + 1;
            while(l >= 0 && scores[l] == target) {
                res++;
                l--;
            }
            while(r < scores.length && scores[r] == target) {
                res++;
                r++;
            }
        }
        return res;
    }

}
