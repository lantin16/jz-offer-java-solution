package myAtoi;

/**
 * LCR 192. 把字符串转换成整数 (atoi)
 * 本题难点在于对int越界的处理和判断
 * 题目描述见：https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/description/
 */

public class Solution {

    /**
     * 双指针，从低位向高位逐步加入每位的值
     * @param str
     * @return
     */
    // 时间复杂度：O(n)，其中n为str的长度
    // 时间复杂度：O(1)
    // public static int myAtoi(String str) {
    //     str = str.trim();   // 去掉首尾的空格
    //     char[] arr = str.toCharArray();
    //     // 如果只有空格或者去除前导空格后第一个字符不是正负号或数字，则无法转换，返回0
    //     if(str.length() == 0 || (arr[0] != '+' && arr[0] != '-' && !isNumber(arr[0]))) {
    //         return 0;
    //     }
    //
    //     boolean pos = true; // 正负标志
    //     int res = 0;
    //     int l = 0;
    //     // 读取符号（如果有的话），如果没有正负号则为正
    //     if(arr[l] == '-' || arr[l] == '+') {
    //         pos = arr[l] != '-';
    //         l++;
    //     }
    //     // l跳过前导0
    //     while(l < arr.length && arr[l] == '0') {
    //         l++;
    //     }
    //
    //     int r = l;
    //     // r向右搜索直至一串数字结束
    //     while (r < arr.length && isNumber(arr[r])) {
    //         r++;
    //     }
    //     r--;    // 退出循环时r要么出界，要么指向有效数的最右边的下一个非数字，因此需要回退一格
    //
    //     // 从右往左生成结果
    //     long base = 1;  // 每位的基（十进制），这里用long，防止到后面base本身越界
    //     while (r >= l) {
    //         int cur = arr[r] - '0'; // 当前位数字
    //         if (pos) {  // 正数
    //             if (base > Integer.MAX_VALUE || Integer.MAX_VALUE - res < base * cur) { // 若base没溢出int且 res + base * cur 也没溢出int的最大值
    //                 return Integer.MAX_VALUE;
    //             }
    //             res += cur * base;
    //         } else {    // 负数
    //             if (base > Integer.MAX_VALUE || Integer.MIN_VALUE + base * cur > res) { // 若base没溢出int且 res - base * cur 也没溢出int的最小值
    //                 return Integer.MIN_VALUE;
    //             }
    //             res -= cur * base;
    //         }
    //         base *= 10;
    //         r--;
    //     }
    //
    //     return res;
    // }

    /**
     * 判断是否为0~9的数字
     * @param c
     * @return
     */
    private static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }


    /**
     * 技巧：
     * 1. 不用boolean类型的pos来记录符号位，而是可以直接用int类型的+1/-1来记录，这样后面计算最终结果时就不用先判断pos再分正负计算，而是可以直接用 res * sign 来统一得到结果
     * 2. 将数字字符串转换为整数类型，从左向右遍历比从右向左遍历更简单，因为从右往左每次还需要将base乘10，而从左向右遍历数字，可以直接用公式 res=10×res+x，巧妙地每次将前面的各位数乘10
     * 3. 设bdy = (2^31 - 1) / 10 = 2147483647 / 10 = 214748364，则越界的两种情况：
     *    1) res > bdy，此时根据公式计算时 res * 10 >= 2147483650 必越界
     *    2) res = bdy 且 x > 7，此时根据公式计算时 res * 10 = 214748340，个位如果再加上超过7的x则res会超过2147483647而越界
     * 4. 另一种判断越界的巧妙思路：先计算res，然后尝试除以10，如果除10的结果和上一轮的res不同，意味发生越界。因为发生溢出的话结果不会如预期，除以10回不到过去
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        str = str.trim();   // 去掉首尾的空格
        char[] arr = str.toCharArray();
        // 如果只有空格或者去除前导空格后第一个字符不是正负号或数字，则无法转换，返回0
        if(str.length() == 0 || (arr[0] != '+' && arr[0] != '-' && !isNumber(arr[0]))) {
            return 0;
        }

        int sign = 1;   // 符号标志
        int bdy = Integer.MAX_VALUE / 10; // 2147483647 / 10 = 214748364
        int res = 0;
        int l = 0;
        // 读取符号（如果有的话），如果没有正负号则为正
        if(arr[l] == '-' || arr[l] == '+') {
            sign = arr[l] == '-' ? -1 : 1;
            l++;    // 有符号位读完需要将l右移，如果没有则l当前就指向第一个数字，不用右移
        }

        // l跳过前导0，最后指向第一个非零的字符
        while(l < arr.length && arr[l] == '0') {
            l++;
        }

        // 从左往右遍历生成结果，重点在于如何判断越界
        for (int i = l; i < arr.length; i++) {
            if (!isNumber(arr[i])) {    // 遇到非数字直接结束
                break;
            }
            // 缺点：需要对上界2147483647的末位熟悉，而且需要将末位7硬编码到程序中
            if (res > bdy || (res == bdy && arr[i] > '7')) {    // 越界，根据符号返回上界或下界
                // 看似将下界-2147483648当作越界处理了（实际上int可以表示该值），但巧妙之处在于即使当作越界也返回的是-2147483648，实际没越界其实也返回的是-2147483648
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + (arr[i] - '0');

            // // 另一种判断越界的方法：先计算res，然后尝试除以10，如果除10的结果和上一轮的res不同，意味发生越界。
            // int temp = res * 10 + (arr[i] - '0');   // 若此处发生溢出则结果不会如预期，除以10回不到过去
            // if (temp / 10 != res) {
            //     return sign == 1? Integer.MAX_VALUE : Integer.MIN_VALUE;
            // }
            // res = temp;
        }
        return sign * res;
    }
}
