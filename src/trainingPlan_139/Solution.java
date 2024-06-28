package trainingPlan_139;

/**
 * LCR 139. 训练计划 I
 *
 * 教练使用整数数组 actions 记录一系列核心肌群训练项目编号。为增强训练趣味性，需要将所有奇数编号训练项目调整至偶数编号训练项目之前。
 * 请将调整后的训练项目编号以 数组 形式返回。
 */

public class Solution {

    /**
     * 双指针
     * 左指针从左往右遍历，跳过遇到的奇数（因为奇数本来就应该在左边）
     * 右指针从右往左遍历，跳过遇到的偶数（因为偶数本来就应该在右边）
     * 当左右指针还没有相遇前如果停下来就要交换所指的奇偶数
     * @param actions
     * @return
     */
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int[] trainingPlan(int[] actions) {
        int l = 0, r = actions.length - 1;
        while(l < r) {
            while(l < r && actions[l] % 2 != 0) {    // l跳过奇数
                l++;
            }
            while(l < r && actions[r] % 2 == 0) {    // r跳过偶数
                r--;
            }

            if(l >= r) {
                break;
            } else {
                // 在l和r没有相遇的情况下，走到这里时l正指向前面的偶数，r指向后面的奇数，此时交换奇偶
                swap(actions, l, r);
            }
        }

        return actions;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
