package basicClass.class07;

public class Code05_PrintBinaryTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        printTree(head);

        head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        head.left.left.right = new Node(1);
        printTree(head);
    }

    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    private static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }

        printInOrder(head.right, height + 1, "R", len);

        String val = to + head.value + to;
        Integer leftL = (len - val.length()) / 2;
        Integer rightL = len - val.length() - leftL;
        val = getSpace(leftL) + val + getSpace(rightL);

        System.out.println(getSpace(len * height) + val);
        printInOrder(head.left, height + 1, "L", len);

    }

    private static String getSpace(Integer len) {
        StringBuilder builder = new StringBuilder();
        while (len > 0) {
            builder.append(" ");
            len--;
        }
        return builder.toString();
    }

}
