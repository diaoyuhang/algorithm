package TrainingCamp3.class03;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Code06_TopKTimes {

    public static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.times - o1.times;
        }
    }

    public static void printTopKAndRank(String[] str, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : str) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }

        PriorityQueue<Node> nodes = new PriorityQueue<>(k, new NodeComparator());

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            if (nodes.size() < k) {
                nodes.add(node);
            } else {
                if (nodes.peek().times < node.times) {
                    nodes.poll();
                    nodes.add(node);
                }
            }
        }

        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            System.out.println(node.str);
        }
    }
}
