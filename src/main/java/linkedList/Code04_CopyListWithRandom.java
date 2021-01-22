package linkedList;

import java.util.HashMap;

/**
 * @author: Mr.diao
 * @date: 9:28 2021/1/4
 */
public class Code04_CopyListWithRandom {

    public static void main(String[] args) {
        Node head = null;
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        Node node = copyListWithRand2(head);
        printRandLinkedList(node);
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node copyListWithRand1(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            Node newNode = map.get(cur);
            newNode.next = map.get(cur.next);
            newNode.rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;

        //1->2->3
        //1->1'->2->2'->3->3'
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;

            cur = next;
        }
        cur = head;
        next = null;
        //给rand变量赋值
        while (cur != null) {
            next = cur.next.next;
            Node copyNode = cur.next;
            copyNode.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }

        Node res = head.next;
        cur = head;
        //给next变量赋值
        while (cur != null) {
            next = cur.next.next;
            Node copyNode = cur.next;
            cur.next = next;
            copyNode.next = next != null ? next.next : null;

            cur = next;
        }
        return res;
    }
}
