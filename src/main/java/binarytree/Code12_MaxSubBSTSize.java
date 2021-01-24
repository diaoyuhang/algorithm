package binarytree;

import java.util.ArrayList;

/**
 * @Author: Mr.diao
 * @Description:最大搜索子树
 * @Date: 10:32 2021/1/24
 */
public class Code12_MaxSubBSTSize {
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
            if (getMaxSubBST1(head) != getMaxSubBST2(head)) {
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

    public static Integer getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> nodes = new ArrayList<>();
        process1(head, nodes);
        //判断当前这颗二叉树是不是搜索子树，如果不是就直接返回0
        for (int i = 1; i < nodes.size(); i++) {
            if (nodes.get(i).value <= nodes.get(i - 1).value) {
                return 0;
            }
        }
        //如果是搜索数，就返回该树的节点数
        return nodes.size();
    }

    private static void process1(Node head, ArrayList<Node> nodes) {
        if (head == null) {
            return;
        }
        process1(head.left, nodes);
        nodes.add(head);
        process1(head.right, nodes);
    }

    public static Integer getMaxSubBST1(Node head) {
        if (head == null) {
            return 0;
        }
        Integer size = getBSTSize(head);
        //size==0说明当前头节点的树不是二叉搜索子树
        if (size != 0) {
            return size;
        }
        //递归分别向左右两颗树探索，然后取最大的向上返回
        return Math.max(getMaxSubBST1(head.left), getMaxSubBST1(head.right));
    }

    public static class Info {
        public Boolean isBST;
        public Integer max;
        public Integer min;
        public Integer maxSize;

        public Info(Boolean isBST, Integer max, Integer min, Integer maxSize) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
            this.maxSize = maxSize;
        }

    }

    public static Integer getMaxSubBST2(Node head) {
        if (head == null) {
            return 0;
        }
        return process2(head).maxSize;
    }

    private static Info process2(Node head) {
        if (head == null) {
            return null;
        }
        Info left = process2(head.left);
        Info right = process2(head.right);

        //max min记录当前树的最大值，最小值
        int max = head.value;
        int min = head.value;
        int maxSize = 0;
        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
            maxSize = Math.max(maxSize, left.maxSize);
        }

        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
            maxSize = Math.max(maxSize, right.maxSize);
        }
        //判断当前节点是否为二叉搜索子树
        if ((left == null ? true : (left.isBST && left.max < head.value))
                && (right == null ? true : (right.isBST && right.min > head.value))) {
            //判断如果左右子树为null的情况
            maxSize = (left == null ? 0 : left.maxSize) + (right == null ? 0 : right.maxSize) + 1;
            return new Info(true, max, min, maxSize);
        }
        return new Info(false, max, min, maxSize);
    }
}
