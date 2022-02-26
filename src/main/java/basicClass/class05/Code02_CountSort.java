package basicClass.class05;

import java.util.Arrays;

public class Code02_CountSort {
    public static void main(String[] args) {
        int[] arr = {23, 435, 234, 324, 645, 645, 645, 756, 765, 8};
        countSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int max = Integer.MIN_VALUE;
        for (int n : arr) {
            max = Math.max(max, n);
        }

        int[] help = new int[max + 1];
        for (int n : arr) {
            help[n]++;
        }
        int index = 0;
        for (int i = 0; i < help.length; i++) {

            while (help[i]-- > 0) {
                arr[index++] = i;
            }
        }
    }
}
