package articleMatch;

/**
 * LCR 137. 模糊搜索验证
 *
 * 请设计一个程序来支持用户在文本编辑器中的模糊搜索功能。用户输入内容中可能使用到如下两种通配符：
 * '.' 匹配任意单个字符。
 * '*' 匹配零个或多个前面的那一个元素。
 *
 * 请返回用户输入内容 input 所有字符是否可以匹配原文字符串 article。
 */

public class Solution {


    /**
     * 动态规划
     * ！！！注意本题的 a* 这种是指 这个整体可以匹配 0或多个a，而不是说前面先得有个a匹配，然后*再匹配0或多个a！！！
     * @param s
     * @param p
     * @return
     */
    // 时间复杂度：O(mn)，其中m，n为s，p的长度
    // 空间复杂度：O(mn)，其中m，n为s，p的长度
    public boolean articleMatch(String s, String p) {
        char[] sc = s.toCharArray(), pc = p.toCharArray();
        int l1 = s.length(), l2 = p.length();
        // dp[i][j]：p[0:j-1]能否匹配s[0:i-1]，即p的前j个字符能够匹配s的前i个字符，这样定义方便进行i=0和j=0的初始化，因为当i=0和j=0分别代表s和p为空串
        boolean[][] dp = new boolean[l1 + 1][l2 + 1];

        // 初始化
        // 1. 空正则可以匹配空串
        dp[0][0] = true;
        // 2. i = 0，非空正则是否能匹配空串，需要计算（不是一定能匹配！）
        // 什么样的非空正则能匹配空串呢？——利用 x* 能匹配0次的特点，形如：a*b*c*，即第偶数位全为*时才能匹配空串
        for(int j = 2; j <= l2; j += 2) {
            dp[0][j] = dp[0][j-2] && pc[j-1] == '*';
        }
        // j为奇数时的dp[0][j]均为false
        // 3. j = 0，空正则无法匹配非空串，保持false

        // 遍历顺序，先i后j
        for(int i = 1; i <= l1; i++) {
            for(int j = 1; j <= l2; j++) {
                // 关注最后一个字符p[j-1]
                if(pc[j-1] == '.') {    // p[j-1]为.
                    dp[i][j] = dp[i-1][j-1];    // 若p的前j-1个能匹配上s的前i-1个，最后一个交给.一定能匹配上
                } else if(pc[j-1] == '*') { // p的第j个字符即p[j-1]为*，则看前一个（p[j-2]）是什么
                    if(pc[j-2] == '.'){ // .*
                        dp[i][j] = dp[i][j-2] || dp[i-1][j];    // .*匹配0次 或 p前j个能匹配s的前i-1个，后面用重复.匹配
                    } else {    // 字母*
                        dp[i][j] = dp[i][j-2] || (dp[i-1][j] && sc[i-1] == pc[j-2]);    // 字母*匹配0次 或 p前j个能匹配s的前i-1个，后面用重复字母匹配（要求s第i个和重复字母相同）
                    }
                } else {    // p[j-1]为字母
                    dp[i][j] = dp[i-1][j-1] && sc[i-1] == pc[j-1];
                }
            }
        }
        return dp[l1][l2];
    }
}
