package basicClass.class10;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Code03_TopologySort {

    public static void topologySort(Graph graph) {
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);

            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }

        while (!zeroInQueue.isEmpty()) {
            Node node = zeroInQueue.poll();
            System.out.println(node.value);
            for (Node next : node.next) {
                Integer in = inMap.get(next);
                if (in == 1) {
                    zeroInQueue.add(next);
                }
                inMap.put(next, in - 1);
            }
        }

    }
}
