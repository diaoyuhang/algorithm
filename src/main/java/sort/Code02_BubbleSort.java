package sort;

import java.util.Arrays;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 14:40 2020/10/14
 */
public class Code02_BubbleSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1])
                    swap(arr, j, j + 1);
            }
        }
    }

    private static void swap(int[] arr, int j, int i) {
        arr[j] = arr[j] ^ arr[i];
        arr[i] = arr[j] ^ arr[i];
        arr[j] = arr[j] ^ arr[i];
    }

    public static void main(String[] args) {
        int testTime = 5000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxValue, maxSize);
            int[] newarr = copy(arr);
            sort(arr);
            comparator(newarr);
            if (!isEqual(arr, newarr)) {
                success = false;
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(newarr));
                break;
            }
        }
        System.out.println(success ? "Nice!" : "Fucking fucked!");
    }

    public static int[] generateRandomArray(int maxValue, int maxSize) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random() - maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copy(int[] arr) {
        if (arr == null)
            return null;

        int[] newArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }

        return newArr;
    }

    private static boolean isEqual(int[] arr, int[] newarr) {
        if (arr == null || newarr == null)
            return false;
        if (arr.length != newarr.length)
            return false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]!=newarr[i])
                return false;
        }
        return true;
    }

    private static void comparator(int[] newarr) {
        Arrays.sort(newarr);
    }
}
