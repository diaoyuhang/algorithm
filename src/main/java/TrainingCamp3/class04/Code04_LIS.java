package TrainingCamp3.class04;

public class Code04_LIS {

    public static int[] lis(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getDp(arr);
        return generateLIS(arr, dp);
    }

    private static int[] getDp(int[] arr) {

        int[] dp = new int[arr.length];
        int[] end = new int[arr.length];
        end[0] = arr[0];
        dp[0] = 1;
        int right = 0;

        int l = 0;
        int r = 0;
        int m = 0;

        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;

            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > end[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }

            right = Math.max(right, l);
            end[l] = arr[i];
            dp[i] = l + 1;
        }

        return dp;
    }

    private static int[] generateLIS(int[] arr, int[] dp) {
        int len = 0;
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > len) {
                len = dp[i];
                index = i;
            }
        }
        int[] lis = new int[len];
        lis[--len] = arr[index];
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                lis[--len] = arr[i];
                index = i;
            }
        }
        return lis;
    }

}
