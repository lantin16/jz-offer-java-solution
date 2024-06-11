package countNumbers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LCR.135 报数
 *
 * 实现一个十进制数字报数程序，请按照数字从小到大的顺序返回一个整数数列，该数列从数字 1 开始，到最大的正整数 cnt 位数字结束。
 */

public class Solution {

    // 时间复杂度：O(10^cnt)
    // 空间复杂度：O(1)
    // public int[] countNumbers(int cnt) {
    //     // 计算有多少个数，也是报数到的最大的数
    //     int num = (int)Math.pow(10, cnt) - 1;   // 最大的位数为cnt则最大的数为10^cnt - 1
    //     int[] res = new int[num];
    //     // 开始报数！
    //     for(int i = 0; i < num; i++) {
    //         res[i] = i + 1;
    //     }
    //     return res;
    // }







    /**
     * 大数打印拓展
     * 这题如果要大数越界情况下的打印，则不能简单通过+1来得到下一个数，因为到后面可能会越界
     *
     * 采用分治法
     *
     * @param cnt
     * @return 返回的List中的元素为每个数的字符串形式
     */
    private List<String> res;
    private final char[] NUM = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private StringBuilder sb;
    public List<String> countNumbers(int cnt) {
        res = new ArrayList<>();
        sb = new StringBuilder();
        for (int i = 1; i <= cnt; i++) {
            // sb.setLength(0);    // 清空StringBuilder，这里其实不用清空，因为在尝试完每一位后都会及时撤销
            dfs(i, 0);  // 生成长度为i的数字，且从第0位开始确定
        }
        // 对于本题如果需要返回int数组则做一下转换
        // int[] intArr = res.stream()
        //         .mapToInt(Integer::parseInt)// String转int
        //         .toArray(); // 将流转换为int数组

        return res;
    }

    /**
     * 生成长度为len的数字，正在确定第x位（从左往右，x从0开始）
     * @param len
     * @param x
     */
    private void dfs(int len, int x) {
        if (x == len) { // 前面len位数字都确定完了，结束递归，添加到结果中
            res.add(sb.toString());
            return;
        }

        int start = x == 0 ? 1 : 0; // 如果是第0位（最左边的最高位）则不能为0
        for (int i = start; i < 10; i++) {
            sb.append(NUM[i]);  // 确定本位数字
            dfs(len, x + 1);    // 确定下一位数字（第x+1位）
            sb.deleteCharAt(sb.length() - 1);   // 删除本位数字，尝试本位的下一个种可能（撤销操作）
        }
    }


}
