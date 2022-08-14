package TrainingCamp3.class07;

public class Code01_MinLengthForSort {

    public static int getMinLength(int[] arr) {
        int min = arr[arr.length - 1];
        int minIndex = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] > min) {
                minIndex = i;
            } else {
                min = arr[i];
            }
        }

        if (minIndex == -1) {
            return 0;
        }

        int max = arr[0];
        int maxIndex = -1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < max) {
                maxIndex = i;
            } else {
                max = arr[i];
            }
        }
        return maxIndex - minIndex + 1;
    }
}
