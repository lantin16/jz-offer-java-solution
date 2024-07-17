package crackNumber;

import java.util.Arrays;

/**
 * LCR 165. 解密数字
 *
 * 现有一串神秘的密文 ciphertext，经调查，密文的特点和规则如下：
 * - 密文由非负整数组成
 * - 数字 0-25 分别对应字母 a-z
 * 请根据上述规则将密文 ciphertext 解密为字母，并返回共有多少种解密结果。
 */

public class Solution {

    /**
     * 一维dp
     * @param ciphertext
     * @return
     */
    // 时间复杂度：O(n)，其中n为十进制密码位数
    // 空间复杂度：O(n)
    // public int crackNumber(int ciphertext) {
    //     String s = String.valueOf(ciphertext);   // 十进制数转字符串
    //     int n = s.length();
    //
    //     // dp[i]：s[0:i]解密的结果数
    //     int[] dp = new int[n];
    //
    //     // 初始化
    //     dp[0] = 1;  // 第一个数字只能单独解密
    //
    //     for(int i = 1; i < n; i++) {
    //         dp[i] = dp[i-1];    // 当前位s[i]单独解密的种类数，一定有
    //         String tmp = s.substring(i-1, i+1); // s[i-1:i]两位数
    //         if (tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0) { // 巧妙利用字符串的比较来判断两位数是否在10到25之间
    //             dp[i] += (i >= 2 ? dp[i-2] : 1);  // 如果当前位s[i]还可以和前一位一起解密，也加上
    //         }
    //     }
    //     return dp[n-1];
    // }


    /**
     * dp + 滚动变量
     * @param ciphertext
     * @return
     */
    // 时间复杂度：O(n)，其中n为十进制密码位数
    // 空间复杂度：O(1)
    public int crackNumber(int ciphertext) {
        String s = String.valueOf(ciphertext);   // 十进制数转字符串
        int n = s.length();

        // dp[i]：前i个数字（s[0:i-1]）解密的种类数

        int a = 1, b = 1;   // 分别记录dp[i-2]和dp[i-1]，a初始为1即dp[0]=1如何理解？——反推只是为了计算方便

        for(int i = 2; i <= n; i++) {   // 从第2个数字开始
            String tmp = s.substring(i-2, i); // 第i-1和第i个组成的两位数，即s[i-2:i-1]
            int c = (tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0) ? a + b : b; // 巧妙利用字符串的比较来判断两位数是否在10到25之间
            a = b;
            b = c;
        }
        return b;
    }
}
