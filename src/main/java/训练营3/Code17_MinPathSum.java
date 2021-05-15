package 训练营3;

/**
 * 给定一个二维数组matrix，其中每个数都是正数，要求从左上到右下
 * 每一步只能向右或者向下，沿途经过的数字要累加起来
 * 最后请返回最小的路径和
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 15:38 2021/5/15
 */
public class Code17_MinPathSum {
    public static int minPath3(int[][] m) {
        int[] dp = new int[m[0].length];
        int length = m.length;
        int width = m[0].length;

        dp[0] = m[0][0];
        for (int i = 1; i < width; i++) {
            dp[i] = dp[i - 1] + m[0][i];
        }

        for (int i = 1; i < length; i++) {
            dp[0] = dp[0] + m[i][0];
            for (int j = 1; j < width; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
            }
        }
        return dp[width - 1];
    }
}
