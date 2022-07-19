package TrainingCamp3.class04;

public class Code06_SubArrayMaxSum {

    public static int maxSum(int[] arr) {
        int sum = 0;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            sum = Math.max(cur, sum);
            cur = cur < 0 ? 0 : cur;
        }
        return sum;
    }
}
