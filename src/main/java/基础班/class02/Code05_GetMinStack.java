package 基础班.class02;

import java.util.Stack;

public class Code05_GetMinStack {

    public static class MyStack {
        private Stack<Integer> stackData;
        private Stack<Integer> minStack;

        public MyStack() {
            this.stackData = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(int value) {
            if (stackData.isEmpty()) {
                stackData.push(value);
                minStack.push(value);
            } else {
                if (minStack.peek() < value) {
                    minStack.push(minStack.peek());
                } else {
                    minStack.push(value);
                }
                stackData.push(value);
            }
        }

        public Integer pop() {
            if (stackData.isEmpty()) {
                return null;
            }
            minStack.pop();
            return stackData.pop();
        }

        public Integer getMinStack() {
            if (stackData.isEmpty()) {
                return null;
            }
            stackData.pop();
            return minStack.pop();
        }
    }
}
