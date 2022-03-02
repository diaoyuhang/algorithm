package basicClass.class06;

public class Code04_CopyListWithRandom {

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node copyListWithRand(Node head) {
        if (head == null) {
            return head;
        }

        Node cur = head;
        Node next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }

        cur = head;
        Node copyNode = null;

        while (cur != null) {
            copyNode = cur.next;
            copyNode.rand = cur.rand != null ? cur.rand.next : null;

            cur = cur.next.next;
        }

        Node newHead = head.next;
        cur = head;
        next = null;

        while (cur != null) {
            next = cur.next.next;
            copyNode = cur.next;
            cur.next = next;
            copyNode.next = next != null ? next.next : null;
            cur = next;
        }
        return newHead;
    }
}
