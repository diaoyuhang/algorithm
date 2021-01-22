package sort;

/**
 * @author: Mr.diao
 * @date: 9:20 2020/12/24
 */
public class Code08_Heap {
    public static void main(String[] args) {
        int limit = 100;
        int value = 1000;
        int curLimit = (int) (Math.random() * limit) + 1;
        System.out.println("limit:"+curLimit);
        MaxHeap maxHeap = new MaxHeap(curLimit);
        for (int i = 0; i < curLimit;i++){
            int curValue = (int) (Math.random() * value);
            maxHeap.push(curValue);
        }
        while(true){
            int pop = maxHeap.pop();
            System.out.println(pop);
        }
    }

    //大根堆
    public static class MaxHeap {
        private int[] heap;
        private int limit;
        private int heapSize;

        public MaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public void push(int value) {
            if (heapSize == limit) {
                throw new RuntimeException("已满");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        //将大的值向上移动
        private void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        //交换值
        private void swap(int[] arr, int index, int i) {
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }

        //弹出操作，返回最大值，并将最大值删除
        public int pop() {
            if (heapSize==0){
                throw new RuntimeException("堆中无数据");
            }
            int result = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return result;
        }

        //将指定值比较，小的向下沉
        private void heapify(int[] heap, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                //比较左右两个节点的值大小
                int largeIndex = left + 1 < heapSize && heap[left] < heap[left + 1] ? left + 1 : left;
                largeIndex = heap[largeIndex] < heap[index] ? index : largeIndex;
                if (largeIndex == index) {
                    break;
                }
                swap(heap, largeIndex, index);
                index = largeIndex;
                left = index * 2 + 1;
            }
        }
    }
}
