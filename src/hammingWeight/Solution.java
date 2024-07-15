package hammingWeight;

/**
 * LCR 133. 位 1 的个数
 *
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量)。
 *
 * 提示：
 * - 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * - 在 Java 中，编译器使用 二进制补码 记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 */

public class Solution {

    /**
     * 移位并逐位判断
     * @param n
     * @return
     */
    // 时间复杂度：O(logn)。分析：逐位判断需循环 logn次，其中logn代表数字 n 最高位 1 的所在位数.
    // 空间复杂度：O(1)
    public int hammingWeight(int n) {
        int res = 0;
        while(n != 0) {
            res += n & 1;   // 判断末位是否为1
            n >>>= 1;   // 本题要求把数字 n 看作无符号数，因此使用 无符号右移 操作
        }
        return res;
    }




    /**
     * 巧用 n&(n−1)
     * 参考：https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/solutions/111237/mian-shi-ti-15-er-jin-zhi-zhong-1de-ge-shu-wei-yun
     * (n−1) 解析： 二进制数字 n 最右边的 1 变成 0 ，此 1 右边的 0 都变成 1 。
     * n&(n−1) 解析： 二进制数字 n 最右边的 1 变成 0 ，其余不变。
     * @param n
     * @return
     */
    // 时间复杂度：O(m)，其中m为二进制数n中1的个数。
    // 空间复杂度：O(1)
    // public int hammingWeight(int n) {
    //     int res = 0;
    //     while(n != 0) {
    //         res ++;
    //         n &= (n-1);
    //     }
    //     return res;
    // }
}
