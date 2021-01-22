package sort;

import java.util.Arrays;

/**
 * @author: Mr.diao
 * @date: 14:21 2020/12/24
 */
public class Code09_HeapSort {

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 1, 234, 43, 5435, 64, 56, 45};
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static class MaxHeap {
        public void heapSort(int[] arr) {
            if (arr == null || arr.length < 2) {
                return;
            }
//            for (int i = 0; i < arr.length; i++) {
//                heapInsert(arr, i);
//            }

            for (int i = arr.length - 1; i >= 0; i--) {
                heapify(arr, i, arr.length);

            }

            int size = arr.length;
            swap(arr, 0, --size);
            while (size > 0) {
                heapify(arr, 0, size);
                swap(arr, 0, --size);
            }

        }

        public void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }

        }

        //将index元素对比两个子节点，向下沉
        public void heapify(int[] arr, int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int largeNum = left + 1 < size && arr[left] < arr[left + 1] ? left + 1 : left;
                largeNum = arr[largeNum] > arr[index] ? largeNum : index;

                //如果最大的还是父节点，就不需要再动
                if (largeNum == index) {
                    break;
                }
                swap(arr, largeNum, index);
                index = largeNum;
                left = index * 2 + 1;
            }
        }

        private void swap(int[] arr, int index, int i) {
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }
}
