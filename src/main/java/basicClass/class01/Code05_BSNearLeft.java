package basicClass.class01;

/**
 * 在一个有序数组中，找>=某个数最左侧的位置
 */
public class Code05_BSNearLeft {

    public static int bsNearLeft(int[] sortedArr, int num) {
        int index = -1;
        int left = 0;
        int right = sortedArr.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (sortedArr[mid] >= num) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }
}
