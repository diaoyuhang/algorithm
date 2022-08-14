package TrainingCamp3.class07;

import java.util.Arrays;

public class Code02_SmallestUnFormedSum {


    //类似使用背包问题
    public static int unformedSum2(int[] arr) {
        int sum = 0;
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            min = Math.min(min, arr[i]);
        }
        boolean[][] dp = new boolean[arr.length][sum + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= sum; j++) {
                //从i位置开始之前的所有数，能不能组成当前j位置的和
                dp[i][j] = dp[i - 1][j] || (j - arr[i] >= 0) ? dp[i - 1][j - arr[i]] : false;
            }
        }

        for (int i = min; i <= sum; i++) {
            if (!dp[arr.length - 1][i]) {
                return i;
            }
        }
        return sum + 1;
    }

    //已知一定存在1
    public static int unformedSum3(int[] arr) {

        Arrays.toString(arr);
        int range = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > range) {
                return range + 1;
            } else {
                range += arr[i];
            }
        }
        return range + 1;
    }
}
