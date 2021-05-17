package 训练营3;

import java.util.HashMap;

/**
 * 请实现如下结构：
 * TopRecord{
 * public TopRecord(int K)  :  构造时事先指定好K的大小，构造后就固定不变了
 * public  void add(String str)  :   向该结构中加入一个字符串，可以重复加入
 * public  List<String> top() : 返回之前加入的所有字符串中，词频最大的K个
 * }
 * 要求：
 * add方法，复杂度O(log K);
 * top方法，复杂度O(K)
 *
 * @author: Mr.diao
 * @date: 10:44 2021/5/17
 */
public class Code21_TopKTimesRealTime {
    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }

    public static class TopRecord {
        private Node[] heap;
        private int heapSize;

        private HashMap<String, Node> strNodeMap;
        private HashMap<Node, Integer> nodeIndexMap;

        public TopRecord(int K) {
            this.heap = new Node[K];
            heapSize = 0;
            strNodeMap = new HashMap<String, Node>();
            nodeIndexMap = new HashMap<Node, Integer>();
        }

        public void add(String str) {
            //表示不在堆上
            int preIndex = -1;
            Node cur = null;

            if (!strNodeMap.containsKey(str)) {
                cur = new Node(str, 1);
                strNodeMap.put(str, cur);
                nodeIndexMap.put(cur, preIndex);
            } else {
                cur = strNodeMap.get(str);
                preIndex = nodeIndexMap.get(cur);
                cur.times++;
            }

            if (preIndex == -1) {
                if (heapSize == heap.length) { //堆满了
                    if (heap[0].times < cur.times) { //比较第一个节点大小
                        nodeIndexMap.put(heap[0], -1);

                        heap[0] = cur;
                        heapify(0, heapSize);
                    }
                } else {//如果没有满，则插入到下一个位置
                    preIndex = heapSize;
                    heap[heapSize++] = cur;
                    nodeIndexMap.put(cur, preIndex);
                    //将刚加入的节点比较，小的话向上移动
                    heapInsert(preIndex);
                }
            } else { //已经在堆上了 比较将大的向下移动
                heapify(preIndex, heapSize);
            }
        }

        public void heapInsert(int index) {
            while (index > 0 && heap[index].times < heap[(index - 1) / 2].times) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        //将大节点向下沉 小根堆
        public void heapify(int index, int heapSize) {
            int leftIndex = index * 2 + 1;
            while (leftIndex < heapSize) {
                //取出左右两个子节点，较小的那个
                int smallIndex = leftIndex + 1 < heapSize && heap[leftIndex].times > heap[leftIndex + 1].times ? leftIndex + 1 : leftIndex;
                //再将父节点和子节点比较大小
                smallIndex = heap[index].times > heap[smallIndex].times ? smallIndex : index;
                //如果当父节点就是最小的，跳出循环
                if (smallIndex == index) {
                    break;
                }
                swap(index, smallIndex);
                index = smallIndex;
                leftIndex = index * 2 + 1;
            }

        }

        private void swap(int index, int largeIndex) {
            Node temp = heap[index];
            heap[index] = heap[largeIndex];
            heap[largeIndex] = temp;
        }

    }

    public static void main(String[] args) {
        System.out.println(1 / 2);
    }
}
