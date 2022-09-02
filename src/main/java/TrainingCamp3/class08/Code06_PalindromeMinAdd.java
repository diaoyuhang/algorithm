package TrainingCamp3.class08;

import java.util.Arrays;
import java.util.HashMap;

public class Code06_PalindromeMinAdd {


    public static int getLen2(int[] arr) {
        int res = 0;
        int left = 0;
        int right = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int max = arr[0];
        int min = arr[0];

        while (right < arr.length) {

            while (right < arr.length) {
                if (hashMap.containsKey(arr[right])) {
                    break;
                } else {
                    hashMap.put(arr[right], 1);
                    max = Math.max(max, arr[right]);
                    min = Math.min(min, arr[right]);

                    if (max - min + 1 == hashMap.size()) {
                        res = Math.max(res, max - min + 1);
                    }
                    right++;
                }
            }
            hashMap.remove(arr[left]);
            left++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {5, 5, 3, 2, 6, 4, 3};
        System.out.println(getLen2(arr));

    }

}
