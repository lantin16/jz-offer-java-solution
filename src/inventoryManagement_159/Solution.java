package inventoryManagement_159;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LCR 159. 库存管理 III
 *
 * 仓库管理员以数组 stock 形式记录商品库存表，其中 stock[i] 表示对应商品库存余量。请返回库存余量最少的 cnt 个商品余量，返回 顺序不限。
 */

public class Solution {

    /**
     * 优先队列实现小根堆
     * 将所有库存都入队，最后弹出前k个数即为最小的k个数
     * @param stock
     * @param cnt
     * @return
     */
    // 时间复杂度：O(nlogn)。分析：如果用小根堆的话所有元素都要入堆
    // 空间复杂度：O(n)
    // public int[] inventoryManagement(int[] stock, int cnt) {
    //     // 找出最小的k个数
    //     // 小顶堆
    //     int[] res = new int[cnt];
    //     int n = stock.length;
    //     PriorityQueue<Integer> minHeap = new PriorityQueue<>(n);
    //     for(int s : stock) {
    //         minHeap.add(s);
    //     }
    //     for(int i = 0; i < cnt; i++) {
    //         res[i] = minHeap.poll();
    //     }
    //     return res;
    // }


    /**
     * 优先队列实现大根堆（掌握）
     * 找前k小用大根堆，找前k大用小根堆
     *
     * 保持堆的大小为K，然后遍历数组中的数字，遍历的时候做如下判断：
     * 1. 若目前堆的大小小于K，直接将当前数字放入堆中。
     * 2. 否则判断当前数字与大根堆堆顶元素的大小关系，如果当前数字比大根堆堆顶还大，这个数就直接跳过；（大根堆堆顶元素是堆中k个里面最大的，如果比它还大说明必不可能是全局最小的k个数之一）
     *    反之如果当前数字比大根堆堆顶小，先poll掉堆顶，再将该数字放入堆中。（说明它暂时可以排到当前最小的k个数之一，把堆顶挤走）
     *
     * @param stock
     * @param cnt
     * @return
     */
    // 时间复杂度：O(nlogk)。分析：堆满时只有比堆顶小的才入堆，减少了调整堆的次数，且每次调整堆的时间复杂度为O(logk)也优于O(logn)
    // 空间复杂度：O(k)
    // public int[] inventoryManagement(int[] stock, int cnt) {
    //     // 找出最小的k个数
    //     // 大顶堆
    //     if(cnt == 0 || stock.length == 0) {
    //         return new int[0];
    //     }
    //
    //     int[] res = new int[cnt];
    //     PriorityQueue<Integer> maxHeap = new PriorityQueue<>(cnt, (x, y) -> y - x); // 默认小根堆，实现大根堆需要传入比较器
    //
    //     for (int num : stock) {
    //         // 堆还没满k个，直接入堆
    //         if (maxHeap.size() < cnt) {
    //             maxHeap.add(num);
    //         } else if(num < maxHeap.peek())  {  // 堆满k个，只有当当前数字小于堆顶元素才入堆
    //             maxHeap.poll(); // 记得先poll掉堆顶，因为要维护堆大小为k
    //             maxHeap.add(num);
    //         }
    //     }
    //
    //     for(int i = 0; i < cnt; i++) {
    //         res[i] = maxHeap.poll();
    //     }
    //     return res;
    // }


    /**
     * 快速排序（掌握）
     */
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(nlogn)。分析：快排的递归深度最好/平均为O(logn)，最差（输入数组完全倒序且每次pivot选择第一个元素）为O(n)
    // public int[] inventoryManagement(int[] stock, int cnt) {
    //     int[] res = new int[cnt];
    //
    //     // 对数组进行快排（升序）
    //     quickSort(stock, 0, stock.length - 1);
    //
    //     // 前k个元素即为最小的k个元素
    //     System.arraycopy(stock, 0, res, 0, cnt);    // 数组拷贝
    //     return res;
    // }
    //
    // private void quickSort(int[] nums, int l, int r) {
    //     // 子数组长度为1时递归终止
    //     if (l >= r) {
    //         return;
    //     }
    //
    //     int i = l, j = r;   // 遍历指针
    //     int pivot = nums[l];    // 每次就选第一个元素为基准数
    //     while (i < j) {
    //         // 由于选择了第一个元素（最左边）为pivot，且最后是pivot与nums[i]交换，所以j得先移
    //         while (i < j && nums[j] >= pivot) { // j从右往左找到首个小于等于pivot的元素
    //             j--;
    //         }
    //
    //         while (i < j && nums[i] <= pivot) { // i从左往右找到首个大于等于pivot的元素
    //             i++;
    //         }
    //
    //         swap(nums, i, j);  // 交换，使得小于等于pivot的元素到左边，大于等于pivot的到右边
    //     }
    //     // i == j 退出循环，将pivot与nums[i]交换
    //     swap(nums, l, i);
    //
    //     // 递归对左右子数组分别进行哨兵划分
    //     quickSort(nums, l, i - 1);
    //     quickSort(nums, i + 1, r);
    // }
    //
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    /**
     * 快速选择（掌握）
     * 其实最后所求只用求最小的k个数，至于顺序并没有要求，因此没必要全排序，这里用快速排序哨兵划分的思想找到最小的k个即可（快速选择）
     * 根据快速排序原理，如果某次哨兵划分后 基准数正好是第 k+1 小的数字 ，那么此时基准数左边的所有数字便是 最小的 k 个数 。
     * @param stock
     * @param cnt
     * @return
     */
    // 时间复杂度：O(n)。分析：对于长度为 n 的数组执行哨兵划分操作的时间复杂度为 O(n)，每轮哨兵划分后根据 k 和 i 的大小关系选择性单边递归，
    // 由于 i 分布的随机性，则向下递归子数组的平均长度为 n/2；平均情况下，划分操作复杂度为O(n),O(n/2),O(n/4)...根据等比数列求和，总体时间复杂度为O(n)
    // 空间复杂度：O(logn)。分析：划分函数的平均递归深度为O(logn)
    int[] res;
    public int[] inventoryManagement(int[] stock, int cnt) {
        res = new int[cnt];

        // 对数组快速选择，找到第k+1小的数
        quickSelect(stock, 0, stock.length - 1, cnt);

        // 前k个元素即为最小的k个元素
        System.arraycopy(stock, 0, res, 0, cnt);    // 数组拷贝
        return res;
    }

    /**
     * 想要确定nums数组升序排列中下标为k(第k+1小的数)的数，如果找到了，则根据快排的性质，其左边的k个数就是nums中最小的k个数
     * 这样每次只用递归一边的子数组
     * @param nums
     * @param l
     * @param r
     * @param k
     */
    private void quickSelect(int[] nums, int l, int r, int k) {
        if (l >= r) {
            return;
        }
        int i = l, j = r;
        // 选择nums[l]作为基准
        int pivot = nums[l];

        while (i < j) {
            // 先移j
            while(i < j && nums[j] >= pivot) {
                j--;
            }

            while (i < j && nums[i] <= pivot) {
                i++;
            }

            swap(nums, i, j);
        }
        swap(nums, l, i);

        if (i == k) {   // 已经找到全局第k+1小的数
            System.arraycopy(nums, 0, res, 0, k);
        } else if (i < k) { // 第k+1小的数在右子数组中，递归右子数组
            quickSelect(nums, i + 1, r, k);
        } else {    // 第k+1小的数在左子数组中，递归左子数组
            quickSelect(nums, l, i - 1, k);
        }
    }

}
