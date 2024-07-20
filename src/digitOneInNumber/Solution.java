package digitOneInNumber;

/**
 * LCR 162. 数字 1 的个数
 *
 * 给定一个整数 num，计算所有小于等于 num 的非负整数中数字 1 出现的个数。
 */

public class Solution {

    /**
     * 计算所有小于等于 n 的非负整数中数字 1 出现的个数 等于 计算各位的1出现的次数，将所有次数加起来，即为总次数
     * 如：n=30。
     * 当个位为1时，可以是01、11、21总共3个(只看个位1)；
     * 当十位为1时，可以是10、11...19，总共10次(只看十位1)，则3+10 = 13次；
     * 发现在考察十位时，虽然和考察个位时一样也出现了11，但由于11本就应该算两个1，故这样相加恰好是要求的1的总次数。
     *
     * !!!参考：https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solutions/229751/mian-shi-ti-43-1n-zheng-shu-zhong-1-chu-xian-de-2/comments/2053786
     * @param num
     * @return
     */
    // 时间复杂度：O(logn)。分析：循环内的计算操作使用 O(1) 时间；循环次数为数字 n 的位数，即 logn，因此循环使用 O(logn) 时间。
    // 空间复杂度：O(1)
    public int digitOneInNumber(int num) {
        int cur, low, high; // 当前位、低位、高位，如501222的当前位为百位2时，低位就为22，高位就为501
        int d = 1;  // 每位权重，如个位权重1，十位权重10，百位权重100
        int res = 0;
        int tmp = num;  // 跳出循环用，用于判断num的每一位为1的情况是否都算到了

        while(tmp > 0) {    // 注意这里不要用 d <= num 来作为循环条件，因为当num很大接近10^9时，d会变为10^10溢出变为不正确的int值导致没有及时跳出循环结果错误
            cur = num / d % 10;
            low = num % d;
            high = num / d / 10;
            tmp /= 10;

            if (cur == 0) {
                res += high * d;
            } else if (cur == 1) {
                res += high * d + low + 1;
            } else {    // cur > 1
                res += (high + 1) * d;
            }
            d *= 10;
        }

        return res;
    }
}
