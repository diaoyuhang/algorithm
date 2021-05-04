package 训练营3;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 如果给你一个二维数组，每一个值表示这一块地形的高度，
 * 求整块地形能装下多少水。
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 11:06 2021/5/4
 */
public class Code12_TrappingRainWaterII {

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

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }

    }

    public static int water(int[][] arr) {
        if (arr == null || arr.length < 3 || arr[0].length < 3) {
            return 0;
        }
        int res = 0;
        int h = arr.length;
        int w = arr[0].length;
        boolean[][] flags = new boolean[h][w];


        PriorityQueue<Node> que = new PriorityQueue<>(new NodeComparator());

        //将矩阵四周的数加入到小根堆中去
        for (int i = 0; i < w; i++) {
            que.add(new Node(arr[0][i], 0, i));
            que.add(new Node(arr[h - 1][i], h - 1, i));
        }
        for (int i = 1; i < h - 1; i++) {
            que.add(new Node(arr[i][0], i, 0));
            que.add(new Node(arr[i][w - 1], i, w - 1));
        }

        Node max = null;
        while (que.isEmpty()) {
            max = que.poll();

            if (max.row - 1 >= 0 && !flags[max.row - 1][max.col]) {
                res += Math.max(0, max.value - arr[max.row - 1][max.col]);
                que.add(new Node(arr[max.row - 1][max.col], max.row - 1, max.col));
                flags[max.row - 1][max.col] = true;
            }

            if (max.row + 1 < h && !flags[max.row + 1][max.col]) {
                res += Math.max(0, max.value - arr[max.row + 1][max.col]);
                que.add(new Node(arr[max.row + 1][max.col], max.row + 1, max.col));
                flags[max.row + 1][max.col] = true;
            }

            if (max.col - 1 >= 0 && !flags[max.row][max.col - 1]) {
                res += Math.max(0, max.value - arr[max.row][max.col - 1]);
                que.add(new Node(arr[max.row][max.col - 1], max.row, max.col - 1));
                flags[max.row][max.col - 1] = true;
            }


            if (max.row + 1 < w && !flags[max.row][max.col + 1]) {
                res += Math.max(0, max.value - arr[max.row][max.col + 1]);
                que.add(new Node(arr[max.row][max.col + 1], max.row, max.col + 1));
                flags[max.row][max.col + 1] = true;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> que = new PriorityQueue<>();
        que.add(1);
        que.add(1);
        que.add(1);
        System.out.println(que.size());
    }
}
