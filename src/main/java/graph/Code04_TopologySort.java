package graph;

import java.util.*;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 16:15 2021/2/15
 */
public class Code04_TopologySort {

    public static List<Node> sortedTopology(Graph graph) {
        if (graph == null) {
            return null;
        }
        HashMap<Node, Integer> inMap = new HashMap<>();
        //入度为0的结点进入
        Queue<Node> zeroInQueue = new LinkedList<>();

        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        ArrayList<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node poll = zeroInQueue.poll();
            result.add(poll);
            //遍历当前入度为0的结点的下面的结点，将这些结点的入度-1
            for (Node n : poll.nexts) {
                inMap.put(n, inMap.get(n) - 1);
                if (inMap.get(n) == 0) {
                    zeroInQueue.add(n);
                }
            }
        }
        return result;
    }
}
