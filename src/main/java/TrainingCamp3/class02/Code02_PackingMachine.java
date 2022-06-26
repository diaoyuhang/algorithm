package TrainingCamp3.class02;

public class Code02_PackingMachine {


    public static int MinOps(int[] arr) {
        int ans = -1;
        int sum = 0;
        for (int a : arr) {
            sum += a;
        }

        if (sum % arr.length != 0) {
            return -1;
        }

        int avg = sum / arr.length;
        int leftSum = 0;

        for (int i = 0; i < arr.length; i++) {

            int leftRest = leftSum - avg * i;
            int rightRest = sum - leftSum - arr[i] - (arr.length - i) * avg;

            if (leftRest < 0 && rightRest < 0) {
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += arr[i];
        }
        return ans;
    }
}
