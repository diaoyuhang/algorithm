package basicClass.class06;

public class Code02_IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node node = slow.next;
        Node pre = null;
        while (node != null) {
            Node next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }

        Node h1 = pre;
        Node h2 = head;
        boolean res = true;
        while (h1 != null && h2 != null) {
            if (h1.value != h2.value) {
                res = false;
                break;
            }
            h1 = h1.next;
            h2 = h2.next;
        }
        node = pre;
        pre=null;

        while(node !=null){
            Node next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }

        return res;
    }
}
