package sort;

import java.util.Arrays;

/**
 * @author: Mr.diao
 * @date: 11:21 2020/12/28
 */
public class Code12_RadixSort {

    public static void main(String[] args) {
        int[] arr = {2, 1, 100, 87, 12, 2, 3};
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
        System.out.println(Arrays.toString(arr));
    }

    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int value : arr) {
            max = Math.max(max, value);
        }
        int result = 0;
        while (max != 0) {
            result++;
            max /= 10;
        }
        return result;
    }

    //获取指定位上的数字
    public static int getDigit(int num, int digit) {

        return (num / ((int) Math.pow(10, digit - 1))) % 10;
    }

    public static void radixSort(int[] arr, int l, int r, int digits) {
        int[] help = new int[r - l + 1];

        for (int n = 1; n <= digits; n++) {
            int[] count = new int[10];

            for (int value : arr) {
                int digit = getDigit(value, n);
                count[digit]++;
            }

            for (int i = 1; i < count.length; i++) {
                count[i] = count[i] + count[i - 1];
            }
            //从右往左遍历
            for (int i = r; i >= l; i--) {
                int digit = getDigit(arr[i], n);
                help[count[digit] - 1] = arr[i];
                count[digit]--;
            }
            System.out.println();
            for (int i = l; i <= r; i++) {
                arr[i] = help[i];
            }
        }
    }
}
