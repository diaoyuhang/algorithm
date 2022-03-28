package basicClass.class11;

/**
 * @author: diaoyuhang
 * @date 2022/3/28
 * 背包问题
 */
public class Code07_Knapsack {

    public static int knapsack(int[] weight, int[] value, int bag) {
        if (bag <= 0) {
            return 0;
        }
        return process(weight, value, bag, 0);
    }

    private static int process(int[] weight, int[] value, int res, int index) {
        if (res <= 0) {
            return 0;
        }
        if (index == weight.length) {
            return 0;
        }
        int p1 = Integer.MIN_VALUE;
        if (res >= weight[index]) {
            p1 = value[index] + process(weight, value, res - weight[index], index + 1);
        }
        int p2 = process(weight, value, res, index + 1);
        return Math.max(p1, p2);
    }

    public static int dpWay(int[] weight, int[] value, int rest) {
        if (rest <= 0) {
            return 0;
        }

        int[][] dp = new int[rest + 1][weight.length + 1];
        for (int i = 0; i <= weight.length; i++) {
            dp[0][i] = 0;
        }

        for (int i = 0; i < rest + 1; i++) {
            dp[i][weight.length] = 0;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = weight.length - 1; j >= 0; j--) {

                int p1 = Integer.MIN_VALUE;
                if (i >= weight[j]) {
                    p1 = value[j] + dp[i - weight[j]][j + 1];
                }
                int p2 = dp[i][j + 1];
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[rest][0];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(dpWay(weights, values, bag));
        System.out.println(knapsack(weights, values, bag));
    }
}
