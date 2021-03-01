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
