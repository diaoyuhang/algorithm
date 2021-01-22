package sort;

import java.util.Arrays;

/**
 * @Author: Mr.diao
 * @Description: 归并排序
 * @Date: 9:16 2020/12/21
 */
public class Code05_MergeSort {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        process(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        //取中间点
        int mid = l + ((r - l) >> 1);

        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int index = 0;
        int h1 = l;
        int h2 = mid + 1;

        while (h1 <= mid && h2 <= r) {
            help[index++] = arr[h1] < arr[h2] ? arr[h1++] : arr[h2++];
        }
        while (h1 <= mid) {
            help[index++] = arr[h1++];
        }

        while (h2 <= r) {
            help[index++] = arr[h2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[l++] = help[i];
        }
    }
}
