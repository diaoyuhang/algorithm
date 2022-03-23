package basicClass.class11;

import java.util.Stack;

public class Code04_ReverseStackUsingRecursive {

    public static void reverseStackUsingRecursive(Stack<Integer> stack) {
        if (stack == null || stack.isEmpty()) {
            return;
        }
        Integer num = reverse(stack);
        reverseStackUsingRecursive(stack);
        stack.push(num);
    }

    private static Integer reverse(Stack<Integer> stack) {
        Integer pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        } else {
            Integer last = reverse(stack);
            stack.push(pop);
            return last;
        }
    }

}
