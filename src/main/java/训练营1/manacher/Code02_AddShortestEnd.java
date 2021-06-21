package 训练营1.manacher;

/**
 * 在字符串最后加上最少的字符，使整个字符串编程回文
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 20:23 2021/3/20
 */
public class Code02_AddShortestEnd {
    public static void main(String[] args) {
        String str1 = "1aab";
        System.out.println(manacher(str1));
    }

    public static String manacher(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char[] chars = manacherString(str);
        int r = -1;
        int c = -1;
        int rLimit = chars.length - 1;
        int[] record = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            record[i] = r > i ? Math.min(record[c * 2 - i], r - i) : 1;
            if (i == rLimit) {
                return process(i - record[i], chars);
            }
            while (i + record[i] < chars.length && i - record[i] >= 0) {
                if (chars[i + record[i]] == chars[i - record[i]]) {
                    if (i + record[i] == rLimit) {
                        return process(i - record[i] - 1, chars);
                    }
                    record[i]++;
                } else {
                    break;
                }
            }

            if (i + record[i] > r) {
                r = i + record[i];
                c = i;
            }
        }
        return "";
    }

    public static String process(int index, char[] chars) {
        StringBuilder builder = new StringBuilder();
        for (; index >= 0; index--) {
            if (chars[index] != '#') {
                builder.append(chars[index]);
            }
        }
        return builder.toString();
    }

    public static char[] manacherString(String str) {
        char[] chars = str.toCharArray();
        char[] result = new char[chars.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            result[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return result;
    }
}
