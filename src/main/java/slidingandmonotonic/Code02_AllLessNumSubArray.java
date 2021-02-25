package slidingandmonotonic;

import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 *
 * @author: Mr.diao
 * @date: 15:16 2021/2/25
 */
public class Code02_AllLessNumSubArray {
    // for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = getRandomArray(30);
        int num = 5;
        printArray(arr);
        System.out.println(way1(arr, num));


    }

    public static int way1(int[] arr, int num) {
        LinkedList<Integer> qMax = new LinkedList<>();
        LinkedList<Integer> qMin = new LinkedList<>();
        int L = 0;
        int R = 0;
        int res = 0;

        while (L < arr.length) {
            while (R < arr.length) {
                while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[R]) {
                    qMax.pollLast();
                }
                qMax.addLast(R);

                while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[R]) {
                    qMin.pollLast();
                }
                qMin.addLast(R);

                if (arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > num) {
                    break;
                }
                R++;
            }
            res += R - L;
            if (qMin.peekFirst() == L) {
                qMin.pollFirst();
            }

            if (qMax.peekFirst() == L) {
                qMax.pollFirst();
            }
            L++;
        }

        return res;
    }
}
