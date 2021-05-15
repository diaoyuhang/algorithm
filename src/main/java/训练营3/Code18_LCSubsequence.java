package 训练营3;

/**
 * 请注意区分子串和子序列的不同，给定两个字符串str1和str2，
 * 求两个字符的最长公共子序列
 * <p>
 * 动态规划的空间压缩技巧
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 16:28 2021/5/15
 */
public class Code18_LCSubsequence {
    public static void main(String[] args) {
        System.out.println(publicSubsequence("ab12345cob", "123qef45o"));
        System.out.println(publicSubsequence2("ab12345cob", "123qef45o"));
    }

    public static int publicSubsequence(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        return process(chars1, chars2, 0, 0);
    }

    private static int process(char[] chars1, char[] chars2, int index1, int index2) {
        if (index1 == chars1.length || index2 == chars2.length) {
            return 0;
        }
        int res = 0;
        if (chars1[index1] == chars2[index2]) {
            res = Math.max(res, process(chars1, chars2, index1 + 1, index2 + 1) + 1);
        }
        res = Math.max(res, process(chars1, chars2, index1 + 1, index2));
        res = Math.max(res, process(chars1, chars2, index1, index2 + 1));


        return res;
    }

    public static int publicSubsequence2(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int n1 = chars1.length;
        int n2 = chars2.length;

        int[][] dp = new int[n1][n2];
        dp[n1 - 1][n2 - 1] = chars1[n1 - 1] == chars2[n2 - 1] ? 1 : 0;

        for (int i = n1 - 2; i >= 0; i--) {
            dp[i][n2 - 1] = chars1[i] == chars2[n2 - 1] ? 1 : dp[i + 1][n2 - 1];
        }
        for (int i = n2 - 2; i >= 0; i--) {
            dp[n1 - 1][i] = chars1[n1 - 1] == chars2[i] ? 1 : dp[n1 - 1][i + 1];
        }

        for (int i = n1 - 2; i >= 0; i--) {
            for (int j = n2 - 2; j >= 0; j--) {
                if (chars1[i] == chars2[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                }
                dp[i][j] = Math.max(dp[i][j], dp[i + 1][j]);
                dp[i][j] = Math.max(dp[i][j], dp[i][j + 1]);
            }
        }

        return dp[0][0];
    }
}
