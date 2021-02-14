package greedy;

import java.util.PriorityQueue;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 17:08 2021/2/13
 */
public class Code03_LessMoneySplitGold {
    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (lessMoney1(arr) != lessMoney2(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
    public static int lessMoney1(int[] arrs) {
        if (arrs == null || arrs.length == 0) {
            return 0;
        }
        return process(arrs, 0);
    }

    private static int process(int[] arrs, int result) {
        if (arrs.length == 1) {
            return result;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arrs.length; i++) {
            for (int j = i + 1; j < arrs.length; j++) {
                int a = process(merge(arrs, i, j), result + arrs[i] + arrs[j]);
                ans = Math.min(ans, a);
            }
        }
        return ans;
    }

    private static int[] merge(int[] arrs, int i, int j) {
        int[] arr = new int[arrs.length - 1];
        int index = 0;
        for (int n = 0; n < arrs.length; n++) {
            if (n != i && n != j) {
                arr[index++] = arrs[n];
            }
        }
        arr[index] = arrs[i] + arrs[j];
        return arr;

    }

    public static int lessMoney2(int[] arrs) {
        //利用小根堆进行排序
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int arr : arrs) {
            pQ.add(arr);
        }
        int sum = 0;
        int cur = 0;
        //利用哈夫曼树求最小值
        while (pQ.size() > 1) {
            cur = pQ.poll() + pQ.poll();
            sum += cur;
            pQ.add(cur);
        }
        return sum;
    }
}
