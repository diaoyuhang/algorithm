package sort;

import java.util.Arrays;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 8:53 2020/12/22
 */
public class Code06_SmallSum {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        int result = process(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(result);
    }

    public static int process(int[] arr, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);

        return process(arr, l, mid)
                + process(arr, mid + 1, r)
                + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int index = 0;
        int h1 = l;
        int h2 = mid + 1;
        int result = 0;

        while (h1 <= mid && h2 <= r) {
            if (arr[h1] < arr[h2]) {
                result += ((r - h2 + 1) * arr[h1]);
                help[index++] = arr[h1++];
            } else {
                help[index++] = arr[h2++];
            }
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

        return result;
    }
}
