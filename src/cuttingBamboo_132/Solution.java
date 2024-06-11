package cuttingBamboo_132;

/**
 * LCR 132. 砍竹子Ⅱ
 * 现需要将一根长为正整数 bamboo_len 的竹子砍为若干段，每段长度均为 正整数。请返回每段竹子长度的 最大乘积 是多少。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 */


/**
 * 大数求余的解法：
 * 1. 循环求余，时间复杂度O(n)
 * 2. 快速幂求余，时间复杂度：O(logn)
 * 这两种方法均基于求余运算规则推出：两个数乘积对p求余等于这两个数分别对p求余后的乘积再对p求余（积的取余等于取余的积的取余）
 * 参考：https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/solutions/106190/mian-shi-ti-14-ii-jian-sheng-zi-iitan-xin-er-fen-f
 */

public class Solution {

    /**
     * 由于bamboo_len ∈ [2,1000] 实在太大了，最后的乘积用 long long 都存不下。
     * 用元素类型为int的dp数组直接去记录乘积肯定是存不下了，那能否将求余之后的结果作为状态呢？
     * ——不可以，因为求余 MOD 操作不能在取 MAX 值的这条链上等价传递，可能会导致最大值改变。即一个数求mod后的结果大不代表它本身就大
     * 所以本题用动态规划不好做
     *
     * 贪心，根据 LCR.131 中的推导，将竹子尽可能多地切成长度为3的片段是最优的
     *
     * 循环求余/快速幂求余解决大数求余问题
     *
     * @param bamboo_len
     * @return
     */
    // 时间复杂度：O(logn)。分析：如果用快速幂求余时间复杂度为O(logn)，优于循环求余的O(n)
    // 空间复杂度：O(1)
    public int cuttingBamboo(int bamboo_len) {
        if(bamboo_len <= 3) {
            return bamboo_len - 1;
        }

        // bamboo_len = 3 * a + b
        int a = bamboo_len / 3;
        int b = bamboo_len % 3;
        int p = (int) (1e9 + 7);

        // 求 3^(a-1) % p = rem，由于 rem * 6 可能超出int范围，因此用long
        // 为了避免重复计算，这里求的是 3^(a-1) % p，后面需要用到 3^a % p 时在这个的基础上再乘即可
        // long rem = remainder_loop(3, a - 1, p); // 循环求余法
        long rem = remainder_loop(3, a - 1, p); // 快速幂求余

        if (b == 0) {
            // 返回 3^a % p = [(3^(a-1) % p) * 3] % p
            return (int)((rem * 3) % p);
        } else if (b == 1) {
            // 返回 [3^(a-1) * 4] % p = [(3^(a-1) % p) * (4 % p)] % p
            return (int)((rem * 4) % p);
        } else {
            // 返回 [3^a * 2] % p = [(3^(a-1) % p) * (6 % p)] % p
            return (int)((rem * 6) % p);
        }
    }


    /**
     * 循环求余法
     * 求 (x^a) % p
     * 至少要保证变量 rem 可以正确存储 1000000007^2，而 2^64 > 1000000007^2 > 2^32，因此我们选取 long 类型
     * @param x
     * @param a
     * @param p
     * @return 余数 long类型
     */
    // 时间复杂度：O(n)，其中 n=a
    // 空间复杂度：O(1)
    private long remainder_loop(int x, int a, int p) {
        long rem = 1;    // 由于rem是计算过程中的余数，3 * rem 是可能超出int的表示范围的，因此这里要用long
        for (int i = 1; i <= a; i++) {
            rem = (rem * x) % p;
        }
        return rem;
    }


    /**
     * 快速幂求余（理解比较难，先记住！）
     * 求 (x^a) % p
     * 至少要保证变量 x 和 rem 可以正确存储 1000000007^2，而 2^64 > 1000000007^2 > 2^32，因此我们选取 long 类型。
     *
     * 证明可以参考http://t.csdnimg.cn/xsRWd
     *
     * @param x 基底 long类型
     * @param a
     * @param p
     * @return 余数 long类型
     */
    // 时间复杂度：O(logn)，其中 n=a
    // 空间复杂度：O(1)
    private long remainder_quick(long x, int a, int p) {
        long rem = 1;   // 由于rem是计算过程中的余数，3 * rem 是可能超出int的表示范围的，因此这里要用long
        while (a > 0) { // 指数大于0就一直循环
            if (a % 2 == 1) {   // 指数为奇数
                rem = (x * rem) % p;
            }
            a /= 2;  // 指数折半
            x  = (x * x) % p;   // x^2可能超出int的表示范围
        }
        return rem;
    }


    /**
     * 快速幂算法
     * 核心思想：每一次运算都把指数折半，底数变其平方
     * 参考：https://blog.csdn.net/m0_52072919/article/details/116400820
     */
    // 时间复杂度：O(logn)
    private long quick_power(int base, int power) {
        long res = 1;
        while (power > 0) { // 指数大于0进行指数折半，底数变其平方的操作
            if (power % 2 == 1) {   // 指数为奇数
                res *= base;
            }
            power /= 2; // 指数折半，如果p为奇数则自动向下取整（多出来的一个base在上面的if中已经乘上了）
            base *= base;   // 底数变平方
        }
        return res;
    }

}
