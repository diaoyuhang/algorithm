package binarytree;

import java.util.ArrayList;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 16:46 2021/1/23
 */
public class Code11_IsBST {
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isBST2(head)) {
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

    public static Boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        process1(head, arr);
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i).value >= arr.get(i + 1).value) {
                return false;
            }
        }
        return true;
    }

    private static void process1(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        process1(head.left, arr);
        arr.add(head);
        process1(head.right, arr);
    }

    public static class Info {
        public Integer min;
        public Integer max;
        public Boolean flag;

        public Info(Integer min, Integer max, Boolean flag) {
            this.min = min;
            this.max = max;
            this.flag = flag;
        }
    }

    public static Boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        return process2(head).flag;
    }

    private static Info process2(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process2(head.left);
        Info rightInfo = process2(head.right);
        int min = head.value;
        int max = head.value;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }

        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        if ((leftInfo == null ? true : (leftInfo.flag && leftInfo.max < head.value))
                && (rightInfo == null ? true : (rightInfo.flag && rightInfo.min > head.value))) {
            return new Info(min, max, true);
        }
        return new Info(min, max, false);

    }
}
