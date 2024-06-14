package inventoryManagement_158;

/**
 * LCR 158. 库存管理 II
 *
 * 仓库管理员以数组 stock 形式记录商品库存表。stock[i] 表示商品 id，可能存在重复。请返回库存表中数量大于 stock.length / 2 的商品 id。
 */

public class Solution {

    /**
     * 摩尔投票法，找众数
     * @param stock
     * @return
     */
    public int inventoryManagement(int[] stock) {
        int n = stock.length;
        int can = stock[0]; // 候选商品id
        int vote = 1;   // 候选人的票数
        for(int i = 1; i < n; i++) {
            if(stock[i] == can) {   // 与候选人相同，票数+1
                vote++;
            } else {    // 否则票数-1
                vote--;
            }
            if(vote == 0) { // 说明候选商品不是这个区间的众数，退选
                can = stock[++i];   // 下一个商品参选
                vote = 1;   // 重置票数
            }
        }
        return can;
    }
}
