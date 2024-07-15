package myPow;

/**
 * LCR 134. Pow(x, n)
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n）。
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31 - 1
 * -10^4 <= x^n <= 10^4
 */

public class Solution {

    /**
     * 分治法
     * 若n为偶数，x^n = (x^2)^(n/2)
     * 若n为奇数，x^n = x * (x^2)^(n/2)
     * 根据推导，可通过循环 x=x^2 操作，每次把幂从 n 降至 n/2 ，直至将幂降为 0 ；
     *
     * 转化为位运算：
     * - 向下整除 n//2 等价于 右移一位 n>>1 ；
     * - 取余数 n mod 2 等价于 判断二进制最右位 n & 1 ；
     *
     * @param x
     * @param n
     * @return
     */
    // 时间复杂度：O(logn)。分析：每次将指数n二分，二分的时间复杂度为对数级别
    // 空间复杂度：O(1)
    public double myPow(double x, int n) {
        // 数字 0 的正数次幂恒为 0；0 的 0 次幂和负数次幂没有意义，因此直接返回 0.0 即可。
        if(x == 0.0) {
            return 0.0;
        }

        double res = 1.0;

        // Java 中 int32 变量区间 n∈[−2147483648,2147483647] ，因此当 n=−2147483648 时执行 n=−n 会因越界而赋值出错。
        // 解决方法是先将 n 存入 long 变量 b ，后面用 b 操作即可。
        long b = n;
        if (b < 0) {    // 求负数次幂转为求x的倒数的正数次幂
            x = 1 / x;
            b = -b;
        }

        while(b > 0) {
            // 每当 b 为奇数时，将多出的一项 x 乘入 res
            if ((b & 1) == 1) {
                res *= x;
            }
            // 底数变平方，指数减半（向下取整，奇数的情况在上面已经补了x）
            x *= x; // x^2
            b >>= 1;    // b/2
        }
        return res;
    }
}
