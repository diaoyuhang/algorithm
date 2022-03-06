package basicClass.class08;

/**
 * 是否搜索二叉树
 */
public class Code03_IsBST {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        public boolean isBst;
        public int max;
        public int min;

        public Info(boolean isBst, int max, int min) {
            this.isBst = isBst;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean isBst(Node head) {
        if (head == null) {
            return true;
        }
        Info info = process(head);
        return info.isBst;
    }

    private static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        Info info = new Info(true, head.value, head.value);

        if (leftInfo != null) {
            info.isBst = leftInfo.isBst && (leftInfo.max < head.value);
            info.min = leftInfo.min;
        }
        if (rightInfo != null) {
            info.isBst = rightInfo.isBst && (rightInfo.min > head.value);
            info.max = rightInfo.max;
        }
        return info;
    }
}
