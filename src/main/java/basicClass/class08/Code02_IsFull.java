package basicClass.class08;

/**
 * 是否满二叉树
 */
public class Code02_IsFull {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        public int num;
        public int height;

        public Info(int num, int height) {
            this.num = num;
            this.height = height;
        }
    }

    public static boolean isFull(Node head) {
        if (head == null) {
            return false;
        }
        Info info = process(head);
        return Math.pow(2, info.height) - 1 == info.num;
    }

    private static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int num = leftInfo.num + rightInfo.num + 1;
        int height = (leftInfo.height > rightInfo.height ? leftInfo.height : rightInfo.height) + 1;

        return new Info(num, height);
    }
}
