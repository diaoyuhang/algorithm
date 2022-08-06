package TrainingCamp3.class06;

import java.util.HashMap;

public class Code05_Split4Parts {

    public static boolean canSplits2(int[] arr) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            hash.put(sum, i);
        }
        int lsum = arr[0];

        for (int i = 1; i < arr.length; i++) {//第一刀切的位置
            int checkSum = lsum * 2 + arr[i];
            if (hash.containsKey(checkSum)) {
                Integer nextIndex = hash.get(checkSum) + 1;
                if (nextIndex >= arr.length) {
                    return false;
                }

                checkSum += lsum + arr[nextIndex];
                if (hash.containsKey(checkSum)) {
                    nextIndex = hash.get(checkSum) + 1;
                    if (nextIndex < arr.length && sum - checkSum - arr[nextIndex] == lsum) {
                        return true;
                    }
                }

            }
            lsum += arr[i];
        }

        return false;
    }
}
