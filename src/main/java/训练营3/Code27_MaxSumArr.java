package 训练营3;

/**
 * 给定一个数组arr，返回子数组的最大累加和。
 *
 * @author: Mr.diao
 * @date: 19:32 2021/5/31
 */
public class Code27_MaxSumArr {

    public static int maxSum(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int max = dp[0];

        for (int i = 1; i < arr.length; i++) {
            if (dp[i - 1] >= 0) {
                dp[i] = dp[i - 1] + arr[i];
            } else {
                dp[i] = arr[i];
            }
            max = Math.max(dp[i], max);
        }

        return max;
    }

    public static int maxSum2(int[] arr) {

        int cur = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(cur, max);
            cur = Math.max(cur, 0);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr1 = {-2, -3, -5, 40, -10, -10, 100, 1};
        System.out.println(maxSum(arr1));
        System.out.println(maxSum2(arr1));

        int[] arr2 = {-2, -3, -5, 0, 1, 2, -1};
        System.out.println(maxSum(arr2));
        System.out.println(maxSum2(arr2));

        int[] arr3 = {-2, -3, -5, -1};
        System.out.println(maxSum(arr3));
        System.out.println(maxSum2(arr3));
    }
}
