package basicClass.class10;

import java.util.*;

public class Code04_Kruskal {

    public class UnionFind {
        public Map<Node, Node> fatherMap;
        public Map<Node, Integer> sizeMap;

        public UnionFind() {
            this.fatherMap = new HashMap<>();
            this.sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node n : nodes) {
                fatherMap.put(n, n);
                sizeMap.put(n, 1);
            }
        }

        public Node findFather(Node node) {
            LinkedList<Node> nodes = new LinkedList<>();
            while (node != fatherMap.get(node)) {
                nodes.add(node);
                node = fatherMap.get(node);
            }

            while (!nodes.isEmpty()) {
                fatherMap.put(nodes.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(Node o1, Node o2) {
            return fatherMap.get(o1) == fatherMap.get(o2);
        }

        public void unionSet(Node o1, Node o2) {
            if (fatherMap.get(o1) == fatherMap.get(o2)) {
                return;
            }
            Node f1 = fatherMap.get(o1);
            Node f2 = fatherMap.get(o2);

            if (sizeMap.get(f1) > sizeMap.get(f2)) {
                fatherMap.put(f2, f1);
                sizeMap.put(f1, sizeMap.get(f1) + sizeMap.get(f2));
                sizeMap.remove(f2);
            } else {
                fatherMap.put(f1, f2);
                sizeMap.put(f2, sizeMap.get(f1) + sizeMap.get(f2));
                sizeMap.remove(f1);
            }
        }
    }

    public Set<Edge> kruskal(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
        HashSet<Edge> edges = new HashSet<>();

        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();

            if (!unionFind.isSameSet(edge.from, edge.to)) {
                edges.add(edge);
                unionFind.unionSet(edge.from, edge.to);
            }
        }
        return edges;
    }
}
