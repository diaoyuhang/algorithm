package TrainingCamp1.class01;

import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 */
public class Code02_AllLessNumSubArray {

    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qMin = new LinkedList<>();
        LinkedList<Integer> qMax = new LinkedList<>();
        int left = 0;
        int right = 0;
        int res = 0;

        while (left < arr.length) {
            while (right < arr.length) {
                while (!qMin.isEmpty() && arr[qMin.peekLast()] > arr[right]) {
                    qMin.pollLast();
                }
                qMin.addLast(right);

                while (!qMax.isEmpty() && arr[qMax.peekLast()] < arr[right]) {
                    qMax.pollLast();
                }
                qMax.add(right);

                if (arr[qMax.peekFirst()] - arr[qMin.peekFirst()] <= num) {
                    res++;
                    right++;
                }
                break;
            }
            if (qMin.peekFirst() == left) {
                qMin.pollFirst();
            }
            if (qMax.peekFirst() == left) {
                qMax.pollFirst();
            }

            left++;
        }
        return res;
    }
}
