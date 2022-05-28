package TrainingCamp2.class02;

public class Code03_LongestLessSumSubArrayLength {


    public static int getMaxLength(int[] arr, int k) {
        int[] minSum = new int[arr.length];
        int[] minSumEnd = new int[arr.length];

        minSum[arr.length - 1] = arr[arr.length - 1];
        minSumEnd[arr.length - 1] = arr.length - 1;

        for (int i = arr.length - 2; i >= 0; i++) {

            if (minSum[i + 1] <= 0) {
                minSum[i] = arr[i] + minSum[i + 1];
                minSumEnd[i] = minSumEnd[i + 1];
            } else {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }

        int sum = 0;
        int end = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {

            while (end < arr.length && sum + minSum[end] <= k) {
                sum += minSum[end];
                end = minSumEnd[end] + 1;
            }

            max = Math.max(max, end - i);
            if (end > i) {
                sum -= arr[i];
            } else {
                end = i + 1;
            }
        }
        return max;
    }
}
