package 训练营1.kmp;

/**
 * 假设字符串str长度为N，字符串match长度为M，M <= N
 * 想确定str中是否有某个子串是等于match的。
 * 时间复杂度O(N)
 *
 * @author: Mr.diao
 * @date: 14:17 2021/3/1
 */
public class Code01_KMP {
    public static void main(String[] args) {

    }
    /**
     *  kmp加速原理：
     *  S: [abcde...abcd]x...
     *  M: [abcde...abcd]y...
     *  假设当S来到x字符，M来到y字符，这两个不匹配，
     *  S不动，M就会根据next数组的下一跳来到e字符开始和x字符进行匹配；
     *  1、这个就直接省去abcd的比较
     *  2、确定S的之前的字符不可能匹配出完整的字符串
     */
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }

        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int sIndex = 0;
        int mIndex = 0;
        int[] nextArray = getNextArray(m);
        while (sIndex < s.length() && mIndex < m.length()) {
            if (str[sIndex] == match[mIndex]) {
                sIndex++;
                mIndex++;
            } else if (nextArray[mIndex] == -1) {//如果match的第一个字符就不匹配，则sIndex指向下一个
                sIndex++;
            } else {
                mIndex = nextArray[mIndex];
            }
        }

        return mIndex == m.length() ? sIndex - mIndex : -1;
    }
    /**
     * 获取下一跳数字的本质就是，遍历每个字符，计算该字符之前的字符串，前缀等于后缀最大值（不包含整个字符串），
     * 第一个字符默认-1，第二个字符默认0
     */
    //a     b   b   a   b   b   c
    //-1    0   0   0   1   2   3
    //获得match字符串的下一跳数组
    public static int[] getNextArray(String m) {
        if (m.length() == 1) {
            return new int[]{-1};
        }
        char[] match = m.toCharArray();
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cur = 0;
        while (i < m.length()) {
            if (match[cur] == match[i - 1]) {
                next[i++] = ++cur;
            } else if (cur > 0) { //如果当前匹配的字符不一样，并且当前的cur
                cur = next[cur];
            } else {
                next[i++] = 0;
            }
        }

        return next;
    }
}
