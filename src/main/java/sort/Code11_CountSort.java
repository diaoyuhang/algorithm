package sort;

import java.util.Arrays;

/**
 * @author: Mr.diao
 * @date: 9:27 2020/12/28
 */
public class Code11_CountSort {
    public static void main(String[] args) {
        int[] arr = {5, 6, 2, 4, 98, 232, 2, 3, 2, 7};
        countSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int max = Integer.MIN_VALUE;
        //得到给定数组中最大的一个值
        for (int value : arr) {
            max = Math.max(max, value);
        }
        int[] help = new int[max + 1];
        for (int value : arr) {
            help[value]++;
        }
        int index = 0;
        for (int i = 0; i < help.length; i++) {
            while (help[i] > 0) {
                arr[index++] = i;
                help[i]--;
            }
        }
    }
}
