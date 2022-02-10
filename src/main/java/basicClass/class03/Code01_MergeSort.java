package basicClass.class03;

import java.util.Arrays;

/**
 * @author: diaoyuhang
 * @date 2022/2/8
 */
public class Code01_MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int left = 0;
        int right = arr.length - 1;
        process(arr, left, right);
    }

    private static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] helper = new int[right - left + 1];
        int l = left;
        int r = mid + 1;
        int index = 0;

        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) {
                helper[index++] = arr[l++];
            } else {
                helper[index++] = arr[r++];
            }
        }

        while (l <= mid) {
            helper[index++] = arr[l++];
        }

        while (r <= right) {
            helper[index++] = arr[r++];
        }
        index = 0;
        for (int i = left; i <= right; i++) {
            arr[i] = helper[index++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {17, -41, 28, -67, 1, -97, 72, -28, 52, 40, 59, -7, -27, 18, -9, -7, -88, -53, 13, 18, -37, -40, -84, 11, 7, 14, -79, 5, 9, -10, 48};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
