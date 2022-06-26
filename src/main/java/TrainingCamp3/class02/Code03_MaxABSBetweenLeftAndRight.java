package TrainingCamp3.class02;

public class Code03_MaxABSBetweenLeftAndRight {

    public static int maxABS(int arr[]) {
        int max = Integer.MIN_VALUE;

        for (int a : arr) {
            max = Math.max(max, a);
        }

        return max - Math.min(arr[0], arr[arr.length - 1]);
    }
}
