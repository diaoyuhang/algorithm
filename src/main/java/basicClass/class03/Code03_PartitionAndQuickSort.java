package basicClass.class03;

import java.util.Arrays;

/**
 * @author: diaoyuhang
 * @date 2022/2/8
 */
public class Code03_PartitionAndQuickSort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 127, 87, 81, 2, 3, 5, 6, 56, 7, 8, 89};
        partitionAndQuickSort2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void partitionAndQuickSort1(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = partition1(arr, 0, right);
        partitionAndQuickSort1(arr, 0, mid - 1);
        partitionAndQuickSort1(arr, mid + 1, right);
    }

    private static int partition1(int[] arr, int left, int right) {
        int less = left - 1;
        int more = right;
        int index = left;

        int num = arr[right];
        while (index < more) {
            if (arr[index] < num) {
                swap(arr, ++less, index++);
            } else if (arr[index] > num) {
                swap(arr, --more, index);
            } else {
                index++;
            }
        }
        swap(arr, more, right);
        return less + 1;
    }


    public static void partitionAndQuickSort2(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int[] index = partition2(arr, left, right);
        partitionAndQuickSort2(arr, left, index[0] - 1);
        partitionAndQuickSort2(arr, index[1] + 1, right);
    }

    private static int[] partition2(int[] arr, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, left};
        }

        int less = left - 1;
        int more = right;
        int index = left;
        swap(arr,left + (int) (Math.random() * (right - left + 1)), right);

        int num = arr[right];

        while (index < more) {
            if (arr[index] == num) {
                index++;
            } else if (arr[index] < num) {
                swap(arr, ++less, index++);
            } else {
                swap(arr, --more, index);
            }
        }
        swap(arr, more, right);

        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
