package dynamicprogram;

/**
 * 背包动态规划
 *
 * @author: Mr.diao
 * @date: 13:37 2021/2/22
 */
public class Code03_Knapsack {

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        System.out.println(getMaxValue(weights, values, 11));
        System.out.println(dpWays(weights, values, 11));
    }

    public static int getMaxValue(int[] weights, int[] values, int bag) {
        return process(weights, values, 0, 0, bag);
    }

    private static int process(int[] weights, int[] values, int index, int alreadyWeight, int bag) {
        //如果当前重量超过了bag的容量，则当前选择的方案不行
        if (alreadyWeight > bag) {
            return -1;
        }

        if (index == weights.length) {
            return 0;
        }
        int ans1 = process(weights, values, index + 1, alreadyWeight, bag);
        int ans2 = process(weights, values, index + 1, alreadyWeight + weights[index], bag);

        if (ans2 != -1) {
            ans2 = values[index] + ans2;
        }

        return Math.max(ans1, ans2);
    }

    public static int dpWays(int[] weights, int[] values, int bag) {
        int length = weights.length;

        int[][] dp = new int[length + 1][bag + 1];
        for (int index = length - 1; index >= 0; index--) {
            for (int rest = 1; rest <= bag; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest > weights[index]) {
                    dp[index][rest] = Math.max(dp[index][rest], values[index] + dp[index + 1][rest - weights[index]]);
                }
            }
        }
        return dp[0][bag];
    }
}
