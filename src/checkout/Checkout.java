package checkout;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LCR 184. 设计自助结算系统
 *
 * 请设计一个自助结账系统，该系统需要通过一个队列来模拟顾客通过购物车的结算过程，需要实现的功能有：
 * - get_max()：获取结算商品中的最高价格，如果队列为空，则返回 -1
 * - add(value)：将价格为 value 的商品加入待结算商品队列的尾部
 * - remove()：移除第一个待结算的商品价格，如果队列为空，则返回 -1
 * 注意，为保证该系统运转高效性，以上函数的均摊时间复杂度均为 O(1)
 */

/**
 * 普通队列 + 单调双端队列
 * 本题类似于最小栈问题，最小栈是要求O(1)时间求出当前栈中的最小元素，本题是要求O(1)时间求出当前队列中的最大元素，因此考虑用单调队列维护最大值
 *
 * 注意：
 * 1. 由于单调队列涉及到删除队尾元素维持单调性，因此需要用双端队列的接口Deque接收而不能用Queue接收，否则没有removeLast()方法
 * 2. 同样地，这里要非严格单调减而不能用严格单调减
 */
public class Checkout {

    private Queue<Integer> queue;
    private Deque<Integer> decrQ;   // 非严格单调减 队列（确保队首元素为queue中的最大值）

    public Checkout() {
        queue = new LinkedList<>();
        decrQ = new LinkedList<>();
    }

    public int get_max() {
        return decrQ.isEmpty() ? -1 : decrQ.getFirst();
    }

    public void add(int value) {
        queue.offer(value);
        // 如果待入队的元素比队尾元素大，则为了维持非严格递减性，需要先弹出队尾元素直至符合单调性再将新元素入队
        while(!decrQ.isEmpty() && decrQ.getLast() < value) {
            decrQ.removeLast();
        }
        decrQ.addLast(value);
    }

    public int remove() {
        if(queue.isEmpty()) {
            return -1;
        }

        int x = queue.poll();
        if(x == decrQ.getFirst()) {
            decrQ.removeFirst();
        }
        return x;
    }
}

/**
 * Your Checkout object will be instantiated and called as such:
 * Checkout obj = new Checkout();
 * int param_1 = obj.get_max();
 * obj.add(value);
 * int param_3 = obj.remove();
 */
