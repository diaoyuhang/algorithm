package TrainingCamp3.class08;

public class Code02_MoneyWays {

    public static int moneyWays(int[] arbitrary, int[] onlyOne, int money) {

        int[][] aDP = getArbitraryDp(arbitrary, money);
        int[][] oDp = getOnlyOneDp(onlyOne, money);

        int left = 0;
        int right = money;
        int res = 0;
        while (left <= right) {
            res += (aDP[arbitrary.length - 1][left] * oDp[onlyOne.length - 1][right]);
        }

        return res;
    }

    private static int[][] getOnlyOneDp(int[] arr, int money) {
        int[][] dp = new int[arr.length][money + 1];

        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }

        dp[0][arr[0]] = 1;

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= money; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += (j - arr[i] >= 0 ? dp[i - 1][j - arr[i]] : 0);
            }
        }
        return dp;
    }

    private static int[][] getArbitraryDp(int[] arr, int money) {
        int[][] dp = new int[arr.length][money + 1];

        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; arr[0] * i <= money; i++) {
            dp[0][i * arr[0]] = 1;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= money; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }

        return dp;
    }
}
