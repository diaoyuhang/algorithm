package basicClass.class08;

/**
 * 二叉树中最大的二叉搜索子树的size和头节点
 */
public class Code04_MaxSubBSTSizeAndHead {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        public int size;
        public boolean isBst;
        public int max;
        public int min;
        public Node head;

        public Info(int size, boolean isBst, int max, int min, Node head) {
            this.size = size;
            this.isBst = isBst;
            this.max = max;
            this.min = min;
            this.head = head;
        }
    }

    public static int getMaxSubBSTSize(Node head) {
        if (head == null) {
            return 0;
        }

        Info info = process(head);
        return info.size;
    }

    private static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        Info info = new Info(1, true, head.value, head.value, head);
        if (leftInfo != null) {
            info.isBst = info.isBst && leftInfo.isBst && head.value > leftInfo.max ? true : false;
        }

        if (rightInfo != null) {
            info.isBst = info.isBst && rightInfo.isBst && head.value < rightInfo.min ? true : false;
        }

        if (info.isBst) {
            if (leftInfo != null) {
                info.min = Math.min(leftInfo.min, info.min);
                info.max = Math.max(leftInfo.max, info.max);
                info.size += leftInfo.size;
            }

            if (rightInfo != null) {
                info.min = Math.min(rightInfo.min, info.min);
                info.max = Math.max(rightInfo.max, info.max);
                info.size += rightInfo.size;
            }
        } else {
            if (leftInfo != null) {
                info.size = leftInfo.size;
                info.max = leftInfo.max;
                info.min = leftInfo.min;
                info.head = leftInfo.head;
            }
            if (rightInfo != null && rightInfo.size >= info.size) {
                info.size = rightInfo.size;
                info.max = rightInfo.max;
                info.min = rightInfo.min;
                info.head = rightInfo.head;
            }
        }
        return info;
    }
}
