package TrainingCamp2.class02;

public class Code01_LongestSumSubArrayLengthInPositiveArray {

    public int getMaxLength(int[] arr, int k) {
        int max = 0;
        int l = 0;
        int r = 0;
        int sum = arr[0];

        while (r < arr.length) {
            if (sum == k) {
                max = Math.max(max, r - l + 1);
                sum -= arr[l++];
            } else if (sum < k) {
                r++;
                if (r == arr.length) {
                    break;
                }
                sum += arr[r];
            } else {
                sum -= arr[l++];
            }
        }
        return max;
    }
}
