package TrainingCamp3.class05;

public class Code02_EditCost {

    public static int minCost(String str1, String str2, int ic, int dc, int rc) {
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();

        int[][] dp = new int[ch1.length + 1][ch2.length + 1];
        for (int i = 1; i <= ch2.length; i++) {
            dp[0][i] = i * ic;

        }
        for (int i = 1; i < ch1.length; i++) {
            dp[i][0] = i * dc;
        }

        for (int i = 1; i <= ch1.length; i++) {
            for (int j = 1; j <= ch2.length; j++) {
                if (ch1[i] == ch2[i]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }

                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
            }
        }
        return dp[ch1.length][ch2.length];
    }
}
