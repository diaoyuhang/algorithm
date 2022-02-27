package basicClass.class06;

public class Code01_LinkedListMid {
    public static class Node {
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

    //输入链表头节点，奇数长度返回中点，偶数长度返回上中点
    public static Node midOrUpMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //输入链表头节点，奇数长度返回中点，偶数长度返回下中点
    public static Node midOrDownMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
    public static Node midUpOrBeforeMidUpNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next.next;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
