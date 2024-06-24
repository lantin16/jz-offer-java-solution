package validNumber;

/**
 * LCR 138. 有效数字
 * 规则见：https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/description/
 */

public class Solution {

    /**
     * 有限状态机
     * 分阶段分情况判断，要细心，对各种情况都考虑到
     * 如果s是有效数字需要满足多个条件，因此只要检测到某处不满足就可以直接返回false，到最后都满足才返回true
     *
     * @param s
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)。分析：使用arr字符数组存储字符串s
    // public boolean validNumber(String s) {
    //     int len = s.length();
    //     char[] arr = s.toCharArray();
    //     int i = 0;
    //
    //     // 跳过前面的空格
    //     while(i < len &&  arr[i] == ' ') {
    //         i++;
    //     }
    //     // 全是空格的情况
    //     if(i == len) {
    //         return false;
    //     }
    //
    //     // 检查小数或整数部分
    //     int start = i;
    //     // 遇到e或空格就停止
    //     while(i < len && arr[i] != 'e' && arr[i] != 'E' && arr[i] !=  ' ') {
    //         i++;
    //     }
    //     // 如果这一段不是整数也不是小数，则直接返回false
    //     if(!isInteger(arr, start, i - 1) && !isDecimal(arr, start, i - 1)) {
    //         return false;
    //     }
    //
    //     // 有e的情况
    //     if (i < len && (arr[i] == 'e' || arr[i] == 'E')) { // 是因为配到了e或E才跳出循环的
    //         start = ++i;    // 读e后面的
    //         // 遇到空格就停止
    //         while(i < len && arr[i] != ' ') {
    //             i++;
    //         }
    //         // e后面如果跟的不是一个整数则返回false
    //         if (!isInteger(arr, start, i - 1)) {
    //             return false;
    //         }
    //     }
    //
    //     // 如果后面还有空格，则检查剩下的是否全是空格
    //     while (i < len) {
    //         if (arr[i] != ' ') {
    //             return false;   // 最后一部分如果不不是全为空格则返回false
    //         }
    //         i++;
    //     }
    //
    //     return true;
    // }
    //
    // /**
    //  * 检查arr[start:end]这一段是否为小数
    //  * @param arr
    //  * @param start
    //  * @param end
    //  * @return
    //  */
    // private boolean isDecimal(char[] arr, int start, int end) {
    //     // 如果实际要检查的这一段是空
    //     if (start > end) {
    //         return false;
    //     }
    //
    //     int i = start;
    //     // 跳过开始的+或-
    //     if (arr[i] == '+' || arr[i] == '-') {
    //         i++;
    //     }
    //     if (i > end) {  // 只有+，-
    //         return false;
    //     }
    //
    //     // 必须要是三种格式之一
    //     if (arr[i] == '.') {    // 一个点 '.' ，后面跟着至少一位数字
    //         i++;
    //         if (i > end) { // . 后什么都没有了
    //             return false;
    //         }
    //         // . 后至少有一位字符，确保后面不会出现数字之外的字符
    //         while(i <= end) {
    //             if (!isNumber(arr[i])) {
    //                 return false;
    //             }
    //             i++;
    //         }
    //         return true;
    //     } else if (isNumber(arr[i])) {  // 以至少一位数字开头，后面跟一个 . ，再后面要有也只能是数字
    //         while(i <= end && isNumber(arr[i])) {   // 跳过 . 前面的所有数字
    //             i++;
    //         }
    //         if (i > end || arr[i] != '.') { // 如果不是在 . 这里停下（根本没.或者遇到非数字了）
    //             return false;
    //         } else {    // i现在指向的就是.，检查后面要有是否全是数字
    //             i++;
    //             while(i <= end) {
    //                 if (!isNumber(arr[i])) {
    //                     return false;
    //                 }
    //                 i++;
    //             }
    //             return true;
    //         }
    //     } else {    // 不是 . 或 数字开头
    //         return false;
    //     }
    // }
    //
    // /**
    //  * 某个字符是否是数字
    //  * @param c
    //  * @return
    //  */
    // private boolean isNumber(char c) {
    //     return c >= '0' && c <= '9';
    // }
    //
    // /**
    //  * 检查arr[start:end]这一段是否为整数
    //  * @param arr
    //  * @param start
    //  * @param end
    //  * @return
    //  */
    // private boolean isInteger(char[] arr, int start, int end) {
    //     // 如果实际要检查的这一段是空
    //     if (start > end) {
    //         return false;
    //     }
    //
    //     int i = start;
    //     // 跳过开始的+或-
    //     if (arr[i] == '+' || arr[i] == '-') {
    //         i++;
    //     }
    //     if (i > end) {  // 只有+，-
    //         return false;
    //     }
    //
    //     // 至少一位数字
    //     while (i <= end) {
    //         if (!isNumber(arr[i])) {
    //             return false;
    //         }
    //         i++;
    //     }
    //     return true;
    // }





    /**
     * 按位遍历一遍，将所有非法的情况（如出现多个小数点、多个E或e）排除
     *
     * @param s
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false; // s为空对象或 s长度为0(空字符串)时, 不能表示数值
        }

        boolean isNum = false, isDot = false, ise_or_E = false; // 标记是否遇到数位、小数点、‘e’或'E'
        char[] str = s.trim().toCharArray();  // 删除字符串头尾的空格，转为字符数组，方便遍历判断每个字符（这一步就可以直接使得后面不用考虑首尾的空格了！）
        for (int i = 0; i < str.length; i++) {
            if (str[i] >= '0' && str[i] <= '9') {
                isNum = true; // 判断当前字符是否为 0~9 的数位
            } else if (str[i] == '.') { // 遇到小数点
                if (isDot || ise_or_E) {
                    return false; // 小数点之前可以没有整数，但是不能重复出现小数点、或出现‘e’、'E'
                }
                isDot = true; // 第一次遇到小数点后就置为true，之后就一直为true，以便再遇到重复小数点时判断
            } else if (str[i] == 'e' || str[i] == 'E') { // 遇到‘e’或'E'
                if (!isNum || ise_or_E) {
                    return false; // ‘e’或'E'前面必须有整数，且前面不能重复出现‘e’或'E'
                }
                ise_or_E = true; // 第一次遇到E或e后就置为true，之后就一直为true，以便再遇到重复的E或e时判断
                isNum = false; // 重置isNum，因为‘e’或'E'之后也必须接上整数，防止出现 123e或者123e+的非法情况
            } else if (str[i] == '-' || str[i] == '+') {
                if (i != 0 && str[i - 1] != 'e' && str[i - 1] != 'E') {
                    return false; // 正负号只可能出现在第一个位置，或者出现在‘e’或'E'的后面一个位置
                }
            } else {    // 其它情况均为不合法字符，如字母等
                return false;
            }
        }
        return isNum;   // 遍历一遍不在上面返回false就返回true
    }

}
