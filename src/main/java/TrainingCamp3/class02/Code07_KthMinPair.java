package TrainingCamp3.class02;

import java.util.Comparator;

public class Code07_KthMinPair {

    public static class Pair {
        public int x;
        public int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class PairComparator implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.x != o2.x ? o1.x - o2.x : o1.y - o2.y;
        }
    }

    public static int[] kthMinPair(int[] arr, int k) {
        int n = arr.length;
        int k1 = (k - 1) / n + 1;

        int firstNum = getMinKth(arr, 0, arr.length - 1, k1);

        int k2 = k - (firstNum - 1) * n;
        int secondNum = getMinKth(arr, 0, arr.length - 1, k2);
        return new int[]{firstNum, secondNum};
    }

    private static int getMinKth(int[] arr, int left, int right, int k) {
        int index = 0;
        int l = left - 1;
        int r = right;

        int num = arr[(int) (Math.random() * (arr.length - 1))];

        while (index <= r) {
            if (arr[index] == num) {
                index++;
            } else if (arr[index] < num) {
                swap(arr, index++, ++l);
            } else {
                swap(arr, index, r--);
            }
        }
        if (k > l && k < r) {
            return k;
        } else if (k < l) {
            return getMinKth(arr, left, l, k);
        } else {
            return getMinKth(arr, r, right, k);
        }
    }

    private static void swap(int[] arr, int index, int i) {
        int temp = arr[index];
        arr[index] = arr[i];
        arr[i] = temp;
    }
}
