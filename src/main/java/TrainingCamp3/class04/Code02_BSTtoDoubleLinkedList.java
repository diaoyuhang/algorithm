package TrainingCamp3.class04;

public class Code02_BSTtoDoubleLinkedList {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        public Node start;
        public Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Node convert(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).start;
    }

    private static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        Info info = new Info(head, head);
        if (leftInfo != null) {
            leftInfo.end.right = head;
            head.left = leftInfo.end;
            info.start = leftInfo.start;
        }
        if (rightInfo != null) {
            rightInfo.start.left = head;
            head.right = rightInfo.start;
            info.end = rightInfo.end;
        }
        return info;
    }
}
