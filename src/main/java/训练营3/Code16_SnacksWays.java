package 训练营3;

/**
 * 背包容量为w
 * 一共有n袋零食, 第i袋零食体积为v[i]
 * 总体积不超过背包容量的情况下，
 * 一共有多少种零食放法？(总体积为0也算一种放法)。
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 17:55 2021/5/9
 */
public class Code16_SnacksWays {

    public static int ways1(int[] v, int w) {
        return process(w, v, 0);
    }

    private static int process(int rest, int[] v, int index) {
        if (index >= v.length) {
            return 1;
        }
        int next1 = process(rest, v, index + 1);
        int next2 = 0;
        if (rest >= v[index]) {
            next2 = process(rest - v[index], v, index + 1);
        }
        return next1 + next2;
    }

    public static int ways2(int[] arr, int w) {
        int size = arr.length;
        int[][] dp = new int[w + 1][size + 1];
        for (int i = 0; i <= w; i++) {
            dp[i][size] = 1;
        }
        for (int rest = 0; rest <= w; rest++) {
            for (int index = size - 1; index >= 0; index--) {
                int next1 = dp[rest][index + 1];
                int next2 = 0;
                if (rest >= arr[index]) {
                    next2 = dp[rest - arr[index]][index + 1];
                }
                dp[rest][index] = next1 + next2;
            }
        }
        return dp[w][0];
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 9};
        int w = 8;
        System.out.println(ways1(arr, w));
        System.out.println(ways2(arr, w));
//        System.out.println(ways3(arr, w));

    }
}
