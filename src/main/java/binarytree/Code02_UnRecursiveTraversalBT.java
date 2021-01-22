package binarytree;

import java.util.Stack;

/**
 * @author: Mr.diao
 * @date: 16:36 2021/1/4
 */
public class Code02_UnRecursiveTraversalBT {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

//        pre(head);
//        in(head);
//        pos(head);
        pos2(head);
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> nodes = new Stack<>();
        nodes.add(head);
        while (!nodes.isEmpty()) {
            Node pop = nodes.pop();
            System.out.println(pop.value);
            if (pop.right != null) {
                nodes.push(pop.right);
            }
            if (pop.left != null) {
                nodes.push(pop.left);
            }
        }
    }

    //中序遍历
    public static void in(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                Node pop = stack.pop();
                System.out.println(pop.value);
                head = pop.right;
            }
        }
    }

    //后序遍历 一个栈
    public static void pos(Node head) {
        if (head == null)
            return;

        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node c = null;
        while (!stack.isEmpty()) {
            c = stack.peek();
            if (c.left != null && c.left != head && c.right != head) {
                stack.push(c.left);
            } else if (c.right != null && c.right != head) {
                stack.push(c.right);
            } else {
                Node pop = stack.pop();
                System.out.println(pop.value);
                head = pop; //这一步很关键，用来标识该节点已经压栈弹出
            }
        }
    }

    //后序遍历 两个栈
    public static void pos2(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            Node pop = stack1.pop();
            stack2.push(pop);
            if (pop.left != null) {
                stack1.push(pop.left);
            }
            if (pop.right != null) {
                stack1.push(pop.right);
            }
        }
        while(!stack2.isEmpty()){
            System.out.println(stack2.pop().value);
        }

    }
}
