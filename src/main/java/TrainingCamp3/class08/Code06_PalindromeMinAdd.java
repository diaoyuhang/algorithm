package TrainingCamp3.class08;

import java.util.Arrays;
import java.util.HashMap;

public class Code06_PalindromeMinAdd {

    private static int dp(String content) {
        //范围上的尝试模型
        int[][] dp = new int[content.length()][content.length()];
        char[] chars = content.toCharArray();
        for (int r = 1; r < content.length(); r++) {
            dp[r - 1][r] = chars[r - 1] == chars[r] ? 0 : 1;

            for (int l = r - 2; l >= 0; l--) {
                dp[l][r] = Math.min(dp[l + 1][r], dp[l][r - 1]) + 1;
                if (chars[l] == chars[r]) {
                    dp[l][r] = Math.min(dp[l][r], dp[l + 1][r - 1]);
                }
            }
        }
        return dp[0][content.length() - 1];
    }
}
