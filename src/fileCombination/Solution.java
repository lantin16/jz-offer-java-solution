package fileCombination;

import java.util.ArrayList;
import java.util.List;

/**
 * LCR 180. 文件组合
 *
 * 待传输文件被切分成多个部分，按照原排列顺序，每部分文件编号均为一个 正整数（至少含有两个文件）。传输要求为：连续文件编号总和为接收方指定数字 target 的所有文件。
 * 请返回所有符合该要求的文件传输组合列表。
 *
 * 注意，返回时需遵循以下规则：
 * - 每种组合按照文件编号 升序 排列；
 * - 不同组合按照第一个文件编号 升序 排列。
 */

public class Solution {


    /**
     * 基于等差数列求和公式进行的一系列推导
     * 以下n代表符合条件的连续文件的个数，first代表连续文件的首个编号，last代表连续文件的最后一个编号
     *
     * 要求连续文件至少有2个（n>=2）且文件编号为正整数，那么最小最少得选1,2，此时target为3
     *
     * 根据等差序列求和公式：(first + last) * n / 2 = target
     * 可得：first + last = target * 2 / n，显然这个值得是大于等于3的正整数，即 target * 2 / n >= 3
     * 因此 n <= target * 2 / 3 且 n为正整数，这样就可以确定一个大致的n的上界
     *
     * 又由连续可得，last = first + n - 1，因此 first * 2 = target * 2 / n + 1 - n 得是偶数
     *
     * 题目要求不同组合按照第一个文件编号即first升序排列，根据上面的公式可得n越大，first越小，因此找符合条件的组合时 n 得从大到小遍历
     *
     * 接下来就根据推导过程中正整数这一条件进行筛选
     *
     * @param target
     * @return
     */
    // 时间复杂度：O(n)，其中 n = target
    // 空间复杂度：O(n)。分析：需要辅助集合record来记录符合条件的起始点及连续个数
    // public int[][] fileCombination(int target) {
    //     // target <= 2 时不可能找到符合要求的文件组合，因为n>=2，最起码得选1，2，target最起码为3
    //     if(target <= 2) {
    //         return new int[0][0];
    //     }
    //
    //     int tar2 = target * 2;  // target的2倍
    //     int maxN = (tar2 / 3);  // 搜索的n的上界
    //     List<Integer[]> record = new ArrayList<>(); // 用于记录符合条件的组合，Integer[0]表示该组合的起始编号，Integer[1]表示连续的个数
    //     for(int n = maxN; n >= 2; n--) {    // n越大，first越小，应该排在更前面
    //         int first2 = tar2 / n + 1 - n;  // first的2倍
    //         // 根据 target * 2 / n 为正整数 且 first >= 1 且 first * 2 得是偶数 进行筛选
    //         if(tar2 % n == 0 && first2 >= 2 && first2 % 2 == 0) {
    //             int first = first2 / 2;
    //             record.add(new Integer[]{first, n});  // 记录下符合条件的起始点first和连续个数n
    //         }
    //     }
    //
    //     // 根据record生成结果二维数组
    //     int[][] res = new int[record.size()][]; // 二维数组的内部数组的长度可以不一致，但是至少得指定第一维的长度，因此无法在一开始就初始化结果数组
    //     for(int i = 0; i < record.size(); i++) {    // 遍历每个符合条件的组合
    //         int first = record.get(i)[0];
    //         int n = record.get(i)[1];
    //         int[] arr = new int[n];
    //         for(int k = 0; k < n; k++) {
    //             arr[k] = first + k;
    //         }
    //         res[i] = arr;
    //     }
    //
    //     return res;
    // }


    /**
     * 滑动窗口
     *
     * 滑动窗口的重要性质是：窗口的左边界和右边界永远只能向右移动，而不能向左移动。这是为了保证滑动窗口的时间复杂度是 O(n)。
     *
     * @param target
     * @return
     */
    // 时间复杂度：O(n)
    // 时间复杂度：O(1)
    public int[][] fileCombination(int target) {
        // target <= 2 必找不到符合条件的
        if(target <= 2) {
            return new int[0][0];
        }

        List<int[]> res = new ArrayList<>();    // 由于暂时不知道有多少组符合条件的，因此外层先用List集合存，最后转成数组即可

        // 经分析符合条件的连续数最小最少得选1,2，因此l、r直接初始化为1，2，sum初始化为3
        int l = 1, r = 2;   // 滑动窗口的左右边界（均包含）
        int sum = 3;    // 连续数的和

        while(l <= target / 2) {    // 由于至少要连续两个数，因此l最大只可能取到 target / 2，此时 r = target / 2 + 1 时有可能满足 l + r = target，如 9 = 4 + 5
            // 移动窗口的左右边界同时维护sum，这样可以避免每次新窗口都通过等差数列求和公式来计算sum（乘除比加减计算慢）
            if(sum > target) {  // 窗口和大于target，需要缩小窗口，故l右移
                sum -= l;   // sum要先减去原来的左边界，再l右移
                l++;
            } else if(sum < target) {   // 窗口和小于target，需要扩大窗口，故r右移
                r++;
                sum += r;   // 注意：sum是要加上新的数，故r要先右移再加
            } else {    // 找到符合条件的连续数
                int[] arr = new int[r - l + 1];
                for(int i = l; i <= r; i++) {
                    arr[i - l] = i;
                }
                res.add(arr);
                // 找到符合条件的一组数后，以这个l开头的不可能再有别的，因此l可以右移了
                sum -= l;
                l++;
            }
        }
        return res.toArray(new int[res.size()][]);  // 外层List转数组
    }
}
