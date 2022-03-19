package basicClass.class10;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Code05_Prim {

    public static Set<Edge> prim(Graph graph) {
        HashSet<Edge> result = new HashSet<>();
        HashSet<Node> nodes = new HashSet<>();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);

        for (Node n : graph.nodes.values()) {
            if (!nodes.contains(n)) {
                List<Edge> edges = n.edges;

                for (Edge e : edges) {
                    priorityQueue.add(e);
                }
                nodes.add(n);
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll();
                    if (!nodes.contains(edge.to)) {
                        result.add(edge);
                        nodes.add(edge.to);
                        for (Edge e : edge.to.edges) {
                            priorityQueue.add(e);
                        }
                    }
                }
            }
        }
        return result;
    }
}
