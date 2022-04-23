package TrainingCamp1.class01;

import java.util.LinkedList;

public class Code01_SlidingWindowMaxArray {

    public static int[] getMaxWindow(int[] arr, int len) {
        if (arr == null || len < 1 || len > arr.length) {
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] result = new int[arr.length - len + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] < arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);

            if (qmax.peekFirst() == i - len) {
                qmax.pollFirst();
            }
            if (i >= len - 1) {
                result[index++] = qmax.peekFirst();
            }
        }

        return result;
    }


}
