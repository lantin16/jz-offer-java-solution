package statisticalResult;

/**
 * LCR 191. 按规则计算统计结果
 *
 * 为了深入了解这些生物群体的生态特征，你们进行了大量的实地观察和数据采集。数组 arrayA 记录了各个生物群体数量数据，其中 arrayA[i] 表示第 i 个生物群体的数量。
 * 请返回一个数组 arrayB，该数组为基于数组 arrayA 中的数据计算得出的结果，其中 arrayB[i] 表示将第 i 个生物群体的数量从总体中排除后的其他数量的乘积。
 */

public class Solution {

    /**
     * 前缀积 + 后缀积
     *
     * 注意：本题不能先求出所有数的乘积，再挨个除以对应的元素，因为元素可能有0，除以0是非法的，因此只能用乘法求
     * @param arrayA
     * @return
     */
    // 时间复杂度：O(n)，其中n为arrayA的长度。分析：构建前缀积、后缀积数组以及结果数组需要遍历三次长度为n的数组
    // 空间复杂度：O(n)，其中n为arrayA的长度。分析：前缀积、后缀积数组长度均为n
    public int[] statisticalResult(int[] arrayA) {
        int len = arrayA.length;
        if(len == 0) {
            return new int[0];
        }
        int[] arrayB = new int[len];    // 结果数组
        int[] preMult = new int[len];   // 前缀积数组，preMult[i]表示arrayA从下标0到i的乘积（包括i）
        int[] postMult = new int[len];  // 后缀积数组，postMult[j]表示arrayA从下标len-1到j的乘积（包括j）

        // 构建前缀积数组
        preMult[0] = arrayA[0];
        for(int i = 1; i < len; i++) {
            preMult[i] = preMult[i - 1] * arrayA[i];
        }

        // 构建后缀积数组
        postMult[len - 1] = arrayA[len - 1];
        for(int j = len - 2; j >= 0; j--) {
            postMult[j] = postMult[j + 1] * arrayA[j];
        }

        // 通过前缀积数组和后缀积数组快速求得结果
        // 两端的情况单独处理，这样可以不再for循环中写if判断边界
        arrayB[0] = postMult[1];
        arrayB[len - 1] = preMult[len - 2];
        for(int k = 1; k < len - 1; k++) {
            arrayB[k] = preMult[k - 1] * postMult[k + 1];
        }
        return arrayB;
    }
}
