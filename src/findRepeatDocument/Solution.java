package findRepeatDocument;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LCR 120. 寻找文件副本
 *
 * 设备中存有 n 个文件，文件 id 记于数组 documents。若文件 id 相同，则定义为该文件存在副本。请返回任一存在副本的文件 id。
 */

public class Solution {

    /**
     * 用HashSet记录出现过的文件id
     * @param documents
     * @return
     */
    // 时间复杂度：O(n)
    // 时间复杂度：O(n)
    // public int findRepeatDocument(int[] documents) {
    //     Set<Integer> fidSet = new HashSet<>();
    //     int res = 0;
    //     for(int id : documents) {
    //         if(fidSet.contains(id)) {
    //             res = id;
    //             break;
    //         }
    //         fidSet.add(id);
    //     }
    //     return res;
    // }



    /**
     * 先排序，再挨个找相邻相同的id
     * @param documents
     * @return
     */
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(logn)。分析：sort排序需要O(logn)的栈空间
    // public int findRepeatDocument(int[] documents) {
    //     Arrays.sort(documents);
    //     int pre = 0;
    //     for (int i = 1; i < documents.length; i++) {
    //         if (documents[i] == pre) {
    //             break;
    //         }
    //         pre = documents[i];
    //     }
    //     return pre;
    // }





    /**
     * 原地哈希
     * 题目有前提：在一个长度为 n 的数组 documents 里的所有数字都在 0 ~ n-1 的范围内
     * 因此可以自定义一种哈希映射规则：元素i将会被映射到数组中索引i的位置。这样，重复的元素就会被映射到同一个位置。
     *
     * @param documents
     * @return
     */
    // 时间复杂度：O(n)
    // 时间复杂度：O(1)
    public int findRepeatDocument(int[] documents) {
        int n = documents.length;
        int res = 0;

        // 从前往后遍历，按照映射规则将每一个元素交换到其对应的位置
        for(int i = 0; i < n; i++) {
            // 可能交换过来的数仍然不是索引i这应该放的数，因此需要一直交换，直到i这里被换过来是i或者documents[i]对应的位置已经有了一个相同的数documents[i]
            while(i != documents[i] && documents[documents[i]] != documents[i]) {
                swap(documents, i, documents[i]);
            }

            // 如果是因为documents[i]对应的位置已经有了一个相同的数documents[i]才跳出while循环的，证明已经找到了重复的文件id
            if(i != documents[i] && documents[documents[i]] == documents[i]) {
                res = documents[i];
                break;
            }
        }

        return res;
    }


    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
