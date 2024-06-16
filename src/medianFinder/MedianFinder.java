package medianFinder;

/**
 * LCR 160. 数据流中的中位数
 *
 * 中位数 是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 */

import java.util.PriorityQueue;

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
public class MedianFinder {

    private PriorityQueue<Integer> minHeap; // 小根堆存较大的一半数
    private PriorityQueue<Integer> maxHeap; // 大根堆存较小的一半数，如果总数为奇数则多存一个

    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((x,y) -> y-x);
    };

    // 时间复杂度：O(logn)。分析：堆的插入和弹出操作使用 O(logn) 时间。
    public void addNum(int num) {
        if((minHeap.size() + maxHeap.size()) % 2 == 0) {    // 若原来总数为偶数
            // 加入num后总数将为奇数，大根堆将会多存一个数
            minHeap.add(num);   // 为了确保小根堆在加入num后仍为较大的一半数，先将num加入小根堆调整
            maxHeap.add(minHeap.poll());    // 再将小根堆的堆顶元素弹出插入大根堆，这样就能保证插入大根堆的一定是小于等于小根堆所有元素的数
        } else {
            // 同理
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }
    }

    // 时间复杂度：O(1)。分析：只需要访问大小根堆的堆顶元素即可
    public double findMedian() {
        return (minHeap.size() + maxHeap.size()) % 2 == 0 ? (minHeap.peek() + maxHeap.peek()) / 2.0 : maxHeap.peek();
    }
}
