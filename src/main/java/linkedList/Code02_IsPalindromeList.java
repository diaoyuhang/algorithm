package linkedList;

import java.util.Stack;

/**
 * @author: Mr.diao
 * @date: 11:21 2020/12/29
 */
public class Code02_IsPalindromeList {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(3);
        Node node5 = new Node(2);
        Node node6 = new Node(1);
        node5.next = node6;
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;

        System.out.println(isPalindrome3(node1));
    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Boolean isPalindrome1(Node header) {
        if (header == null || header.next == null) {
            return true;
        }

        Stack<Integer> nodes = new Stack<>();

        Node node1 = header;
        nodes.push(header.value);
        while (node1.next != null) {
            node1 = node1.next;
            nodes.push(node1.value);
        }

        while (header != null) {
            if (header.value != nodes.pop()) {
                return false;
            }
            header = header.next;
        }
        return true;
    }

    public static Boolean isPalindrome2(Node header) {
        if (header == null || header.next == null) {
            return true;
        }
        Node right = header.next;
        Node cur = header;
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }
        Stack<Integer> values = new Stack<>();
        while (right != null) {
            values.push(right.value);
            right = right.next;
        }

        while (!values.isEmpty()) {
            if (header.value != values.pop()) {
                return false;
            }
            header = header.next;
        }
        return true;
    }

    public static Boolean isPalindrome3(Node header) {
        if (header == null || header.next == null) {
            return true;
        }

        Node n1 = header;
        Node n2 = header;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next; //此时n2为链表右半部分第一个节点
        n1.next = null;
        Node n3 = null;
        while (n2.next != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }

        n2.next = n1; //n2为最后一个节点
        n1 = header;
        while (n1 != null) {
            if (n1.value != n2.value) {
                return false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        return true;
    }
}
