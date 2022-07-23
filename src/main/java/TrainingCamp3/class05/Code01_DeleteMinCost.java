package TrainingCamp3.class05;

public class Code01_DeleteMinCost {

    public static void main(String[] args) {
        System.out.println(minCost("cad1234", "abcdefghijk"));
    }

    //str1最少删除几个字符可以成为str2的子串
    public static int minCost(String str1, String str2) {
        char[] ch1 = str1.toCharArray();
        int min = Integer.MAX_VALUE;
        for (int start = 0; start < str2.length(); start++) {
            for (int end = start + 1; end <= str2.length(); end++) {
                min = Math.min(min, distance(ch1, str2.substring(start, end).toCharArray()));
            }
        }

        return min;
    }

    private static int distance(char[] ch1, char[] ch2) {
        int row = ch1.length;
        int col = ch2.length;
        int[][] dp = new int[row][col];
        dp[0][0] = ch1[0] == ch2[0] ? 0 : Integer.MAX_VALUE;

        for (int i = 1; i < col; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = (dp[i - 1][0] != Integer.MAX_VALUE || ch1[i] == ch2[0]) ? i : Integer.MAX_VALUE;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }

                if (ch1[i] == ch2[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i][j]);
                }

            }
        }
        return dp[row - 1][col - 1];
    }

}
