package TrainingCamp3.class06;

public class Code06_StringCross {

    public static boolean isCross1(String str1, String str2, String aim) {

        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        char[] aimCh = aim.toCharArray();

        boolean[][] dp = new boolean[ch1.length + 1][ch2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= ch1.length; i++) {
            if (ch1[i - 1] != aimCh[i]) {
                break;
            }
            dp[i][0] = true;
        }

        for (int i = 1; i <= ch2.length; i++) {
            if (ch2[i - 1] != aimCh[i]) {
                break;
            }
            dp[0][i] = true;
        }

        for (int i = 1; i <= ch1.length; i++) {
            for (int j = 1; j <= ch2.length; j++) {
                if (ch1[i - 1] == aimCh[i + j - 1] && dp[i - 1][j]
                        || ch2[j - 1] == aimCh[i + j - 1] && dp[i][j - 1]
                ) {
                    dp[i][j] = true;
                }


            }
        }
        return dp[ch1.length][ch2.length];
    }
}
