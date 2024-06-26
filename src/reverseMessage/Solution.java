package reverseMessage;

/**
 * LCR 181. 字符串中的单词反转
 *
 * 你在与一位习惯从右往左阅读的朋友发消息，他发出的文字顺序都与正常相反但单词内容正确，为了和他顺利交流你决定写一个转换程序，把他所发的消息 message 转换为正常语序。
 * 注意：输入字符串 message 中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 */

public class Solution {

    /**
     * 双指针，从右往左逐个单词读取并添加
     * 建议画图模拟双指针的移动帮助理解
     * @param message
     * @return
     */
    // 时间复杂度：O(n)，其中n为message的长度。分析：l指针从右往左遍历一遍message
    // 空间复杂度：O(n)，其中n为message的长度。分析：StringBuilder中的字符串长度 <= n，占用O(n)的额外空间
    public String reverseMessage(String message) {
        message = message.trim();   // 先将首尾的空格去除
        StringBuilder sb = new StringBuilder();
        int l = message.length() - 1, r = message.length(); // 双指针从右往左移动，由于字符串截取时end是不包括的，因此r的初始值为len而不是len-1
        while(l >= 0) {
            if(message.charAt(l) == ' ') {  // 如果l指向的是空格（单词间的从右往左第一个空格）
                sb.append(message, l + 1, r).append(' ');   // 证明已经检索到了一个单词，将其添加到sb中并追加一个分隔空格
                while(l - 1 >= 0 && message.charAt(l - 1) == ' ') { // 将多余的空格全部跳过
                    l--;
                }
                // 退出循环时l指向最后一个空格或指向0，r直接移到这里，然后l再左移一位
                r = l--;
            } else {    // 如果l指向的是非空格（单词中的字符），则继续左移
                l--;
            }
        }
        sb.append(message, l + 1, r);   // 退出循环时l指向-1，还需要将message的最左边的单词添加进sb末尾且不用再跟空格
        return sb.toString();
    }
}
