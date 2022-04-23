package TrainingCamp1.class01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code03_MonotonousStack {

    public static int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];

        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> list = stack.pop();

                int leftNearestIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer index : list) {
                    res[index][0] = leftNearestIndex;
                    res[index][1] = i;
                }
            }

            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> pop = stack.pop();
            int leftNearestIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer index : pop) {
                res[index][0] = leftNearestIndex;
                res[index][1] = -1;
            }
        }
        return res;
    }
}
