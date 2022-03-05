package basicClass.class07;

import java.util.LinkedList;

public class Code06_TreeMaxWidth {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Integer maxWidthNoMap(Node head) {
        if (head == null) {
            return null;
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curLevelEnd = head;
        int max = 0;
        Node nextEnd = null;
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.left != null) {
                queue.add(node.left);
                nextEnd = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextEnd = node.right;
            }
            curLevelNodes++;

            if (node == curLevelEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curLevelEnd = nextEnd;
            }
        }
        return max;
    }
}
