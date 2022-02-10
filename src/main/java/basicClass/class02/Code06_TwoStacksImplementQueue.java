package basicClass.class02;

import java.util.Stack;

/**
 * @author: diaoyuhang
 * @date 2022/2/8
 */
public class Code06_TwoStacksImplementQueue {

    public static class MyQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public MyQueue() {
            stackPop = new Stack<>();
            stackPush = new Stack<>();
        }

        public void pushToPop() {
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(Integer num) {
            stackPush.push(num);
            pushToPop();
        }

        public Integer pop() {
            if (stackPop.isEmpty()) {
                return null;
            }
            return stackPop.pop();
        }
    }
}
