package 训练营1.manacher;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 19:14 2021/3/20
 */
public class Code01_Manacher {

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

    public static int manacher(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] chars = manacherString(str);
        int center = -1;
        int right = -1;

        int[] record = new int[chars.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < chars.length; i++) {

            record[i] = right > i ? Math.min(record[2 * center - i], right - i) : 1;

            while (i + record[i] < chars.length && i - record[i] >= 0) {
                if (chars[i + record[i]] == chars[i - record[i]]) {
                    record[i]++;
                } else {
                    break;
                }
            }

            if (record[i] + i > right) {
                right = record[i] + i;
                center = i;
            }
            max = Math.max(max, record[i]);
        }

        return max - 1;
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
