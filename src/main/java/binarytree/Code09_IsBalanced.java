package binarytree;

/**
 * @author: Mr.diao
 * @date: 14:08 2021/1/12
 */
public class Code09_IsBalanced {
    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

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

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        int i = process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null)
            return -1;
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static class Info {
        public Boolean flag;
        public Integer height;

        public Info(Boolean f, Integer height) {
            this.flag = f;
            this.height = height;
        }
    }

    public static Boolean isBalanced2(Node head) {
        return process2(head).flag;
    }

    private static Info process2(Node head) {
        if (head == null) {
            return new Info(true, 0);
        }
        Info resultInfo1 = process2(head.left);
        Info resultInfo2 = process2(head.right);
        Integer height = Math.max(resultInfo1.height, resultInfo2.height) + 1;
        if (!resultInfo1.flag || !resultInfo2.flag || Math.abs(resultInfo1.height - resultInfo2.height) > 1) {
            return new Info(false, height);
        }
        return new Info(true, height);

    }
}
