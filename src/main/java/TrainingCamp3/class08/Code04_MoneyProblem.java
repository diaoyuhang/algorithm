package TrainingCamp3.class08;

public class Code04_MoneyProblem {

    // int[] d d[i]：i号怪兽的武力
    // int[] p p[i]：i号怪兽要求的钱
    public static long func1(int[] d, int[] p) {
        return process1(d, p, 0, 0);
    }

    private static long process1(int[] d, int[] p, int power, int index) {
        if (index == d.length) {
            return 0;
        }
        if (d[index] > power) {
            return p[index] + process1(d, p, power + d[index], index + 1);
        } else {
            long result1 = process1(d, p, power, index + 1);
            long result2 = process1(d, p, power + d[index], index + 1);
            return result1 > result2 ? result1 : result2;
        }
    }

    public static long dp(int[] d, int[] p) {
        int sum = 0;
        for (int num : p) {
            sum += num;
        }

        int[][] dp = new int[d.length][sum + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][p[0]] = d[0];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j <= sum; j++) {

                if (j >= p[i] && dp[i - 1][j - p[i]] != -1) {
                    dp[i][j] = dp[i - 1][j - p[i]] + d[i];
                }

                if (dp[i - 1][j] >= d[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= sum; i++) {
            if (dp[dp.length - 1][i] != -1) {
                return i;
            }
        }
        return ans;
    }
}

