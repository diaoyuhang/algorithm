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

        int right = -1; //当有回文子串的右边界，如果超过了right，就更新right值为右边界的值
        int center = -1; //当right值更新，center就更新为回文子串的中心点

        int[] pArr = new int[chars.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < chars.length; i++) {
            //1、如果下标i超过了right边界位置的，就直接赋值1，没任何优化
            //2、如果下标i在right的边界内:
            //      找到i在center回文串中的相对应的另一个点i'的位置，i'的为中心的回文串的p'半径长度已经记录在pArr数组中
            //      分3中情况计算i的回文半径长度：
            //      1、如果i'回文长度没有超过center回文的左边界，那么该长度就是i的回文长度；
            //      2、如果i'回文长度超过center回文的左边界,那么i的回文长度就是right-i的长度；
            //      3、如果i'回文长度正好在center回文的左边界，那么i的回文长度需要在i'回文长度的基础上继续计算
            pArr[i] = right > i ? Math.min(pArr[2 * center - i], right - i) : 1;

            while (i + pArr[i] < chars.length && i - pArr[i] >= 0) {
                if (chars[i + pArr[i]] == chars[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }

            if (pArr[i] + i > right) {
                right = pArr[i] + i;
                center = i;
            }
            max = Math.max(max, pArr[i]);
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
