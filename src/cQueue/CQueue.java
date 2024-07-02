package cQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LCR 125. 图书整理 II
 *
 * 读者来到图书馆排队借还书，图书管理员使用两个书车来完成整理借还书的任务。书车中的书从下往上叠加存放，图书管理员每次只能拿取书车顶部的书。排队的读者会有两种操作：
 * - push(bookID)：把借阅的书籍还到图书馆。
 * - pop()：从图书馆中借出书籍。
 *
 * 为了保持图书的顺序，图书管理员每次取出供读者借阅的书籍是 最早 归还到图书馆的书籍。你需要返回 每次读者借出书的值 。
 * 如果没有归还的书可以取出，返回 -1 。
 */


/**
 * 两个栈实现队列
 */
public class CQueue {

    Deque<Integer> getCart;    // 还书车，读者还的书就放入此栈
    Deque<Integer> giveCart;   // 借书车，读者准备借出去的书就放入此栈

    public CQueue() {
        getCart = new LinkedList<>();
        giveCart = new LinkedList<>();
    }

    /**
     * 把借阅的书籍还到图书馆
     * @param value
     */
    public void appendTail(int value) {
        getCart.push(value);    // 直接放入还书车的栈
    }

    /**
     * 从图书馆中借出书籍
     * @return
     */
    public int deleteHead() {
        // 如果借书车里还有书可借则直接弹出
        if(!giveCart.isEmpty()) {
            return giveCart.pop();
        }

        // 如果借书车为空，则先将还书车里面的书放入借书车
        while(!getCart.isEmpty()) {
            giveCart.push(getCart.pop());
        }

        // 如果借书车还为空，代表还书车之前也没有书，返回-1
        if(giveCart.isEmpty()) {
            return -1;
        }
        // 否则依旧从借书车中弹出
        return giveCart.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
