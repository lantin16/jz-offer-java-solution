package crackPassword;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LCR 164. 破解闯关密码
 *
 * 闯关游戏需要破解一组密码，闯关组给出的有关密码的线索是：
 * - 一个拥有密码所有元素的非负整数数组 password
 * - 密码是 password 中所有元素拼接后得到的最小的一个数
 * 请编写一个程序返回这个密码。
 *
 * 说明：
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 */

public class Solution {

    /**
     * 用优先队列数组存储以0~9各个数字开头的各个数字
     * @param password
     * @return
     */
    // 时间复杂度：O(nlogk)，其中n为password的长度，k为password中以某个数开头的最多个数
    // 空间复杂度：O(n)
    // public String crackPassword(int[] password) {
    //     // 优先队列数组，pqs[i]表示password中所有以i开头的数字的优先队列，队列中按照自定义规则排序
    //     PriorityQueue<String>[] pqs = new PriorityQueue[10];
    //
    //     // 初始化数组中的优先队列元素并自定义排序规则（不能按照默认的字典顺序，如：2，21按照字典排序应该2排在21的前面，但212比221小，在本题21应该排在2的前面）
    //     // 具体排序规则：将两个数字字符串交换拼接，如果s1+s2比s2+s1字典顺序更小则s1应该排在s2前面
    //     Arrays.fill(pqs, new PriorityQueue<String>((s1, s2) -> (s1 + s2).compareTo(s2 + s1)));
    //
    //     String numStr = null;
    //     for(int num : password) {
    //         numStr = String.valueOf(num);
    //         pqs[numStr.charAt(0) - '0'].add(numStr);    // 按照首位数字存到对应的优先队列中并排序
    //     }
    //     StringBuilder sb = new StringBuilder();
    //
    //     // 按照首位从0~9，内部按照自定义规则的顺序依次将pqs中的数拼到结果中
    //     for (PriorityQueue<String> pq : pqs) {
    //         while (!pq.isEmpty()) {
    //             sb.append(pq.poll());
    //         }
    //     }
    //     return sb.toString();
    // }





    /**
     * 用内置函数直接对输入数组按照自定义规则进行排序
     * @param password
     * @return
     */
    // 时间复杂度：O(nlogn)，其中n为password的长度。分析：使用快排或内置函数的平均时间复杂度为 O(nlogn)。
    // 空间复杂度：O(n)。分析：字符串数组 strs 占用线性大小的额外空间。
    // public String crackPassword(int[] password) {
    //     String[] strs = new String[password.length];
    //     // 将int型数组转为String数组，因为后续排序规则需要比较String的字典顺序
    //     for (int i = 0; i < password.length; i++) {
    //         strs[i] = String.valueOf(password[i]);
    //     }
    //
    //     // 调用内置函数Arrays.sort()来对strs进行排序
    //     // 自定义排序规则：将两个数字字符串交换拼接，如果s1+s2比s2+s1字典顺序更小则s1应该排在s2前面
    //     Arrays.sort(strs, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
    //
    //     StringBuilder sb = new StringBuilder();
    //     // 遍历排序完的strs数组，将元素依次添加到StringBuilder中
    //     for (String s : strs) {
    //         sb.append(s);
    //     }
    //     return sb.toString();
    // }


    /**
     * 按照自定义规则进行快速排序
     * @param password
     * @return
     */
    // 时间复杂度：O(nlogn)，其中n为password的长度。分析：使用快排或内置函数的平均时间复杂度为 O(nlogn)。
    // 空间复杂度：O(n)。分析：字符串数组 strs 占用线性大小的额外空间。
    public String crackPassword(int[] password) {
        String[] strs = new String[password.length];
        // 将int型数组转为String数组，因为后续排序规则需要比较String的字典顺序
        for (int i = 0; i < password.length; i++) {
            strs[i] = String.valueOf(password[i]);
        }

        quickSort(strs, 0, password.length - 1);

        StringBuilder sb = new StringBuilder();
        // 遍历排序完的strs数组，将元素依次添加到StringBuilder中
        for (String s : strs) {
            sb.append(s);
        }
        return sb.toString();
    }

    private void quickSort(String[] strs, int l, int r) {
        // 如果子数组长度为1（即l、r重合）则递归终止
        if (l >= r) {
            return;
        }

        int i = l, j = r;
        String pivot = strs[l];    // 取子数组左端为基准
        while (i < j) {
            // j先移，跳过应该排在pivot后面的
            while (i < j && (strs[j] + pivot).compareTo(pivot + strs[j]) >= 0) {    // 注意这里要取等号
                j--;
            }

            // i跳过应该排在pivot前面的
            while (i < j && (strs[i] + pivot).compareTo(pivot + strs[i]) <= 0) {    // 注意这里要取等号
                i++;
            }

            swap(strs, i, j);
        }
        swap(strs, i, l);   // 交换pivot到i的位置

        // 对左右子数组进行递归快速排序
        quickSort(strs, l, i - 1);
        quickSort(strs, i + 1, r);
    }

    private void swap(String[] strs, int i, int j) {
        String tmp = strs[i];
        strs[i] = strs[j];
        strs[j] = tmp;
    }
}
