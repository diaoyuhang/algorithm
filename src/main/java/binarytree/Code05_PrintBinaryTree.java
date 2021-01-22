package binarytree;

/**
 * @author: Mr.diao
 * @date: 11:38 2021/1/5
 */
public class Code05_PrintBinaryTree {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        printTree(head);
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void printTree(Node header) {
        printInOrder(header, 0, "H", 17);
    }

    public static void printInOrder(Node header, int height, String direction, int len) {
        if (header == null) {
            return;
        }
        printInOrder(header.right, height + 1, "r", len);
        String value = direction + header.value + direction;
        int preLen = (len - value.length()) / 2;
        String preSpace = getSpace(preLen);
        value = preSpace + value + getSpace(len - preLen - value.length());
        System.out.println(getSpace(height * len) + value);
        printInOrder(header.left, height + 1, "l", len);
    }

    private static String getSpace(int preLen) {
        StringBuilder builder = new StringBuilder();
        String space = " ";
        for (int i = 0; i < preLen; i++) {
            builder.append(space);
        }

        return builder.toString();
    }

}
