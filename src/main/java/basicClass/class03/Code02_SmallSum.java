package basicClass.class03;

/**
 * @author: diaoyuhang
 * @date 2022/2/8
 */
public class Code02_SmallSum {

    public static Integer mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static Integer process(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid) +
                process(arr, mid + 1, right) +
                merge(arr, left, mid, right);
    }

    private static Integer merge(int[] arr, int left, int mid, int right) {
        int[] helper = new int[right - left + 1];
        int l = left;
        int r = mid + 1;
        int index = 0;

        int result = 0;
        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) {
                helper[index++] = arr[l];
                result += arr[l] * (right - r + 1);
                l++;
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
        for (; left <= right; left++) {
            arr[left] = helper[index++];
        }
        return result;
    }

}
