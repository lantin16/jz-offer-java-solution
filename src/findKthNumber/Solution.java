package findKthNumber;

/**
 * LCR 163. 找到第 k 位数字
 *
 * 某班级学号记录系统发生错乱，原整数学号序列 [0,1,2,3,4,...] 分隔符丢失后变为 01234... 的字符序列。请实现一个函数返回该字符序列中的第 k 位数字。
 */

public class Solution {


    /**
     * 找规律
     * 一位数共10个（0~9），对应的k的范围是[0,9]
     * 两位数共90个（10~99），对应的k的范围是[10,189]
     * 三位数共900个（100~999），对应的k的范围是[190,2889]
     * ...
     * d位数共 10^d - 10^(d-1) 个（100...00 ~ 999...99），对应的k的范围是[上一次end+1, 这次的start + d位数的个数 * d - 1]
     *
     * 总体思路：根据 k 先确定它所属的数字是几位数，然后确定它是该位数的哪一个具体的数字，最后确定 k 对应的是该数字的哪一位字符
     *
     * @param k
     * @return
     */
    // 时间复杂度：O(1)。分析：k对应的数字最多为9位数，循环也不会超过9次
    // 空间复杂度：O(1)
    public int findKthNumber(int k) {
        // k映射到一位数直接返回
        if(k >= 0 && k <= 9) {
            return k;
        }
        int d = 1;  // d位数
        long start, end = 9;  // d位数的起始k和终结k（为了防止计算end时按照该公式发生int越界，这里用long存）
        int res = -1;
        while(res == -1) {
            d++;    // 位数加一
            start = end + 1;    // d位数的起始k就等于(x-1)位数的终结k加一，如 两位数的start = 9 + 1
            end = start + (long)(Math.pow(10, d) - Math.pow(10, d-1)) * d - 1;  // 如 两位数的 end = start + (100 - 10) * 2 - 1
            if(k >= start && k <= end) {    // k在[start, end]范围内则它所属的数字就是d位数
                long num = (long)Math.pow(10, d-1) + ((long)k - start) / d; // k对应字符所属的d位数字
                // start <= k < 2^31，因此start肯定在int表示范围内，故可以安全long转int
                res = String.valueOf(num).charAt((k - (int)start) % d) - '0';   // 如何确定k对应的是num的具体哪一位呢？——将num转为String然后根据第几位来获取
            }
        }
        return res;
    }
}
