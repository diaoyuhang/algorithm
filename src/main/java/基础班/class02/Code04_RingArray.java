package 基础班.class02;

public class Code04_RingArray {

    public static class MyQueue {
        private int[] arr;
        private int limit;
        private int pushIndex;
        private int popIndex;
        private int size;

        public MyQueue(int length) {
            this.arr = new int[length];
            this.limit = length;
            this.pushIndex = 0;
            this.popIndex = 0;
            this.size = 0;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("栈满了");
            }
            size++;
            arr[pushIndex] = value;
            pushIndex = nextIndex(pushIndex);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("栈空了");
            }
            size--;
            int result = arr[popIndex];
            popIndex = nextIndex(popIndex);
            return result;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private int nextIndex(int index) {
            return index < limit - 1 ? index++ : 0;
        }
    }
}
