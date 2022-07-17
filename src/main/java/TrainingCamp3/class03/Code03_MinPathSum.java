package TrainingCamp3.class03;

public class Code03_MinPathSum {

    public static int minPathSum1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int[][] dp = new int[matrix.length][matrix[0].length];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; i++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[matrix.length - 1][matrix[0].length - 1];

    }

    public static int minPathSum2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int[] dp = new int[matrix[0].length];
        dp[0] = matrix[0][0];

        for (int i = 1; i < matrix[0].length; i++) {
            dp[i] = dp[0] + matrix[0][1];
        }

        for (int i = 1; i < matrix.length; i++) {
            dp[0] = dp[0] + matrix[i][0];
            for (int j = 1; j < matrix[0].length; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + matrix[i][j];
            }
        }
        return dp[dp.length - 1];
    }
}
