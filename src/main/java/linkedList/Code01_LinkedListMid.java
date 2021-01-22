package linkedList;

/**
 * @author: Mr.diao
 * @date: 17:25 2020/12/28
 */
public class Code01_LinkedListMid {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node5.next = node6;
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;

        Node node = midOrDownMidNode(node1);
        System.out.println(node.value);
    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }

    }

    public static Node midOrUpMidNode(Node header) {
        if (header == null || header.next == null || header.next.next == null) {
            return header;
        }
        Node slow = header.next;
        Node fast = header.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node midOrDownMidNode(Node header) {
        if (header == null || header.next == null) {
            return header;
        }

        Node slow = header.next;
        Node fast = header.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
