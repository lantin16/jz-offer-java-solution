package maxAltitude;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LCR 183. 望远镜中最高的海拔
 *
 * 科技馆内有一台虚拟观景望远镜，它可以用来观测特定纬度地区的地形情况。该纬度的海拔数据记于数组 heights ，其中 heights[i] 表示对应位置的海拔高度。
 * 请找出并返回望远镜视野范围 limit 内，可以观测到的最高海拔值。
 *
 * 本题难点： 如何在每次窗口滑动后，将 “获取窗口内最大值” 的时间复杂度从 O(limit) 降低至 O(1) 。
 */

public class Solution {

    /**
     * 优先队列（性能差）
     * 维护一个容量为limit的优先队列，利用它来得到滑动窗口中的最大值
     * @param heights
     * @param limit
     * @return
     */
    // 时间复杂度：O(nlogk)，其中n为heights长度，k=limit。分析：弹出优先队列的队首元素以及移除某个元素调整堆的时间复杂度都为O(logk)
    // 时间复杂度：O(k)，其中k=limit。
    // public int[] maxAltitude(int[] heights, int limit) {
    //     if (heights.length == 0) {
    //         return new int[0];
    //     }
    //     PriorityQueue<Integer> pq = new PriorityQueue<>(limit, (x, y) -> y-x);  // 大根堆需要自定义规则
    //     int l = 0, r = limit - 1;
    //     // 先加进去前limit个数
    //     for(int i = l; i <= r; i++) {
    //         pq.add(heights[i]);
    //     }
    //     int[] res = new int[heights.length - limit + 1];
    //     int maxL = heights.length - limit;  // l的最大值
    //     while(l < maxL) {   // 这里不用等号是因为最后一个窗口没有下一个元素，访问heights[++r]会越界
    //         res[l] = pq.peek(); // 先将本窗口中的最大值存入
    //         pq.remove(heights[l]);  // 从队列中删除左边界（如果有多个左边界值则删除遇到的第一个）
    //         l++;    // 左边界右移
    //         pq.add(heights[++r]);   // 先右移右边界，再将新的右边界加入队列排序
    //     }
    //     res[l] = pq.peek(); // 最后一个滑动窗口 l = maxL 时还需要存入
    //     return res;
    // }


    /**
     * 单调队列（重点）
     * 回忆“最小栈”问题，其使用 单调栈 实现了随意入栈、出栈情况下的 O(1) 时间获取 “栈内最小值” 。本题同理，不同点在于 “出栈操作” 删除的是 “列表尾部元素” ，而 “窗口滑动” 删除的是 “列表首部元素” 。
     *
     * - Queue 接口：表示一个先进先出（FIFO）的队列。常用方法包括 add(), remove(), poll(), peek() 等。
     * - Deque 接口：表示一个双端队列（double-ended queue），既可以作为队列（FIFO）使用，也可以作为栈（LIFO）使用。
     *   常用方法包括 addFirst(), addLast(), removeFirst(), removeLast(), peekFirst(), peekLast() 等。
     *
     *  小技巧：本题队列中存的是下标而不是具体高度，因为具体高度可以通过下标访问数组得到，存下标是为了更方便判断队首元素是否已经不在窗口内，以便及时移除
     * @param heights
     * @param limit
     * @return
     */
    // 时间复杂度：O(n)，其中n为heights数组长度。分析：每个元素最多仅入队和出队一次，故线性遍历 heights 占用 O(2n) = O(n)
    // 空间复杂度：O(k)，其中k=limit。
    public int[] maxAltitude(int[] heights, int limit) {
        int len = heights.length;
        if (len == 0 || limit == 0) {
            return new int[0];
        }

        Deque<Integer> deque = new LinkedList<>();  // 本题可能需要从队尾弹出元素，因此用双端队列的实现，前面用Deque接口
        int[] res = new int[len - limit + 1];   // 总共有 len - limit + 1 个窗口
        // 窗口未形成，先将前limit个数尝试入队，此时队首元素不用出队，但要维护队列下标对应的高度非严格递减
        for(int i = 0; i < limit; i++) {
            while(!deque.isEmpty() && heights[deque.getLast()] < heights[i]) {
                deque.removeLast(); // 将尾部往前小于新高度的高度下标全部弹出，确保单调性
            }
            deque.addLast(i);
        }

        // 滑动窗口已形成，后续每一个新窗口都需要保存最大值到结果数组
        res[0] = heights[deque.getFirst()]; // 第一个窗口的最大值先写入，因为循环内需要先判断队首队尾元素才能存入新窗口的最大值，而第一个窗口已经处理好了需要先写入结果
        int r;  // 窗口有边界
        for (r = limit; r < len; r++){  // 遍历窗口的右边界
            int l = r - limit + 1;  // 此时窗口的左边界
            // 判断队首元素是否已不再窗口中
            if(deque.getFirst() < l) {
                deque.removeFirst();
            }

            // 由于右边界右移，需要维护队列的高度非严格递减性
            while(!deque.isEmpty() && heights[deque.getLast()] < heights[r]) {
                deque.removeLast(); // 将尾部往前小于右边界高度的高度下标全部弹出，确保单调性
            }
            deque.addLast(r); // 将新右边界入队

            // 获取当前窗口的最大值（队首元素）并存入结果数组
            res[l] = heights[deque.getFirst()];
        }
        return res;
    }


}
