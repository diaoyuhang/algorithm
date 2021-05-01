package 训练营1.bfprt_蓄水池;

import java.util.Arrays;

/**
 * 在无序数组中求第K小的数
 * 1）改写快排的方法
 * 2）bfprt算法
 *
 * @author: Mr.diao
 * @date: 9:36 2021/3/5
 */
public class Code01_FindMinKth {
    public static void main(String[] args) {
        int[] arr = {1, 34, 12, 32, 11, 55, 33, 7, 21};
        System.out.println(minKth1(Arrays.copyOf(arr, arr.length), 5));
        System.out.println(minKth2(Arrays.copyOf(arr, arr.length), 5));
    }

    public static int minKth1(int[] arr, int k) {
        return process1(arr, 0, arr.length - 1, k);
    }

    //改写快排，荷兰国旗
    private static int process1(int[] arr, int l, int r, int k) {
        if (l == r) {
            return arr[l];
        }

        int pivot = arr[l + (int) (Math.random() * ((r - l) >> 1))];
        int[] rang = partition(arr, l, r, pivot);
        if (k >= rang[0] && k <= rang[0]) {
            return arr[k];
        } else if (k < rang[0]) {
            return process1(arr, l, rang[0] - 1, k);
        } else {
            return process1(arr, rang[1] + 1, r, k);
        }
    }

    private static int[] partition(int[] arr, int l, int r, int pivot) {
        int less = l;
        int more = r;
        int cur = l;
        while (cur <= more) {
            if (arr[cur] < pivot) {
                swap(arr, cur++, less++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, more--);
            } else {
                cur++;
            }
        }
        return new int[]{less, more};
    }

    //bfprt算法
    public static int minKth2(int[] arr, int k) {
        return bfprt(arr, 0, arr.length - 1, k);

    }

    private static int bfprt(int[] arr, int l, int r, int k) {
        if (l == r) {
            return arr[l];
        }
        int pivot = medianOfMedians(arr, l, r);

        int[] rang = partition(arr, l, r, pivot);
        if (k >= rang[0] && k <= rang[1]) {
            return arr[k];
        } else if (k < rang[0]) {
            return bfprt(arr, l, rang[0] - 1, k);
        } else {
            return bfprt(arr, rang[1] + 1, r, k);
        }
    }

    private static int medianOfMedians(int[] arr, int l, int r) {
        int size = r - l + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];

        for (int t = 0; t < mArr.length; t++) {
            l = l + t * 5;
            mArr[t] = getMedian(arr, l, Math.min(r, l + 4));
        }

        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    private static int getMedian(int[] arr, int l, int r) {
        insertionSort(arr, l, r);
        return arr[l + ((r - l) >> 1)];
    }

    private static void insertionSort(int[] arr, int l, int r) {

        for (int i = l + 1; i <= r; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
