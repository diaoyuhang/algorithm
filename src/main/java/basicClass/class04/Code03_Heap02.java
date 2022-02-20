package basicClass.class04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Code03_Heap02 {

    public static class MyHeap<T> {
        private List<T> heap;
        private Integer heapSize;
        private Comparator<T> comparator;

        public MyHeap(Comparator<T> comparator) {
            this.heap = new ArrayList<>();
            this.heapSize = 0;
            this.comparator = comparator;
        }


        public void add(T value) {
            heap.add(value);
            heapInsert(heapSize++);
        }

        public T pop() {
            T ans = heap.get(0);
            int end = --heapSize;
            swap(0, end);
            heap.remove(end);
            heapify(0);
            return ans;
        }

        private void heapify(int index) {
            int leftIndex = index * 2 + 1;
            while (leftIndex < heapSize) {
                int largerIndex = leftIndex;
                if (leftIndex + 1 < heapSize) {
                    if (comparator.compare(heap.get(leftIndex + 1), heap.get(leftIndex)) > 0) {
                        largerIndex = leftIndex + 1;
                    }
                } else {
                    if (comparator.compare(heap.get(index), heap.get(leftIndex)) > 0) {
                        largerIndex = index;
                    }
                }

                if (largerIndex == index) {
                    break;
                }
                swap(index, largerIndex);
                index = largerIndex;
                leftIndex = index * 2 + 1;
            }
        }

        private void heapInsert(int index) {
            while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) > 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void swap(int index1, int index2) {
            T temp = heap.get(index1);
            heap.set(index1, heap.get(index2));
            heap.set(index2, temp);
        }

        public boolean isEmpty() {

            return heapSize > 0;
        }
    }
}
