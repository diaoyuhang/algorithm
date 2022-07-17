package TrainingCamp3.class03;

public class Code05_LCSubstring {

    public static String lcst1(String str1, String str2) {
        int[][] dp = getDp(str1, str2);
        int end = -1;
        int max = -1;
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (dp[i][j] > max) {
                    end = j;
                    max = dp[i][j];
                }
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    public static int[][] getDp(String str1, String str2) {
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        int n = ch1.length;
        int m = ch2.length;

        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            dp[i][0] = ch1[i] == ch2[0] ? 1 : 0;
        }

        for (int i = 0; i < m; i++) {
            dp[0][i] = ch1[0] == ch2[i] ? 1 : 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (ch1[i] == ch2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                }
            }
        }
        return dp;

    }
}
