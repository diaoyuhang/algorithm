package basicClass.class10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Code01_UnionFind {

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }


    public static class UnionSet<T> {
        public Map<T, Node<T>> nodesMap;
        public Map<Node<T>, Node<T>> parentMap;
        public Map<Node<T>, Integer> sizeMap;

        public UnionSet(List<Node<T>> nodes) {
            nodesMap = new HashMap<>();
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();

            for (Node<T> node : nodes) {
                nodesMap.put(node.value, node);
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<T> findFather(Node<T> cur) {
            Stack<Node<T>> stack = new Stack<>();

            while (cur != parentMap.get(cur)) {
                stack.push(cur);
                cur = parentMap.get(cur);
            }

            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(T n1, T n2) {
            if (nodesMap.get(n1) != null && nodesMap.get(n2) != null) {

                return findFather(nodesMap.get(n1)) == findFather(nodesMap.get(n2));
            }
            return false;
        }

        public void union(T v1, T v2) {
            if (!nodesMap.containsKey(v1) || !nodesMap.containsKey(v2)) {
                return;
            }
            Node<T> node1 = findFather(nodesMap.get(v1));
            Node<T> node2 = findFather(nodesMap.get(v2));

            if (node1 != node2) {
                Integer size1 = sizeMap.get(node1);
                Integer size2 = sizeMap.get(node2);

                if (size1 >= size2) {
                    parentMap.put(node2, node1);
                    sizeMap.put(node1, size1 + size2);
                    sizeMap.remove(node2);
                } else {
                    parentMap.put(node1, node2);
                    sizeMap.put(node2, size1 + size2);
                    sizeMap.remove(node1);
                }
            }


        }
    }
}
