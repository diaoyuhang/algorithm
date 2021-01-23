package binarytree;

/**
 * @Author: Mr.diao
 * @Description: 是否为满二叉树
 * @Date: 15:50 2021/1/23
 */
public class Code10_IsFull {
    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isFull1(head) != isFull2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        int height = h(head);
        int nodes = n(head);
        return (1 << height) - 1 == nodes;
    }

    //求二叉树节点数
    private static int n(Node head) {
        if (head == null) {
            return 0;
        }
        int left = n(head.left);
        int right = n(head.right);

        return left + right + 1;
    }

    //求二叉树高度
    private static int h(Node head) {
        if (head == null) {
            return 0;
        }
        int left = h(head.left);
        int right = h(head.right);

        return Math.max(left, right) + 1;
    }

    public static class Info {
        public Integer height;
        public Integer nodes;

        public Info(Integer height, Integer nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static Boolean isFull2(Node head) {
        if (head == null) {
            return true;
        }
        Info result = process(head);
        return (1 << result.height) - 1 == result.nodes;
    }

    private static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }

        Info left = process(head.left);
        Info right = process(head.right);

        return new Info(Math.max(left.height, right.height) + 1, left.nodes + right.nodes + 1);
    }
}
