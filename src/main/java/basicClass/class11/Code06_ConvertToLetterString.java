package basicClass.class11;

/**
 * @author: diaoyuhang
 * @date 2022/3/28
 */
public class Code06_ConvertToLetterString {
    public static void main(String[] args) {
        System.out.println(convertToLetterString("34121432543254123412342543545645657567"));
            System.out.println(dpWay("34121432543254123412342543545645657567"));
    }

    public static int convertToLetterString(String num) {
        if (num == null || num.length() == 0) {
            return 0;
        }
        return process(num.toCharArray(), 0);
    }

    private static int process(char[] toCharArray, int index) {
        if (index == toCharArray.length) {
            return 1;
        }
        if (toCharArray[index] == '0') {
            return 0;
        }
        int res = 0;

        if (toCharArray[index] == '1' && index + 1 < toCharArray.length) {
            res += process(toCharArray, index + 2);
        }
        if (toCharArray[index] == '2' && index + 1 < toCharArray.length && toCharArray[index] < '6') {
            res += process(toCharArray, index + 2);
        }
        res += process(toCharArray, index + 1);

        return res;
    }

    public static int dpWay(String num) {
        if (num == null || num.length() == 0) {
            return 0;
        }
        char[] charArray = num.toCharArray();
        int len = charArray.length;
        int[] dp = new int[len + 1];
        dp[len] = 1;
        for (int i = len - 1; i >= 0; i--) {
            if (charArray[i] == '0') {
                dp[i] = 0;
            }
            int res = 0;
            if (charArray[i] == '1' && i + 1 < charArray.length) {
                res += dp[i + 2];
            }

            if (charArray[i] == '2' && i + 1 < charArray.length && charArray[i] < '6') {
                res += dp[i + 2];
            }
            dp[i] = (res += dp[i + 1]);
        }
        return dp[0];
    }
}
