package TrainingCamp1.class03;

public class Code01_FindMinKth {
    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
//            int ans1 = minKth1(arr, k);
            int ans2 = minKth2(arr, k);
            int ans3 = minKth3(arr, k);
            if (ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i != ans.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static int minKth2(int[] array, int k) {
        int[] arr = copyArray(array);
        return process2(arr, 0, array.length - 1, k - 1);
    }

    private static int process2(int[] array, int left, int right, int k) {
        if (left == right) {
            return array[left];
        }
        int pivot = array[left + (int) (Math.random() * (right - left + 1))];

        int[] result = partition(array, left, right, pivot);
        if (result[0] <= k && result[1] >= k) {
            return array[k];
        } else if (k < result[0]) {
            return process2(array, left, result[0] - 1, k);
        } else {
            return process2(array, result[1] + 1, right, k);
        }
    }

    private static int[] partition(int[] array, int left, int right, int pivot) {

        int l = left - 1;
        int r = right;
        int index = left;

        while (index <= r) {
            if (array[index] < pivot) {
                swap(array, ++l, index++);
            } else if (array[index] > pivot) {
                swap(array, r--, index);
            } else {
                index++;
            }
        }
        return new int[]{l + 1, r};
    }

    public static int minKth3(int[] arr, int k) {
        int[] arr1 = copyArray(arr);
        return bfprt(arr1, 0, arr.length - 1, k - 1);
    }

    public static int bfprt(int[] arr, int l, int r, int k) {
        if (l == r) {
            return arr[l];
        }
        //求这个范围上的最佳中间数
        int pivot = medianOfMedians(arr, l, r);
        int[] range = partition(arr, l, r, pivot);
        if (range[0] <= k && range[1] >= k) {
            return arr[k];
        } else if (range[0] >= k) {
            return bfprt(arr, l, range[0] - 1, k);
        } else {
            return bfprt(arr, range[1] + 1, r, k);
        }
    }

    private static int medianOfMedians(int[] arr, int l, int r) {
        int size = r - l + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] temp = new int[size / 5 + offset];

        for (int i = 0; i < temp.length; i++) {
            int left = l + i * 5;
            temp[i] = getMedian(arr, left, Math.min(r, left + 4));
        }
        return bfprt(temp, 0, temp.length - 1, (temp.length) / 2);
    }

    private static int getMedian(int[] arr, int left, int right) {
        insertSort(arr, left, right);
        return arr[(left + right) / 2];
    }

    private static void insertSort(int[] arr, int left, int right) {
        for (int i = left + 1; i < right; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j + 1] < arr[j]) {
                    swap(arr, j + 1, j);
                }
            }
        }
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
