package TrainingCamp1.class01;

import java.util.Stack;

public class Code04_AllTimesMinToMax {
    public static int max(int[] arr) {
        if (arr == null) {
            return 0;
        }

        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = arr[i] + sum[i - 1];
        }

        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer index = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? sum[i - 1] : sum[i - 1] - sum[stack.peek()]) * arr[index]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer index = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sum[arr.length - 1] : sum[arr.length - 1] - sum[stack.peek()]) * arr[index]);
        }

        return max;

    }
}
