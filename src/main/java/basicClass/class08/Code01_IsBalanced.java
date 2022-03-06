package basicClass.class08;

/**
 * 是否平衡二叉树
 */
public class Code01_IsBalanced {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        public boolean isBalance;
        public int height;

        public Info(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public static boolean isBalanced(Node head) {
        if (head == null) {
            return false;
        }
        Info info = process(head);
        return info.isBalance;
    }

    private static Info process(Node head) {
        if (head == null) {
            return new Info(true, 0);
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        boolean isBalance = Math.abs(leftInfo.height - rightInfo.height) <= 1 && leftInfo.isBalance && rightInfo.isBalance ? true : false;
        int height = (leftInfo.height > rightInfo.height ? leftInfo.height : rightInfo.height) + 1;
        return new Info(isBalance, height);
    }

}
