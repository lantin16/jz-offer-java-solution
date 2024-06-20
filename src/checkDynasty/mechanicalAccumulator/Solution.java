package checkDynasty.mechanicalAccumulator;

/**
 * LCR 189. 设计机械累加器
 *
 * 请设计一个机械累加器，计算从 1、2... 一直累加到目标数值 target 的总和。
 * 注意这是一个只能进行加法操作的程序，不具备乘除、if-else、switch-case、for 循环、while 循环，及条件判断语句等高级功能。
 */


/**
 * 求累加数的和有三种方法：
 * 1. 等差数列求和公式。需要用到乘除，排除
 * 2. 迭代。需要用到for循环，排除
 * 3. 递归。在递归中止条件处需要用到if判断，能否用其他方法中止递归呢？——
 */
public class Solution {


    /**
     * 递归 + 逻辑运算符的短路效应
     * @param target
     * @return
     */
    // 时间复杂度：O(target)。分析：需要开启target次递归函数，每次累加一个数
    // 时间复杂度：O(target)。分析：递归深度达到 target
    public int mechanicalAccumulator(int target) {
        // if(target == 1) {
        //     return 1;
        // }
        // return target + mechanicalAccumulator(target - 1);   // 没有想到直接改写target达到两个分支都返回target的效果

        // x没有实际意义，只是为了构成一个语句，否则会报错
        // 如果递归中止（target=1），则前半部分就为false了，不会执行后半部分，也就不会开启递归
        // 如果target > 1，才会执行后半部分，开启递归函数并加到target上，> 0也没有实际意义，只是为了使后半部分是布尔表达式，防止报错
        boolean x = target > 1 && (target += mechanicalAccumulator(target - 1)) > 0;
        return target;  // 如果传入的target是1，则上面不会走到递归，这里返回去的也是1
    }
}
