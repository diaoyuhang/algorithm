package dynamicprogram;

/**
 * 两个字符串的公共子序列
 *
 * @author: Mr.diao
 * @date: 17:21 2021/2/22
 */
public class Code05_PublicSubsequence {
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence2("ab12345cob", "123qef45"));
    }

    public static int longestCommonSubsequence2(String str1, String str2) {
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();

        int[][] dp = new int[char1.length][char2.length];
        dp[0][0] = char1[0] == char2[0] ? 1 : 0;
        //第一列的所有值
        for (int i = 1; i < char1.length; i++) {
            dp[i][0] = char1[i] == char2[0] ? 1 : dp[i - 1][0];
        }
        //第一行的所有值
        for (int j = 1; j < char2.length; j++) {
            dp[0][j] = char1[0] == char2[j] ? 1 : dp[0][j - 1];
        }

        for (int i = 1; i < char1.length; i++) {
            for (int j = 1; j < char2.length; j++) {
                //比如 i->4 "a123b4",j->b "a1234b";最长公共子序列为1234，当前i的值不等于j的值 依赖于dp[i][j-1]
                //比如 i->b "a1234b",j->4 "a123b4";最长公共子序列为1234，当前i的值不等于j的值 依赖于dp[i-1][j]
                //根据上面两个情况，取两种的最大值
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

                //i->4 "a123b4",j->4 "a123dc4" 这时候最长公共子序列为1234，当前i的值等于j的值 依赖于dp[i-1][j-1]+1
                if (char1[i] == char2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[char1.length - 1][char2.length - 1];
    }
}
