package goodsOrder;

import java.util.*;

/**
 * LCR 157. 套餐内商品的排列顺序
 * <p>
 * 某店铺将用于组成套餐的商品记作字符串 goods，其中 goods[i] 表示对应商品。请返回该套餐内所含商品的 全部排列方式 。
 * 返回结果 无顺序要求，但不能含有重复的元素。
 */

public class Solution {

    /**
     * dfs + Set去重
     * 本题要求所有不重复的全排列，涉及到去重
     * 这里使用哈希表进行去重，必须等到确定了一种排列准备添加到哈希表的时候才会去重，但重复排列的递归仍让会进行，因此效率并不高
     */
    // 时间复杂度：O(n!)，其中n为goods的长度。分析：对于一个长度为 n 的字符串（假设字符互不重复），其全排列方案数共有：n*(n-1)...*2*1=n!
    // 空间复杂度：O(n)。分析：dfs的递归深度为n，额外的char数组长度也为n
    // private Set<String> res;    // 这里用Set而不是List是为了防止aab这种有重复元素的字符串出现重复的排列
    // public String[] goodsOrder(String goods) {
    //     res = new HashSet<>();
    //     char[] arr = goods.toCharArray();
    //     StringBuilder sb = new StringBuilder(arr.length);
    //     dfs(arr, 0, arr.length - 1, sb);
    //     return res.toArray(new String[res.size()]);
    // }
    //
    // /**
    //  * 本位字符可以从arr[start:end]中选择
    //  * @param arr
    //  * @param start
    //  * @param end
    //  * @param sb
    //  */
    // private void dfs(char[] arr, int start, int end, StringBuilder sb) {
    //     if (start > end) {
    //         res.add(sb.toString());
    //         return;
    //     }
    //
    //     // 如何固定本位字符？——将其交换到start位置，这样之后的递归就从start之后（所有未选择的字符中选择）
    //     for (int i = start; i <= end; i++) {
    //         sb.append(arr[i]);  // 先将尝试选择的字符加入sb，代表这次选择了这个字符
    //         swap(arr, start, i);    // 然后将选择的字符从数组中交换到start的位置（固定）
    //         dfs(arr, start + 1, end, sb);   // 向下递归
    //         sb.deleteCharAt(sb.length() - 1);   // 撤销操作：从sb删除本次加入的字符，以便下次循环尝试其他字符
    //         swap(arr, start, i);    // 把本次选择的字符换回去
    //     }
    // }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /**
     * dfs + 剪枝
     * 当字符串存在重复字符时，排列方案中也存在重复的排列方案。为排除重复方案，需在固定某位字符时，保证 “每种字符只在此位固定一次” ，即遇到重复字符时不交换，直接跳过。
     * 从 DFS 角度看，此操作称为 “剪枝” 。
     *
     * 这里采用[哈希表]进行剪枝，在尝试字符时先检查此位是否已经尝试过该字符，如果尝试过则跳过
     */
    // 时间复杂度：O(n!)，其中n为goods的长度。分析：最差情况goods中没有重复字符，需要找到完整全排列，时间复杂度为O(n!)
    // 时间复杂度：O(n^2)，其中n为goods的长度。分析：使用了辅助字符数组arr，空间复杂度为O(n)，剪枝用的各个哈希表set累计存储的字符数量最多为 n + (n-1) + ... + 2 + 1 = (n+1)n/2，
    // 占用O(n^2)空间，另外dfs递归深度为n，使用的栈空间为O(n)
    private List<String> res;    // 由于在dfs的过程中进行了剪枝，重复字符在相同位置不会重复添加，因此这里可以用List存
    public String[] goodsOrder(String goods) {
        res = new ArrayList<>();
        char[] arr = goods.toCharArray();
        dfs(arr, 0, arr.length - 1);
        return res.toArray(new String[res.size()]);
    }

    /**
     * 本位字符可以从arr[start:end]中选择
     * @param arr
     * @param start
     * @param end
     */
    private void dfs(char[] arr, int start, int end) {
        if (start > end) {
            res.add(String.valueOf(arr));   // 将字符数组转成字符串并添加进结果中
            return;
        }

        Set<Character> set = new HashSet<>();   // 剪枝用
        // 如何固定本位字符？——将其交换到start位置，这样之后的递归就从start之后（所有未选择的字符中选择）
        for (int i = start; i <= end; i++) {
            if (set.contains(arr[i])) { // 如果此位已经尝试过该字符，直接跳过
                continue;
            }
            set.add(arr[i]);    // 没尝试过，就添加进set
            swap(arr, start, i);    // 将选择的字符从数组中交换到start的位置（固定）
            dfs(arr, start + 1, end);   // 向下递归
            swap(arr, start, i);    // 撤销操作：把本次选择的字符换回去，以便下次循环尝试其他字符
        }
    }


}
