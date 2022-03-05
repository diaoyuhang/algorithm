package basicClass.class07;

import java.util.LinkedList;
import java.util.Queue;

public class Code04_SerializeAndReconstructTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Queue<String> preSerial(Node head) {
        LinkedList<String> queue = new LinkedList<>();
        pre(head, queue);
        return queue;
    }

    private static void pre(Node head, LinkedList<String> queue) {
        if (head == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(head.value));
            pre(head.left, queue);
            pre(head.right, queue);
        }
    }

    public static Node buildByPreQueue(Queue<String> queue) {
        String value = queue.poll();
        if (value == null) {
            return null;
        }

        Node head = new Node(Integer.parseInt(value));
        head.left = buildByPreQueue(queue);
        head.right = buildByPreQueue(queue);
        return head;
    }

    public static Queue<String> levelSerial(Node head) {
        LinkedList<String> queue = new LinkedList<>();
        level(head, queue);

        return queue;
    }

    private static void level(Node head, LinkedList<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            LinkedList<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                    ans.add(String.valueOf(node.left.value));
                } else {
                    ans.add(null);
                }

                if (node.right != null) {
                    queue.add(node.right);
                    ans.add(String.valueOf(node.right.value));
                } else {
                    ans.add(null);
                }
            }
        }
    }

    public static Node buildByLevelQueue(Queue<String> queue) {
        String value = queue.poll();
        if (value == null) {
            return null;
        }
        LinkedList<Node> help = new LinkedList<>();
        Node ans = new Node(Integer.parseInt(value));
        help.add(ans);

        while (!help.isEmpty()) {
            Node head = help.poll();

            String left = queue.poll();
            String right = queue.poll();

            if (left != null) {
                Node node = new Node(Integer.parseInt(left));
                head.left = node;
                help.add(node);
            }

            if (right != null) {
                Node node = new Node(Integer.parseInt(right));
                head.right = node;
                help.add(node);
            }
        }
        return ans;
    }
}
