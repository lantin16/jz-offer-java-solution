package pathEncryption;

/**
 * LCR 122. 路径加密
 *
 * 假定一段路径记作字符串 path，其中以 "." 作为分隔符。现需将路径加密，加密方法为将 path 中的分隔符替换为空格 " "，请返回加密后的字符串。
 */

public class Solution {

    /**
     * 调API
     * - replace只能替换单个字符或直接匹配的字符串序列。它不支持正则表达式。
     * - replaceAll使用正则表达式进行匹配，因此可以执行更复杂的替换操作，比如替换多个字符、替换符合特定模式的字符串等。
     * @param path
     * @return
     */
    // public String pathEncryption(String path) {
    //     return path.replace(".", " ");
    // }


    /**
     * 手动遍历替换
     * @param path
     * @return
     */
    // 时间复杂度：O(n)，其中n为path的长度。
    // 空间复杂度：O(n)。分析：需要借助StringBuilder
    public String pathEncryption(String path) {
        StringBuilder sb = new StringBuilder(path.length());
        for(char c : path.toCharArray()) {
            if(c == '.') {
                sb.append(" ");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
