package TrainingCamp3.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_TopKSumCrossTwoArrays {

    public static class Node {
        public int num1;
        public int num2;
        public int sum;

        public Node(int num1, int num2, int sum) {
            this.num1 = num1;
            this.num2 = num2;
            this.sum = sum;
        }
    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.sum - o1.sum;
        }
    }

    public static int[] topKSum(int[] arr1, int[] arr2, int k) {
        int[] res = new int[k];
        boolean[][] flag = new boolean[arr1.length][arr2.length];
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new NodeComparator());
        priorityQueue.add(new Node(arr1.length - 1, arr2.length - 1, arr1[arr1.length - 1] + arr2[arr2.length - 1]));
        flag[arr1.length - 1][arr2.length - 1] = true;

        int resIndex = 0;
        while (resIndex != res.length) {
            Node node = priorityQueue.poll();
            res[resIndex++] = node.sum;

            if (node.num1 > 0 && !flag[node.num1 - 1][node.num2]) {
                priorityQueue.add(new Node(node.num1 - 1, node.num2, arr1[node.num1 - 1] + arr2[node.num2]));
                flag[node.num1 - 1][node.num2] = true;
            }

            if (node.num2 > 0 && !flag[node.num1][node.num2 - 1]) {
                priorityQueue.add(new Node(node.num1, node.num2 - 1, arr1[node.num1] + arr2[node.num2 - 1]));
                flag[node.num1][node.num2 - 1] = true;
            }
        }
        return res;
    }
}
