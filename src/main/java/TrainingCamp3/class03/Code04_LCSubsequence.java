package TrainingCamp3.class03;


public class Code04_LCSubsequence {

    public static String lcse(String str1, String str2) {

        int[][] dp = getDp(str1, str2);
        int m = str1.length();
        int n = str2.length();
        int len = dp[m - 1][n - 1];

        char[] res = new char[len];
        while (len >= 0) {
            if (m > 0 && dp[m][n] == dp[m - 1][n]) {
                m--;
            } else if (n > 0 && dp[m][n] == dp[m][n - 1]) {
                n--;
            } else {
                res[len--] = str1.charAt(n);
                m--;
                n--;
            }
        }

        return new String(res);
    }

    public static int[][] getDp(String str1, String str2) {
        int[][] dp = new int[str1.length()][str2.length()];
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();

        dp[0][0] = ch1[0] == ch2[0] ? 1 : 0;
        for (int i = 1; i < ch2.length; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], ch1[0] == ch2[i] ? 1 : 0);
        }

        for (int j = 1; j < ch1.length; j++) {
            dp[j][0] = Math.max(dp[j - 1][0], ch1[j] == ch2[0] ? 1 : 0);
        }

        for (int i = 1; i < ch1.length; i++) {
            for (int j = 1; j < ch2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (ch1[i] == ch2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }
}
