package sort;

import java.util.Arrays;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 9:57 2020/12/22
 */
public class Code07_PartitionAndQuickSort {

    public static void main(String[] args) {
//        int[] arr = {1, 3, 4, 2, 5};
//        process2(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            process2(arr1, 0, arr1.length - 1);
            System.out.println(Arrays.toString(arr1));
        }
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void process1(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = partition(arr, l, r);
        process1(arr, l, mid - 1);
        process1(arr, mid + 1, r);
    }

    private static int partition(int[] arr, int l, int r) {
        if (l > r) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        //定义了一个边界
        int lessEqual = l - 1;

        int index = l;
        while (index < r) {
            //当arr[index]<arr[r]的时候，将arr[index]交换到lessEqual的左边位置
            if (arr[index] < arr[r]) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
        //将用于对比的数交换到lessEqual位置
        swap(arr, r, ++lessEqual);
        return lessEqual;
    }

    private static void swap(int[] arr, int index, int lessEqual) {
        int temp = arr[lessEqual];
        arr[lessEqual] = arr[index];
        arr[index] = temp;
    }

    public static void process2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int[] equalArea = netherlandsFlag(arr, l, r);
        process2(arr, l, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, r);
    }

    private static int[] netherlandsFlag(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }

        int less = l - 1;
        int more = r;
        int index = l;
        while (index < more) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }
}
