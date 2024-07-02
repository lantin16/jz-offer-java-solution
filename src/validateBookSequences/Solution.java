package validateBookSequences;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LCR 148. 验证图书取出顺序
 *
 * 现在图书馆有一堆图书需要放入书架，并且图书馆的书架是一种特殊的数据结构，只能按照 一定 的顺序 放入 和 拿取 书籍。
 * 给定一个表示图书放入顺序的整数序列 putIn，请判断序列 takeOut 是否为按照正确的顺序拿取书籍的操作序列。你可以假设放入书架的所有书籍编号都不相同。
 */

public class Solution {

    /**
     * 栈来模拟取书和放书的过程
     * 题目指出 “putIn 是 takeOut 的排列” 。因此，无需考虑 putIn 和 takeOut 长度不同 或 包含元素不同 的情况。
     * @param putIn
     * @param takeOut
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    // public boolean validateBookSequences(int[] putIn, int[] takeOut) {
    //     Deque<Integer> stk = new LinkedList<>();
    //     int len = putIn.length;
    //     int i = 0;  // 指向putIn中待放入元素的下标
    //     for(int j = 0; j < len; j++) {
    //         // 如果要取出的数与栈顶的数不相等，则一直将putIn的元素入栈，直到takeOut[j]与栈顶元素匹配上
    //         while((stk.isEmpty() || takeOut[j] != stk.peek()) && i < len) {
    //             stk.push(putIn[i++]);
    //         }
    //         // 如果是由于putIn中的元素已经全部入栈过了，即 i==len 跳出循环的，仍然没有匹配上takeOut[j]则说明顺序不可行
    //         if(takeOut[j] != stk.peek()) {
    //             return false;
    //         }
    //         // 如果匹配上了，则弹出栈顶元素，下一次循环继续匹配下一个takeOut中的数
    //         stk.pop();
    //     }
    //     return true;
    // }


    /**
     * 另一种写法
     * @param putIn
     * @param takeOut
     * @return
     */
    public boolean validateBookSequences(int[] putIn, int[] takeOut) {
        Deque<Integer> stk = new LinkedList<>();
        int i = 0;  // takeOut待匹配的元素的下标
        for (int x : putIn) {
            stk.push(x);    // 先入栈
            while(!stk.isEmpty() && takeOut[i] == stk.peek()) { // 如果匹配上则一直出栈
                stk.pop();
                i++;
            }
        }
        return stk.isEmpty();
    }
}
