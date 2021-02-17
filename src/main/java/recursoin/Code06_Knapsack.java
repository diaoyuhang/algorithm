package recursoin;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 9:27 2021/2/17
 */
public class Code06_Knapsack {

    public static int getMaxValue(int[] weight, int[] value, int bag) {
        return process(weight, value, 0, 0, bag);
    }

    private static int process(int[] weight, int[] value, int index, int alreadyW, int bag) {
        if (alreadyW > bag) {
            return -1;
        }
        if (index == weight.length) {
            return 0;
        }
        //不选择当前这个
        int v1 = process(weight, value, index + 1, alreadyW, bag);
        //选择当前这个
        int v2 = process(weight, value, index + 1, alreadyW + weight[index], bag);

        if (v2 != -1) {
            v2 = v2 + value[index];
        }
        return Math.max(v1, v2);
    }

    public static int dpWay(int[] weight, int[] value, int bag) {
        int N = weight.length;
        int[][] dp = new int[N + 1][bag + 1];

        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 1; rest <= bag; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (rest >= weight[i]) {
                    //每件是只能装一个，所以剩余的空间只能从下一行选取最大值
                    dp[i][rest] = Math.max(dp[i][rest], value[i] + dp[i + 1][rest - weight[i]]);
                }
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(getMaxValue(weights, values, bag));
        System.out.println(dpWay(weights, values, bag));
    }
}
