package TrainingCamp3.class04;

import static TrainingCamp3.class04.Code06_SubArrayMaxSum.maxSum;

public class Code07_SubMatrixMaxSum {


    public static int getMaxSum(int[][] arr) {
        int width = arr[0].length;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int[] arr2 = new int[arr[0].length];
            for (int j = i; j < arr.length; j++) {
                for (int n = 0; n < width; n++) {
                    arr2[n] += arr[j][n];
                }
                max = Math.max(max, maxSum(arr2));
            }
        }
        return max;
    }
}
