package takeAttendance;

/**
 * LCR 173. 点名
 *
 * 某班级 n 位同学的学号为 0 ~ n-1。点名结果记录于升序数组 records。假定仅有一位同学缺席，请返回他的学号。
 */

public class Solution {

    /**
     * 遍历法，遇到的第一个下标与元素不相等的，则下标即为缺席同学的学号
     * @param records 仅有一位同学缺席，故records的长度其实等于总人数-1
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    // public int takeAttendance(int[] records) {
    //     int n = records.length + 1; // 总人数
    //     for(int i = 0; i < n - 1; i++) {
    //         // 遇到的第一个下标与元素不相等的，则下标即为缺席同学的学号
    //         if(records[i] != i) {
    //             return i;
    //         }
    //     }
    //     // 若records中下标与元素均对应相等，则缺席的是学号为n-1的同学
    //     return n - 1;
    // }


    /**
     * 看到升序数组想到二分查找，找到第一个 records[i] > i 的位置(i)
     * @param records 总人数为n，则records长度为n-1
     * @return
     */
    // 时间复杂度：O(logn)
    // 空间复杂度：O(1)
    public int takeAttendance(int[] records) {
        int l = 0, r = records.length - 1;
        // 如果records下标与元素全都匹配说明缺席的学号为n-1，因此res初始置为n-1，这样当下面的二分查找没有找到元素与下标不匹配的话就该返回n-1
        int res = records.length;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(records[mid] > mid) {    // mid处已经不匹配了，说明缺席同学的学号在mid或mid左边
                res = mid;  // 不确定mid是不是第一个不匹配的，先记录到mid
                r = mid - 1;
            } else {    // mid处还是匹配的，说明缺席同学的学号在mid右边
                l = mid + 1;
            }
        }
        return res;
    }

}
