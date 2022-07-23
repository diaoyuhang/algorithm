package TrainingCamp3.class05;

public class Code03_CompleteTreeNodeNumber {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head);

    }

    private static int process(Node head) {
        if (head.right == null && head.left == null) {
            return 1;
        }

        int len = leftTreeLen(head);
        int rightLen = leftTreeLen(head.right);

        if (rightLen + 1 == len) {
            return 1 << (len - 1) + process(head.right);
        } else {
            return 1 << (rightLen) + process(head.left);
        }
    }

    private static int leftTreeLen(Node head) {
        int num = 0;
        while (head != null) {
            head = head.left;
            num++;
        }
        return num;
    }
}
