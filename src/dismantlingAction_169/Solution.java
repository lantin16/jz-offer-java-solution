package dismantlingAction_169;

import java.util.Arrays;

/**
 * LCR 169. 招式拆解 II
 *
 * 某套连招动作记作仅由小写字母组成的序列 arr，其中 arr[i] 第 i 个招式的名字。请返回第一个只出现一次的招式名称，如不存在请返回空格。
 */

public class Solution {

    /**
     * 用数组记录各个字母的出现情况，由于只有小写字母，因此数组长度只用26
     * 具体算法：
     * 1. 初始时数组元素全为-1，-1代表对应字母未出现过
     * 2. 遍历字符串各个字符：
     *    - 如果当前字母在数组中的值为-1，代表现在是首次出现，将其索引存入数组对应位置
     *    - 如果当前字母在数组中的值>=0，代表之前已经出现过一次才将数组的值设为了索引，现在是第二次出现了，那么属于多次出现，将数组中对应的值置为-2，代表该字母多次出现
     *    - 如果当前字母在数组中的值为-2，代表之前已经至少出现2次，现在也属于多次出现，保持-2不变即可
     * 3. 遍历数组，只考虑值>=0的元素（代表只出现了一次且数组中的值就是该字母首次出现的位置），选出这些只出现过一次的字母中的第一个并返回
     * @param arr
     * @return
     */
    // 时间复杂度：O(n)，其中n为arr的长度。分析：遍历字符串的每个字符的耗时O(n)，遍历first数组耗时O(26)=O(1)
    // 时间复杂度：O(1)，其中n为arr的长度。分析：额外辅助数组first占用空间O(26)=O(1)
    // public char dismantlingAction(String arr) {
    //     int[] first = new int[26];
    //     Arrays.fill(first, -1); // -1代表没出现过
    //     for(int i = 0; i < arr.length(); i++) {
    //         char c = arr.charAt(i);
    //         if(first[c - 'a'] == -1) {
    //             first[c - 'a'] = i; // 将第一次出现的索引存在对应位置
    //         } else if(first[c - 'a'] >= 0) {    // 这是第二次出现
    //             first[c - 'a'] = -2;
    //         }
    //         // -2代表已经重复了，跳过
    //     }
    //
    //     int firstIdx = Integer.MAX_VALUE;
    //     char res = ' ';
    //     for(int j = 0; j < 26; j++) {
    //         if(first[j] >= 0 && first[j] < firstIdx) {
    //             firstIdx = first[j];
    //             res = (char)('a' + j);
    //         }
    //     }
    //     return res;
    // }


    /**
     * 数组记录字母的出现次数
     * @param arr
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public char dismantlingAction(String arr) {
        int[] cnt = new int[26];
        char[] chars = arr.toCharArray();
        // 第一次遍历arr记录各字母的出现次数
        for(char c : chars) {
            cnt[c - 'a']++;
        }

        // 第二次遍历arr找出第一个出现次数为1的字母
        for(char c : chars) {
            if(cnt[c - 'a'] == 1) {
                return c;
            }
        }

        return ' ';
    }
}
