package basicClass.class05;

import java.util.Arrays;

public class Code03_RadixSort {

    public static void main(String[] args) {
        int[] arr = {23, 435, 234, 324, 645, 645, 645, 756, 765, 8};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    private static void radixSort(int[] arr, int left, int right, Integer maxbits) {
        int radix = 10;
        int[] help = new int[right - left + 1];

        for (int i = 1; i <= maxbits; i++) {

            int[] count = new int[radix];
            for (int j = left; j <= right; j++) {
                int num = getDigit(arr[j], i);
                count[num]++;
            }

            for (int j = 1; j < count.length; j++) {
                count[j] = count[j] + count[j - 1];
            }

            for (int j = right; j >= left; j--) {
                int num = getDigit(arr[j], i);
                help[count[num] - 1] = arr[j];
                count[num]--;
            }

            System.arraycopy(help, 0, arr, 0, help.length);
        }


    }

    private static int getDigit(int num, int index) {
        return ((num / (int) Math.pow(10, index - 1)) % 10);
    }

    public static Integer maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            max = Math.max(num, max);
        }
        int count = 0;
        while (max != 0) {
            max = max / 10;
            count++;
        }
        return count;
    }

}
