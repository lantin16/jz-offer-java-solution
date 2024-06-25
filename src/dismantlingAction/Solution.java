package dismantlingAction;

import java.util.*;

/**
 * LCR 167. 招式拆解 I
 *
 * 某套连招动作记作序列 arr，其中 arr[i] 为第 i 个招式的名字。请返回 arr 中最多可以出连续不重复的多少个招式。
 */

public class Solution {

    /**
     * 滑动窗口 + 哈希表
     * 维护Set中始终仅包含无重复字符窗口中的所有字符
     * @param arr
     * @return
     */
    // 时间复杂度：O(n)，其中n为arr的长度。分析：左右指针最多均移到末尾
    // 空间复杂度：O(1)。分析：Set中存放的是无重复字符窗口内的所有字符，而字符的 ASCII 码范围为 0 ~ 127 ，哈希表最多使用 O(128)=O(1) 大小的额外空间。
    // public int dismantlingAction(String arr) {
    //     int len = arr.length();
    //     char[] charArr = arr.toCharArray();
    //     Set<Character> set = new HashSet<>();   // 记录窗口中的字符
    //     int l = 0, r = 0;   // 窗口左右边界
    //     int res = 0;
    //     while(r < len) {
    //         // 考虑是否将charArr[r]纳入窗口
    //         if(set.contains(charArr[r])) {  // 如果窗口中已经有该字符了，则先右移左边界并删除set中对应的元素（本次先不移右边界）
    //             set.remove(charArr[l]);
    //             l++;
    //         } else {    // 如果窗口中没有该字符，则可以右移右边界扩大窗口并将其加入set中
    //             set.add(charArr[r]);
    //             res = Math.max(res, r - l + 1);
    //             r++;
    //         }
    //     }
    //     return res;
    // }


    /**
     * 滑动窗口 + 哈希表 （掌握）
     * 上面那种写法仅用Set记录窗口出现的元素，当检测到重复字符时左边界只能一个一个右移，每移动一次都要检查一遍是否将重复元素踢出去了，这样效率并不高
     * 因此这里改用哈希表 Map 统计字符 arr[j] 上一次出现的索引，以便左边界直接跳到能将重复字符剔除窗口的位置。
     * @param arr
     * @return
     */
    // 时间复杂度：O(n)，其中n为arr的长度。分析：左右指针最多均移到末尾
    // 空间复杂度：O(1)。分析：字符的 ASCII 码范围为 0 ~ 127 ，哈希表最多使用 O(128)=O(1) 大小的额外空间。
    // public int dismantlingAction(String arr) {
    //     int len = arr.length();
    //     char[] charArr = arr.toCharArray();
    //     Map<Character, Integer> lastMap = new HashMap<>();  // 记录字符上一次出现的位置
    //     int l = 0, r = 0;   // 窗口左右边界
    //     int res = 0;
    //     while(r < len) {
    //         // 考虑新的右边界charArr[r]
    //         if(lastMap.containsKey(charArr[r]) && lastMap.get(charArr[r]) >= l) {  // 如果窗口中已经有该字符了，则直接将左边界右移到上一次出现的位置的后一个位置
    //             l = lastMap.get(charArr[r]) + 1;
    //         } else {    // 如果窗口中没有该字符，则可以右移右边界扩大窗口并将其加入set中
    //             res = Math.max(res, r - l + 1);
    //
    //         }
    //         lastMap.put(charArr[r], r); // 更新上一次出现的位置
    //         r++;
    //     }
    //     return res;
    // }


    /**
     * 一维dp + 哈希表
     * @param arr
     * @return
     */
    // 时间复杂度：O(n)，其中n为arr的长度
    // 空间复杂度：O(n)，其中n为arr的长度。分析：用到了辅助字符数组charArr，dp数组空间复杂度均为O(n)，哈希表的空间复杂度为O(128) = O(1)
    // public int dismantlingAction(String arr) {
    //     int len = arr.length();
    //     if(len == 0) {
    //         return 0;
    //     }
    //
    //     Map<Character, Integer> lastMap = new HashMap<>();  // 记录字符上一次出现的位置
    //     char[] charArr = arr.toCharArray();
    //
    //     // dp[j]：以charArr[i]结尾的最长连续不重复子串的长度
    //     int[] dp = new int[len];
    //
    //     // 递推公式
    //     // 对于右边界j，设字符arr[j]在左边上一次出现的位置为i
    //     // 若dp[j-1] < j - i，即以arr[j-1]结尾的最长不重复子串并不包括arr[i]，那么arr[j]可以放心加入arr[j-1]结尾的这一段，dp[j] = dp[j-1] + 1
    //     // 若dp[j-1] >= j - i，即以arr[j-1]结尾的最长不重复子串已经包括了arr[i]，如果加入arr[j]会出现重复，那么就需要将i及之前的元素都抛弃，dp[j] = j - i
    //
    //     // 初始化，后面计算dp[j]需要用到dp[j-1]，因此要先初始化dp[0]
    //     dp[0] = 1;  // 单个字符本身一定满足
    //     int res = 1;    // 非空字符串结果至少为1
    //     lastMap.put(charArr[0], 0); // 将第一个字符加入哈希表
    //
    //     // 遍历顺序：从左往右
    //     for(int j = 1; j < len; j++) {  // 从1开始
    //         // 找到charArr[j]上一次出现的位置i
    //         int i = lastMap.getOrDefault(charArr[j], -1);
    //         if(dp[j-1] < j - i) {
    //             dp[j] = dp[j-1] + 1;
    //         } else{
    //             dp[j] = j - i;
    //         }
    //         res = Math.max(res, dp[j]);
    //         lastMap.put(charArr[j], j);
    //     }
    //
    //     return res;
    // }


    /**
     * dp（滚动变量）+ 哈希表（掌握）
     * 由于dp[j]只跟dp[j-1]有关，因此可以用滚动变量进行状态压缩，节省空间
     * @param arr
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)。分析：用到了辅助字符数组charArr，复杂度均为O(n)，哈希表的空间复杂度为O(128) = O(1)
    public int dismantlingAction(String arr) {
        int len = arr.length();
        if(len == 0) {
            return 0;
        }

        Map<Character, Integer> lastMap = new HashMap<>();  // 记录字符上一次出现的位置
        char[] charArr = arr.toCharArray();

        // 递推公式
        // 对于右边界j，设字符arr[j]在左边上一次出现的位置为i
        // 若 pre < j - i，即以arr[j-1]结尾的最长不重复子串并不包括arr[i]，那么arr[j]可以放心加入arr[j-1]结尾的这一段，pre = pre + 1
        // 若 pre >= j - i，即以arr[j-1]结尾的最长不重复子串已经包括了arr[i]，如果加入arr[j]会出现重复，那么就需要将i及之前的元素都抛弃，pre = j - i

        // 这里初始化为0下面的循环j就要从0开始
        int pre = 0;
        int res = 0;

        // 遍历顺序：从左往右
        for(int j = 0; j < len; j++) {  // 从0开始
            // 找到charArr[j]上一次出现的位置i
            int i = lastMap.getOrDefault(charArr[j], -1);
            pre = pre < j - i ? pre + 1 : j - i;
            res = Math.max(res, pre);
            lastMap.put(charArr[j], j);
        }

        return res;
    }
}
