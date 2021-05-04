package 训练营3;

/**
 * 长度为N的数组arr，一定可以组成N^2个数值对。
 * 例如arr = [3,1,2]，
 * 数值对有(3,3) (3,1) (3,2) (1,3) (1,1) (1,2) (2,3) (2,1) (2,2)，
 * 也就是任意两个数都有数值对，而且自己和自己也算数值对。
 * 数值对怎么排序？规定，第一维数据从小到大，第一维数据一样的，第二维数组也从小到大。所以上面的数值对排序的结果为：
 * (1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)
 * <p>
 * 给定一个数组arr，和整数k，返回第k小的数值对。
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 15:48 2021/5/4
 */
public class Code14_KthMinPair {

    public static int[] kthMinPair3(int[] arr, int k) {
        int n = arr.length;
        if (k > n * n) {
            return null;
        }
        int firstNum = getMinKthByBFPRT(arr, (k - 1) / n + 1);
        //找到小于firstNum有多少个
        int lessFirstNumSize = 0;
        //找到等于firstNumSize的有多少个
        int firstNumSize = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == firstNum) {
                firstNumSize++;
            } else if (arr[i] < firstNum) {
                lessFirstNumSize++;
            }
        }
        int secondNum = getMinKthByBFPRT(arr, (k - lessFirstNumSize * n - 1) / firstNumSize + 1);

        return new int[]{firstNum, secondNum};
    }

    private static int getMinKthByBFPRT(int[] arr, int k) {
        return select(arr, 0, arr.length - 1, k - 1);
    }

    private static int select(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }
        int pivot = medianOfMedians(arr, left, right);
        int[] pivotRange = partition(arr, left, right, pivot);

        if (k >= pivotRange[0] && k <= pivotRange[1]) {
            return arr[k];
        } else if (k < pivotRange[0]) {
            return select(arr, left, pivotRange[0] - 1, k);
        } else {
            return select(arr, pivotRange[1] + 1, right, k);
        }
    }

    private static int[] partition(int[] arr, int left, int right, int pivot) {
        int less = left;
        int more = right;
        int cur = left;
        while (cur <= more) {
            if (arr[cur] < pivot) {
                swap(arr, less++, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, more--, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less, more};
    }

    private static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int num = right - left + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        int[] mArr = new int[num / 5 + offset];

        for (int i = 0; i < mArr.length; i++) {
            int begin = left + i * 5;
            int end = begin + 4;
            mArr[i] = getMedian(arr, begin, Math.min(right, end));
        }
        return select(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    private static int getMedian(int[] arr, int begin, int end) {
        insertSort(arr, begin, end);
        return arr[begin + ((end - begin) >> 1)];
    }

    private static void insertSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i <= end; i++) {
            for (int j = i; j > begin; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }
}
