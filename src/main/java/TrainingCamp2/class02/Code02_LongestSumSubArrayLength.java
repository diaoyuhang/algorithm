package TrainingCamp2.class02;

import java.util.HashMap;
import java.util.Map;

public class Code02_LongestSumSubArrayLength {

    public static int getMaxLength(int[] arr, int k) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1);

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                max = Math.max(max, i - map.get(sum - k));
            }
            if (!map.containsKey(sum - k)) {
                map.put(sum - k, i);
            }
        }
        return max;
    }
}
