package basicClass.class04;

import java.util.Arrays;

public class Code02_HeapSort {

    public static void main(String[] args) {
        Code02_HeapSort code02_heapSort = new Code02_HeapSort();
        int[] arr = {34, 4, 65213, 46, 4, 756, 23, 4, 3456, 67, 567789, 6, 45, 653, 5, 4242, 35, 346, 457, 568, 56, 75, 34, 52, 6, 7, 52345};
        code02_heapSort.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int size = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            swap(arr, 0, size);
            size--;
            heapify(arr, 0, size);
        }

    }

    private void heapify(int[] arr, int index, int size) {
        int leftIndex = index * 2 + 1;
        while (leftIndex <= size) {
            int largerIndex = leftIndex + 1 <= size && arr[leftIndex + 1] > arr[leftIndex] ? leftIndex + 1 : leftIndex;
            largerIndex = arr[index] > arr[largerIndex] ? index : largerIndex;
            if (largerIndex == index) {
                break;
            }

            swap(arr, index, largerIndex);
            index = largerIndex;
            leftIndex = index * 2 + 1;
        }
    }

    private void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(int[] arr, int index, int pIndex) {
        int temp = arr[index];
        arr[index] = arr[pIndex];
        arr[pIndex] = temp;
    }
}
