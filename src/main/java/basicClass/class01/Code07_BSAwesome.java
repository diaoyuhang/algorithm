package basicClass.class01;

/**
 * 局部最小值问题
 * 在无序数组中找到任意一个数比其左右两边的数要小
 */
public class Code07_BSAwesome {

    public static int bsAwesome(int[] arr) {
        int index = -1;
        if (arr == null || arr.length == 0) {
            return index;
        }

        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }

        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        int left = 1;
        int right = arr.length - 2;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }
}
