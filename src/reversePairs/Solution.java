package reversePairs;

/**
 * LCR 170. 交易逆序对的总数
 *
 * 在股票交易中，如果前一天的股价高于后一天的股价，则可以认为存在一个「交易逆序对」。
 * 请设计一个程序，输入一段时间内的股票交易记录 record，返回其中存在的「交易逆序对」总数。
 *
 * 利用「归并排序」计算逆序对，是非常经典的做法（掌握）
 */


public class Solution {

    /**
     * 「归并排序」是分治思想的典型应用，它包含这样三个步骤：
     * 1. 分解：待排序的区间为 [l,r]，令 m=(l+r)/2，我们把 [l,r] 分成 [l,m] 和 [m+1,r]
     * 2. 解决：使用归并排序递归地排序两个子序列
     * 3. 合并：把两个已经排好序的子序列 [l,m] 和 [m+1,r] 合并起来
     * 在待排序序列长度为 1 的时候，递归开始「回升」，因为我们默认长度为 1 的序列是排好序的。
     */


    /**
     * 归并排序的过程中确定逆序对的总数
     *
     * 所有的「逆序对」来源于 3 个部分：
     * - 左边区间的逆序对；
     * - 右边区间的逆序对；
     * - 横跨两个区间的逆序对。
     *
     * 参考：https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solutions/622496/jian-zhi-offer-51-shu-zu-zhong-de-ni-xu-pvn2h
     * @param record
     * @return
     */
    // 时间复杂度：O(nlogn)，其中n为record数组长度。分析：归并排序使用O(nlogn)的时间
    // 空间复杂度：O(n)。分析：归并排序需要O(n)额外空间的辅助数组tmp
    public int reversePairs(int[] record) {
        int len = record.length;
        int[] tmp = new int[len];   // 辅助数组，用于合并阶段暂存元素
        return mergeSortAndCount(record, 0, len - 1, tmp);
    }


    /**
     * 执行record的[l, r]区间的归并排序，并返回逆序对总数
     * @param record
     * @param l 排序区间的左端点
     * @param r 排序区间的右端点
     * @param tmp 辅助数组
     * @return 返回[l, r]区间内的逆序对总数
     */
    private int mergeSortAndCount(int[] record, int l, int r, int[] tmp) {
        // 1. 递归中止条件：排序区间长度为1
        if (l >= r) {
            return 0;
        }

        // 2. 递归划分
        int m = (l + r) / 2;    // 划分点取区间中点
        // 对左右子区间进行递归归并排序并统计左、右子区间内的逆序对
        int res = mergeSortAndCount(record, l, m, tmp) + mergeSortAndCount(record, m + 1, r, tmp);

        // 3. 合并阶段
        // 到达此阶段说明左右子区间已经有序了，就可以利用顺序关系加快逆序对数的计算
        // 并且按照左区间内、右区间内、跨左右区间三个来源计算逆序对也能避免重复计算
        // 每当遇到 左子数组当前元素 > 右子数组当前元素 时，意味着 「左子数组当前元素 至 末尾元素」 与 「右子数组当前元素」 构成了若干 「逆序对」 。

        // 如果[l,r]区间内已经有序了，则无需合并，也没有跨左右的逆序对，这部分可以直接返回
        // 由于左右子区间已分别有序，因此只用比较左区间的右端点和右区间的左端点大小即可判断是否整体有序
        if (record[m] <= record[m + 1]) {
            return res;
        }

        // 3.1 暂存数组 record 闭区间 [l,r] 内的元素至辅助数组 tmp 对应位置
        System.arraycopy(record, l, tmp, l, r - l + 1);

        // 3.2 循环合并
        // 每次选择的是左右区间当前元素中较小的那个，保证合并后升序
        int i = l, j = m + 1;   // 双指针分别指向左右子区间的首元素
        for (int k = l; k <= r; k++) {
            if (i == m + 1) {   // 如果i已经指向了右区间的左端点，说明左区间的元素已经合并完了，此时将右区间剩下的元素直接添加到后面即可
                record[k] = tmp[j++];
            } else if (j == r + 1) {    // 如果j已经指向了右区间的右端点之后，说明右区间的元素已经合并完了，此时将左区间剩下的元素直接添加到后面即可
                record[k] = tmp[i++];
            } else if (tmp[i] <= tmp[j]) {  // 左右区间均没合并完 且 左边的当前元素 <= 右边的当前元素，说明这两个元素无法构成逆序对
                record[k] = tmp[i++];
            } else {    // 左右区间均没合并完 且 左边的当前元素 > 右边的当前元素，说明「左子数组当前元素 至 末尾元素」与「右子数组当前元素」构成了若干「逆序对」
                record[k] = tmp[j++];
                // record[i] > record[j]，则由于左区间升序，必有[i,m]区间内的元素均大于record[j]，共(m-i+1)个
                res += (m - i + 1); // 统计跨区间的逆序对（本题相较于归并排序多出的核心代码）
            }
        }
        return res;
    }
}
