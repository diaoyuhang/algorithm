package basicClass.class10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Code06_Dijkstra {

    public static HashMap<Node, Integer> dijkstra1(Node head) {

        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(head, 0);

        HashSet<Node> selectedNode = new HashSet<>();
        Node minNode = getMinDistanceFromUnSelectedNodes(distanceMap, selectedNode);

        while (minNode != null) {
            List<Edge> edges = minNode.edges;
            Integer distance = distanceMap.get(minNode);
            for (Edge edge : edges) {
                Node toNode = edge.to;
                if (distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
                } else {
                    distanceMap.put(toNode, distance + edge.weight);
                }
            }
            selectedNode.add(minNode);
            getMinDistanceFromUnSelectedNodes(distanceMap, selectedNode);
        }
        return distanceMap;
    }

    private static Node getMinDistanceFromUnSelectedNodes(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNode) {
        Node minNode = null;
        Integer minDistance = Integer.MAX_VALUE;

        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node key = entry.getKey();
            Integer distance = entry.getValue();
            if (!selectedNode.contains(key) && distance < minDistance) {
                minDistance = distance;
                minNode = key;
            }
        }

        return minNode;
    }

    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        public Node[] nodes;
        public HashMap<Node, Integer> nodeIndexMap;
        public HashMap<Node, Integer> nodeDistanceMap;
        public int size;

        public NodeHeap(Integer size) {
            this.size = 0;
            nodes = new Node[size];
            nodeIndexMap = new HashMap<>();
            nodeDistanceMap = new HashMap<>();
        }

        public void addOrUpdateInfo(Node node, int distance) {
            if (inHeap(node)) {
                nodeDistanceMap.put(node, Math.min(nodeDistanceMap.get(node), distance));
                //调整堆结构
                insertHeapify(node, nodeIndexMap.get(node));
            }

            if (!nodeIndexMap.containsKey(node)) {
                nodes[size] = node;
                nodeDistanceMap.put(node, distance);
                nodeIndexMap.put(node, size);
                //调整堆结构
                insertHeapify(node, size);
                size++;
            }
        }

        public NodeRecord pop() {
            if (isEmpty()) {
                return null;
            }
            NodeRecord record = new NodeRecord(nodes[0], nodeDistanceMap.get(nodes[0]));
            swap(0, size - 1);
            nodeIndexMap.put(nodes[size - 1], -1);
            nodeDistanceMap.remove(nodes[size - 1]);

            heapify(0, --size);
            return record;
        }

        private void heapify(int index, int size) {
            int left = index * 2 + 1;

            while (left < size) {
                int smallest = left + 1 < size ?
                        (nodeDistanceMap.get(left) < nodeDistanceMap.get(left + 1) ? left : left + 1) : left;
                smallest = nodeDistanceMap.get(smallest) < nodeDistanceMap.get(nodes[index]) ? smallest : index;

                if (smallest == index) {
                    break;
                }

                swap(index, smallest);
                index = smallest;
                left = index * 2 + 1;
            }

        }

        private boolean isEmpty() {
            return size > 0;
        }


        private void insertHeapify(Node node, int index) {
            //向上交换
            while (nodeDistanceMap.get(nodes[(index - 1) / 2]) > nodeDistanceMap.get(node)) {
                swap((index - 1) / 2, index);
                index = (index - 1) / 2;
            }
        }

        private void swap(int index1, int index2) {
            Node temp = nodes[index1];
            nodeIndexMap.put(nodes[index1], index2);
            nodes[index1] = nodes[index2];

            nodeIndexMap.put(nodes[index2], index1);
            nodes[index2] = temp;

        }

        private boolean inHeap(Node node) {
            return nodeIndexMap.containsKey(node) && nodeIndexMap.get(node) > 0;
        }

    }

    public static HashMap<Node, Integer> dijkstra2(Node node, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateInfo(node, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node node1 = record.node;
            int distance = record.distance;
            for (Edge edge : node1.edges) {
                nodeHeap.addOrUpdateInfo(edge.to, distance + edge.weight);
            }
            result.put(node1,distance);
        }
        return result;
    }
}
