package 训练营1.manacher;

/**
 * 假设字符串str长度为N，想返回最长回文子串的长度
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 16:33 2021/3/6
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
        int R = -1;
        int C = -1;
        //存储每个位置的回文半径
        int[] parr = new int[chars.length];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < chars.length; i++) {
            //2 * C - i 求得i关于C的对称点
            parr[i] = R > i ? Math.min(parr[2 * C - i], R - i) : 1;

            while (i + parr[i] < chars.length && i - parr[i] >= 0) {
                if (chars[i + parr[i]] == chars[i - parr[i]]) {
                    parr[i]++;
                } else {
                    break;
                }
            }

            if (i + parr[i] > R) {
                R = parr[i] + i;
                C = i;
            }
            max = Math.max(max, parr[i]);
        }
        return max - 1;
    }

    private static char[] manacherString(String str) {
        char[] chars = str.toCharArray();
        char[] res = new char[chars.length * 2 + 1];
        int index = 0;

        for (int i = 0; i < res.length; i++) {
            res[i] = i % 2 == 0 ? '#' : chars[index++];
        }

        return res;
    }

}
