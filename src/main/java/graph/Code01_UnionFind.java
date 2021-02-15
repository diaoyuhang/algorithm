package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 10:17 2021/2/15
 */
public class Code01_UnionFind {
    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionSet<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parentNodes;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionSet(List<V> values) {
            for (V v : values) {
                Node node = new Node(v);
                nodes.put(v, node);
                parentNodes.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> stack = new Stack<>();
            //一直找到最上层的结点，最上层的节点的父节点就是自己
            while (cur != parentNodes.get(cur)) {
                stack.push(cur);
                cur = parentNodes.get(cur);
            }
            while (!stack.isEmpty()) {
                //将一路遍历到的结点的父节点全部设置成最上层结点
                parentNodes.put(stack.pop(), cur);
            }
            return cur;
        }

        //判断两个结点是不是属于一个集合
        public boolean isSameSet(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }

            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return;
            }

            Node<V> fatherA = findFather(nodes.get(a));
            Node<V> fatherB = findFather(nodes.get(b));

            if (fatherA != fatherB) {
                Integer aSize = sizeMap.get(fatherA);
                Integer bSize = sizeMap.get(fatherB);
                if (aSize >= bSize) {
                    parentNodes.put(fatherB, fatherA);
                    sizeMap.put(fatherA, aSize + bSize);
                    sizeMap.remove(fatherB);
                } else {
                    parentNodes.put(fatherA, fatherB);
                    sizeMap.put(fatherB, aSize + bSize);
                    sizeMap.remove(fatherA);
                }
            }
        }
    }
}
