package basicClass.class04;

public class Code01_Heap01 {

    public static class MaxHeap {
        private int[] heap;
        private int limit;
        private int heapSize = 0;

        public MaxHeap(int size) {
            heap = new int[size];
            limit = size;
        }
        //这里变成一个大根堆所需要的时间复杂度n*logn;
        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("堆满了");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize);
            heapSize++;
        }

        private void heapInsert(int[] heap, int index) {
            while (heap[index] > heap[(index - 1) >> 2]) {
                swap(heap, index, (index - 1) >> 2);
                index = (index - 1) >> 2;
            }
        }

        private void swap(int[] heap, int index1, int index2) {
            int temp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = temp;
        }

        private boolean isFull() {
            return heapSize == limit;
        }
    }
}
