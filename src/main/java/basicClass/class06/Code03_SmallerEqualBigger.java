package basicClass.class06;

public class Code03_SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node listPartition(Node head, int num) {
        if (head == null) {
            return null;
        }

        Node smallStart = null;
        Node smallEnd = null;
        Node equalStart = null;
        Node equalEnd = null;
        Node bigStart = null;
        Node bigEnd = null;

        Node node = head;
        while (node != null) {
            if (node.value == num) {
                if (smallStart == null) {
                    smallStart = node;
                    smallEnd = node;
                } else {
                    smallEnd.next = node;
                    smallEnd = node;
                }
            } else if (node.value < num) {
                if (equalStart == null) {
                    equalStart = node;
                    equalEnd = node;
                } else {
                    equalEnd.next = node;
                    equalEnd = node;
                }
            } else {
                if (bigStart == null) {
                    bigStart = node;
                    bigEnd = node;
                } else {
                    bigEnd.next = node;
                    bigEnd = bigEnd.next;
                }
            }
            node = node.next;
        }

        if (smallEnd != null) {
            smallEnd.next = equalStart;
            equalEnd = equalEnd == null ? smallEnd : equalEnd;
        }

        if (equalEnd != null) {
            equalEnd.next = bigStart;
        }

        return smallStart != null ? smallStart : (equalStart != null ? equalStart : bigStart);
    }

}
