package 训练营3;

/**
 * 最长递增子序列问题的O(N*logN)的解法
 *
 * @author: Mr.diao
 * @date: 17:41 2021/5/24
 */
public class Code25_LIS {

    public static int[] list1(int[] arr) {

        int[] dp = getDp1(arr);

        return generateLIS(arr, dp);
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
        for (int i = index; i > 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                lis[--len] = arr[i];
                index = i;
            }

        }
        return lis;
    }

    private static int[] getDp1(int[] arr) {
        int[] dp = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 2, 1, 5, 3, 6, 4, 8, 9, 7 };
        printArray(arr);
        printArray(list1(arr));
//        printArray(lis2(arr));

    }
}
