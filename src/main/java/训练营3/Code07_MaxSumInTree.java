package 训练营3;

/**
 * 给定一个二叉树的头节点head，路径的规定有以下三种不同的规定：
 * 1）路径必须是头节点出发，到叶节点为止，返回最大路径和
 * 2）路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和
 * 3）路径可以从任何节点出发，到任何节点，返回最大路径和
 *
 * @Author: Mr.diao
 * @Description:
 * @Date: 17:15 2021/5/2
 */
public class Code07_MaxSumInTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            value = val;
        }

    }

    public static int maxPath1(Node head) {
        if (head == null) {
            return 0;
        }
        return process1(head);
    }

    private static int process1(Node head) {
        if (head.left == null && head.right == null) {
            return head.value;
        }
        int res = head.value;
        int subValue = 0;
        if (head.left != null) {
            subValue = process1(head.left);
        }
        if (head.right != null) {
            subValue = Math.max(subValue, process1(head.right));
        }

        return res + subValue;
    }

    public static int maxPath2(Node head) {
        if (head == null) {
            return 0;
        }
        return process2(head);
    }

    private static int process2(Node head) {
        if (head.left == null && head.right == null) {
            return head.value;
        }

        int res = head.value;
        int subValue = 0;
        if (head.left != null) {
            int left = process2(head.left);
            subValue = left > 0 ? left : subValue;
        }
        if (head.right != null) {
            int right = process2(head.right);
            subValue = right > 0 ? right : subValue;
        }

        return res + subValue;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(1);
        head.left.right = new Node(-5);
        head.right = new Node(7);
        head.right.left = new Node(-3);
//        System.out.println(maxPath1(head));
        System.out.println(maxPath2(head));
//        System.out.println(maxSumRecursive(head));
//        System.out.println(maxSumUnrecursive(head));

    }
}
