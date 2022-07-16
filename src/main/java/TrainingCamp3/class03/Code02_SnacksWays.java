package TrainingCamp3.class03;

public class Code02_SnacksWays {
    public static void main(String[] args) {
        int[] arr = { 4, 3, 2, 9 };
        int w = 8;
        System.out.println(way1(arr, w));
        System.out.println(dp2(arr, w));
//        System.out.println(ways3(arr, w));

    }
    public static int way1(int[] v, int restWeight) {
        return process1(v, 0, restWeight);
    }

    private static int process1(int[] v, int index, int restWeight) {
        if (restWeight < 0) {
            return 0;
        }

        if (index >= v.length) {
            return 1;
        }

        int res = process1(v, index + 1, restWeight - v[index]);
        res += process1(v, index + 1, restWeight);
        return res;
    }

    public static int dp2(int[] v, int restWeight) {
        int n = v.length;

        int[][] dp = new int[n + 1][restWeight + 1];
        for (int i = 0; i <= restWeight; i++) {
            dp[n][i] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= restWeight; j++) {
                dp[i][j] = dp[i + 1][j] + (j - v[i] >= 0 ? dp[i + 1][j - v[i]] : 0);
            }
        }
        return dp[0][restWeight];
    }
}
