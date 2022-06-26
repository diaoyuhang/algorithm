package TrainingCamp3.class02;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code05_TrappingRainWaterII {

    public static class Node {
        public int value;
        public int row;
        public int col;

        public Node(int v, int r, int c) {
            value = v;
            row = r;
            col = c;
        }
    }

    public static class NodeComparator implements Comparator<Code05_TrappingRainWaterII.Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    public static int trapRainWater(int[][] heightMap) {

        int N = heightMap.length;
        int M = heightMap[0].length;
        boolean[][] isEnter = new boolean[N][M];

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new NodeComparator());
        //先将四周的点加入到小跟堆中；

        for (int i = 0; i < M; i++) {
            if (!isEnter[0][i]) {
                isEnter[0][i] = true;
                priorityQueue.add(new Node(0, i, heightMap[0][i]));
            }

            if (!isEnter[N - 1][i]) {
                isEnter[N - 1][i] = true;
                priorityQueue.add(new Node(N - 1, i, heightMap[N - 1][i]));
            }
        }
        for (int i = 0; i < N; i++) {
            if (!isEnter[i][M - 1]) {
                isEnter[i][M - 1] = true;
                priorityQueue.add(new Node(i, M - 1, heightMap[i][M - 1]));
            }

            if (!isEnter[i][0]) {
                isEnter[i][0] = true;
                priorityQueue.add(new Node(i, 0, heightMap[i][0]));
            }
        }

        int max = 0;
        int water = 0;
        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            max = Math.max(0, node.value);
            if (node.row - 1 >= 0 && !isEnter[node.row - 1][node.col]) {
                isEnter[node.row - 1][node.col] = true;
                water += Math.max(0, max - heightMap[node.row - 1][node.col]);
            }
            if (node.row + 1 < N && !isEnter[node.row + 1][N]) {
                isEnter[node.row + 1][N] = true;
                water += Math.max(0, max - heightMap[node.row + 1][N]);
            }
            if (node.col - 1 >= 0 && !isEnter[node.row][node.col - 1]) {
                isEnter[node.row][node.col - 1] = true;
                water += Math.max(0, max - heightMap[node.row][node.col - 1]);
            }
            if (node.col + 1 < M && !isEnter[node.row][node.col + 1]) {
                isEnter[node.row][node.col + 1] = true;
                water += Math.max(0, max - heightMap[node.row][node.col + 1]);
            }
        }
        return water;
    }
}
