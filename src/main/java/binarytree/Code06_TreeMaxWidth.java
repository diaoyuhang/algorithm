package binarytree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Mr.diao
 * @date: 11:28 2021/1/11
 */
public class Code06_TreeMaxWidth {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        System.out.println(maxWidthUseMap(head));
        System.out.println(maxWidthUseNoMap(head));
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        HashMap<Node, Integer> level = new HashMap<>();
        queue.add(head);
        level.put(head, 1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            Integer nodeLevel = level.get(node);
            if (node.left != null) {
                queue.add(node.left);
                level.put(node.left, nodeLevel + 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                level.put(node.right, nodeLevel + 1);
            }
            if (nodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    public static int maxWidthUseNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        int curLevelNodes = 0;
        Node curEnd = head;
        Node nextEnd = null;
        int max = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            curLevelNodes++;
            if (node.left != null) {
                queue.add(node.left);
                nextEnd = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextEnd = node.right;
            }
            if (node == curEnd) {
                max = Math.max(max, curLevelNodes);
                curEnd=nextEnd;
                curLevelNodes = 0;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }
}
