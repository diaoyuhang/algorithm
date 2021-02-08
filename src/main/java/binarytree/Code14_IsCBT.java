package binarytree;

import java.util.LinkedList;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 21:05 2021/2/8
 */
public class Code14_IsCBT {
    public static void main(String[] args) {

    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        //用来判断是否是叶子节点
        boolean leaf = false;
        queue.add(head);
        while (!queue.isEmpty()) {
            Node pop = queue.pop();
            Node l = pop.left;
            Node r = pop.right;

            //第一种情况：如果当前节点不是叶子节点,左节点=null，右节点！=null，就返回不是完全二叉树
            //第二种情况：如果放电节点是叶子节点，那左右节点都应该为null，否则就返回false
            if ((l == null && r != null) || (leaf && !(l == null && r == null))) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }

            if (l == null && r == null) {
                leaf = true; //如果左右的节点都为null，就表示当前节点及以后的节点都应是叶子节点
            }
        }
        return true;
    }


    public static Boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }

        return process(head).isCBT;
    }

    private static Info process(Node head) {
        if (head == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        Boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        Integer height = Math.max(leftInfo.height, rightInfo.height) + 1;
        Boolean isCBT = false;

        if (isFull) {
            isCBT = true;
        } else {
            if (leftInfo.isCBT && rightInfo.isCBT) {
                if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                    isCBT = true;
                }
                if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
            }

        }

        return new Info(isFull, isCBT, height);
    }

    public static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean full, boolean cbt, int h) {
            isFull = full;
            isCBT = cbt;
            height = h;
        }
    }
}
