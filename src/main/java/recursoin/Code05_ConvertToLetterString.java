package recursoin;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 20:11 2021/2/16
 */
public class Code05_ConvertToLetterString {

    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        return process(chars, 0);
    }

    private static int process(char[] chars, int i) {
        if (i >= chars.length) {
            return 1;
        }
        if (chars[i] == '0') {
            return 0;
        }

        if (chars[i] == '2') {
            int sum = process(chars, i + 1);
            if (i + 1 < chars.length && Integer.valueOf(chars[i + 1] + "") <= 6) {
                sum += process(chars, i + 2);
            }
            return sum;
        }
        if (chars[i] == '1') {
            int sum = process(chars, i + 1);
            if (i + 1 < chars.length) {
                sum += process(chars, i + 2);
            }
            return sum;
        }
        return process(chars, i + 1);
    }

    public static int dpWays(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int length = chars.length;
        int[] dp = new int[length + 1];
        dp[length] = 1;

        for (int i = length - 1; i >= 0; i--) {
            if (chars[i] == '0') {
                dp[i] = 0;
            }

            if (chars[i] == '2') {
                dp[i] = dp[i + 1];
                if (i + 1 < chars.length && Integer.valueOf(chars[i + 1] + "") <= 6) {
                    dp[i] += dp[i + 2];
                }
            }
            if (chars[i] == '1') {
                dp[i] = dp[i + 1];
                if (i + 1 < chars.length) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        System.out.println(number("121"));
        System.out.println(dpWays("121"));
    }

}
