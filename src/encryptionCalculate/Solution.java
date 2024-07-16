package encryptionCalculate;

/**
 * LCR 190. 加密运算
 *
 * 计算机安全专家正在开发一款高度安全的加密通信软件，需要在进行数据传输时对数据进行加密和解密操作。假定 dataA 和 dataB 分别为随机抽样的两次通信的数据量：
 * - 正数为发送量
 * - 负数为接受量
 * - 0 为数据遗失
 * 请不使用四则运算符的情况下实现一个函数计算两次通信的数据量之和（三种情况均需被统计），以确保在数据传输过程中的高安全性和保密性。
 */

public class Solution {

    /**
     * 补码按位加
     * 在计算机系统中，数值一律用 补码 来表示和存储。补码的优势： 加法、减法可以统一处理（CPU只有加法器）。
     * @param dataA
     * @param dataB
     * @return
     */
    // 时间复杂度：O(1)。分析：循环指向32次
    // 空间复杂度：O(1)
    public int encryptionCalculate(int dataA, int dataB) {
        int res = 0;
        int d = 0;  // 进位标志
        for(int i = 0; i < 32; i++) {   // 从低位到高位计算结果
            int a = dataA & 1, b = dataB & 1;   // 求出A和B的当前最低位
            int k = a ^ b ^ d;  // 计算本位的结果，将A和B的对应位以及进位一起取异或
            if(((a & b) == 1) || ((a | b) == 1 && d == 1)) {    // 确定本位是否有进位（a，b，d至少有2个1时有进位）
                d = 1;
            } else {
                d = 0;
            }
            res |= (k << i);    // 将本位结果左移i位与res求异或替换加法
            dataA >>= 1;
            dataB >>= 1;
        }
        return res;
    }
}
