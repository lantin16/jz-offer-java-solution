package dynamicPassword;

/**
 * LCR 182. 动态口令
 *
 * 某公司门禁密码使用动态口令技术。初始密码为字符串 password，密码更新均遵循以下步骤：
 * - 设定一个正整数目标值 target
 * - 将 password 前 target 个字符按原顺序移动至字符串末尾
 * 请返回更新后的密码字符串。
 */

public class Solution {

    /**
     * 三次翻转
     * 前后各自翻转，再整体翻转
     * @param password
     * @param target
     * @return
     */
    // 时间复杂度：O(n)，其中n为password的长度。分析：共遍历两轮password
    // 时间复杂度：O(n)，其中n为password的长度。分析：辅助arr数组长度为n
    public String dynamicPassword(String password, int target) {
        char[] arr = password.toCharArray();
        reverse(arr, 0, target - 1);
        reverse(arr, target, password.length() - 1);
        reverse(arr, 0, password.length() - 1);
        return String.valueOf(arr);
    }

    private void reverse(char[] arr, int l, int r) {
        while(l < r) {
            char tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
            l++;
            r--;
        }
    }


    /**
     * 利用StringBuilder进行拼接（效率高）
     * @param password
     * @param target
     * @return
     */
    // 时间复杂度：O(n)。分析：StringBuilder的append操作的时间复杂度是O(k)，其中k是被追加字符串的长度。
    // 空间复杂度：O(n)
    // public String dynamicPassword(String password, int target) {
    //     StringBuilder sb = new StringBuilder();
    //     sb.append(password, target, password.length()); // StringBuilder可以拼接字符串的某一段
    //     sb.append(password, 0, target);
    //     return sb.toString();
    // }
}
