package basicClass.class12;

/**
 * 两个字符串的最长公共子序列问题
 */
public class Code05_PalindromeSubsequence {

    public static int dpWay(char[] c1, char[] c2) {
        int[][] dp = new int[c1.length][c2.length];
        dp[0][0] = c1[0] == c2[0] ? 1 : 0;
        for (int i = 1; i < c1.length; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], c1[i] == c2[0] ? 1 : 0);
        }

        for (int i = 1; i < c2.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], c2[i] == c1[0] ? 1 : 0);
        }

        for (int i = 1; i < c2.length; i++) {
            for (int j = 1; j < c1.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (c1[j] == c2[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i][j]);
                }
            }
        }
        return dp[c1.length - 1][c2.length - 1];
    }
}
