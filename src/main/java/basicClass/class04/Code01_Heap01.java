package basicClass.class04;

public class Code01_Heap01 {
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(6);
        maxHeap.push(9);
        maxHeap.push(3);
        maxHeap.push(97);
        maxHeap.push(12);
        maxHeap.push(34);
        maxHeap.push(22);

        System.out.print(maxHeap.pop() + " ");
        maxHeap.push(56);
        System.out.print(maxHeap.pop() + " ");
        System.out.print(maxHeap.pop() + " ");
        maxHeap.push(99);
        System.out.print(maxHeap.pop() + " ");
        System.out.print(maxHeap.pop() + " ");
        System.out.print(maxHeap.pop() + " ");
        System.out.print(maxHeap.pop() + " ");
        System.out.print(maxHeap.pop() + " ");
        System.out.print(maxHeap.pop() + " ");
        System.out.print(maxHeap.pop() + " ");
    }

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

        public int pop() {
            if (heapSize == 0) {
                throw new RuntimeException("堆空了");
            }
            int result = heap[0];
            swap(heap, --heapSize, 0);
            heapify(heap, 0, heapSize);
            return result;
        }

        private void heapify(int[] heap, int index, int heapSize) {
            //求左子节点位置
            int leftIndex = index * 2 + 1;
            while (leftIndex < heapSize) {
                if (leftIndex + 1 < heapSize) {
                    int largerIndex = heap[leftIndex] >= heap[leftIndex + 1] ? leftIndex : leftIndex + 1;
                    largerIndex = heap[index] >= heap[largerIndex] ? index : largerIndex;
                    if (largerIndex == index) {
                        break;
                    }
                    swap(heap, largerIndex, index);

                    index = largerIndex;
                    leftIndex = index * 2 + 1;
                } else {
                    int largerIndex = heap[index] >= heap[leftIndex] ? index : leftIndex;
                    if (largerIndex == index) {
                        break;
                    }
                    swap(heap, largerIndex, index);

                    index = largerIndex;
                    leftIndex = index * 2 + 1;
                }
            }

        }

        private void heapInsert(int[] heap, int index) {
            while (heap[index] > heap[(index - 1) / 2]) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
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

        private boolean isEmpty() {
            return heapSize == 0;
        }
    }
}
