package minStack;


import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * LCR 147. 最小栈
 *
 * 请你设计一个 最小栈 。它提供 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类:
 * - MinStack() 初始化堆栈对象。
 * - void push(int val) 将元素val推入堆栈。
 * - void pop() 删除堆栈顶部的元素。
 * - int top() 获取堆栈顶部的元素。
 * - int getMin() 获取堆栈中的最小元素。
 *
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 */


/**
 * 栈 + 优先队列
 */
// public class MinStack {
//     private Deque<Integer> stk;
//     private PriorityQueue<Integer> pq;
//
//     /** initialize your data structure here. */
//     public MinStack() {
//         stk = new LinkedList<>();
//         pq = new PriorityQueue<>();
//     }
//
//     // 向优先队列中加入元素调整顺序的时间复杂度为O(logn)
//     public void push(int x) {
//         stk.push(x);
//         pq.offer(x);
//     }
//
//     // 删除优先队列中指定元素的时间复杂度为O(n)。分析：需要O(n)时间遍历找到第一个为该值的元素，然后需要O(logn)维护堆结构
//     public void pop() {
//         pq.remove(stk.pop());
//     }
//
//     public int top() {
//         return stk.peek();
//     }
//
//     public int getMin() {
//         return pq.peek();
//     }
// }


/**
 * 普通栈 + 单调栈
 * 用 “非严格” 降序而不是 “严格” 降序的原因：
 * 在普通栈具有 重复 最小值元素时，非严格降序可防止单调栈提前弹出最小值元素
 */
// 各方法的时间复杂度均为O(1)
class MinStack {

    private Deque<Integer> stk; // 普通栈：用于存储所有元素，保证入栈 push() 函数、出栈 pop() 函数、获取栈顶 top() 函数的正常逻辑。
    private Deque<Integer> decrStk; // 非严格单调减 栈：存储普通栈中所有 非严格降序 元素的子序列，则普通栈中的最小元素始终对应单调栈的栈顶元素。

    /** initialize your data structure here. */
    public MinStack() {
        stk = new LinkedList<>();
        decrStk = new LinkedList<>();
    }

    public void push(int x) {
        stk.push(x);
        // 添加元素时维护单调栈的非严格递减性
        if(decrStk.isEmpty() || decrStk.peek() >= x) {
            decrStk.push(x);
        }
    }

    public void pop() {
        /*
        * 注意这里 不能 这样写：
        * if(decrStk.peek() == stk.pop())
        * 因为java中[-128,127]会被cache缓存，因此这个范围内的两个Integer用==比较比较的是内部value值，而不在此范围内的比较的是引用对象是否相同
        * */

        int x = stk.pop();  // 这里用int类型接收就会自动拆箱，用这个x跟下面的peek用==比较，因为自动拆箱的存在实际上比的就是数值
        // 若普通栈弹出的数在数值上等于单调栈的栈顶元素则也将单调栈的栈顶出栈
        if(x == decrStk.peek()) {
            decrStk.pop();
        }

        /*
        * 也 可以 这样写：
        * if(stk.pop().equals(decrStk.peek()))
        * 用equals比较，比的就一定是内部值了
        * */
    }

    public int top() {
        return stk.peek();
    }

    public int getMin() {
        return decrStk.peek();
    }
}


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
