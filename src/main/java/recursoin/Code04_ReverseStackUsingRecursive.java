package recursoin;

import java.util.Stack;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 19:54 2021/2/16
 */
public class Code04_ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack) {
        if (stack == null || stack.size() == 0) {
            return;
        }
        int last = process(stack);
        reverse(stack);
        stack.push(last);
    }

    private static int process(Stack<Integer> stack) {
        Integer result = stack.pop();
        //取出栈中最后一个元素
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = process(stack);
            stack.push(result);
            return last;
        }
    }
}
