package binarytree;

import java.util.ArrayList;

/**
 * @Author: Mr.diao
 * @Description:最大搜索子树的头结点
 * @Date: 9:52 2021/1/31
 */
public class Code13_MaxSubBSTHead {
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
            if (getMaxSubBSTHeader1(head) != getMaxSubBSTHeader2(head)) {
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

    public static Integer getBSTSize(Node header) {
        if (header == null) {
            return 0;
        }
        ArrayList<Node> nodes = new ArrayList<>();
        process(header, nodes);

        for (int i = 1; i < nodes.size(); i++) {
            if (nodes.get(i).value <= nodes.get(i - 1).value) {
                return 0;
            }
        }

        return nodes.size();
    }

    private static void process(Node header, ArrayList<Node> nodes) {
        if (header == null) {
            return;
        }
        process(header.left, nodes);
        nodes.add(header);
        process(header.right, nodes);
    }

    public static Node getMaxSubBSTHeader1(Node header) {
        if (header == null) {
            return null;
        }
        Integer size = getBSTSize(header);
        //如果当前size！=0，说明当前头结点是二叉搜索树
        if (size != 0) {
            return header;
        }
        Node left = getMaxSubBSTHeader1(header.left);
        Node right = getMaxSubBSTHeader1(header.right);
        return getBSTSize(left) >= getBSTSize(right) ? left : right;
    }


    public static class Info {
        public Boolean isBST;
        public Integer min;
        public Integer max;
        public Integer size;
        public Node node;

        public Info(Boolean isBST, Integer min, Integer max, Integer size, Node node) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.size = size;
            this.node = node;
        }
    }

    public static Node getMaxSubBSTHeader2(Node header) {
        if (header == null) {
            return null;
        }
        return process2(header).node;
    }

    private static Info process2(Node header) {
        if (header == null) {
            return null;
        }
        Info left = process2(header.left);
        Info right = process2(header.right);

        int min = header.value;
        int max = header.value;
        int maxSize = 0;
        Node maxHeader = null;
        if (left != null) {
            min = Math.min(min, left.min);
            max = Math.max(max, left.max);
            if (maxSize < left.size) {
                maxSize = left.size;
                maxHeader = left.node;
            }
        }
        if (right != null) {
            min = Math.min(min, right.min);
            max = Math.max(max, right.max);
            if (maxSize < right.size) {
                maxSize = right.size;
                maxHeader = right.node;
            }
        }
        //判断当前节点是否是二叉搜索树
        if ((left == null ? true : (left.isBST && left.max < header.value ))
                && (right == null ? true : (right.isBST && right.min > header.value ))) {
            maxSize = (left == null ? 0 : left.size) + (right == null ? 0 : right.size) + 1;
            return new Info(true, min, max, maxSize, header);
        }
        return new Info(false, min, max, maxSize, maxHeader);
    }
}
