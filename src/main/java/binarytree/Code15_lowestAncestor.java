package binarytree;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 21:00 2021/2/9
 */
public class Code15_lowestAncestor {
    public static void main(String[] args) {

    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node lowestAncestor1(Node head, Node n1, Node n2) {
        if (head == null) {
            return null;
        }

        HashMap<Node, Node> parentNodeMap = new HashMap<>();
        parentNodeMap.put(head, null);
        //将每个结点的父节点填充到map中
        fillParentMap(head, parentNodeMap);
        //用来存储n1的父结点有哪些
        HashSet<Node> parentSet = new HashSet<>();
        Node cur = n1;
        while (parentNodeMap.get(cur) != null) {
            cur = parentNodeMap.get(cur);
            parentSet.add(cur);
        }

        cur = n2;
        //如果你n1的父节点结合中包含了cur，那么最低公共祖先就是cur
        while (!parentSet.contains(cur)) {
            //如果不包含cur则继续向上找
            Node node = parentNodeMap.get(cur);
            cur = node;
        }
        return cur;
    }

    private static void fillParentMap(Node head, HashMap<Node, Node> parentNodeMap) {
        if (head.left != null) {
            parentNodeMap.put(head.left, head);
            fillParentMap(head.left, parentNodeMap);
        }
        if (head.right != null) {
            parentNodeMap.put(head.right, head);
            fillParentMap(head.right, parentNodeMap);
        }
    }
}
