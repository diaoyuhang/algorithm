package linkedList;

/**
 * @author: Mr.diao
 * @date: 15:19 2020/12/29
 */
public class Code03_SmallerEqualBigger {
    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        Node node = listPartition2(head1, 5);
        while (node != null) {
            System.out.print(node.value);
            node = node.next;
        }
    }

    public static Node listPartition2(Node header, int pivot) {
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;

        Node cur = header;
        while (cur != null) {
            Node next = cur.next;
            cur.next = null;
            if (cur.value < pivot) {
                if (sH == null) {
                    sH = cur;
                } else {
                    sT.next = cur;
                }
                sT = cur;
            } else if (cur.value == pivot) {
                if (eH == null) {
                    eH = cur;
                } else {
                    eT.next = cur;
                }
                eT = cur;
            } else {
                if (mH == null) {
                    mH = cur;
                } else {
                    mT.next = cur;
                }
                mT = cur;
            }
            cur = next;
        }
        if (sT != null) {
            sT.next = eH;
            eT = eT != null ? eT : sT;
        }

        if (eT != null) {
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
    }

    public static Node listPartition1(Node header, int pivot) {
        if (header == null) {
            return header;
        }
        Node cur = header;
        int num = 0;
        while (cur != null) {
            num++;
            cur = cur.next;
        }
        Node[] nodes = new Node[num];
        cur = header;
        int index = 0;
        while (cur != null) {
            nodes[index] = cur;
            cur = cur.next;
            index++;
        }

        arrPartition(nodes, pivot);
        header = nodes[0];
        cur = header;
        for (int i = 1; i < nodes.length; i++) {
            cur.next = nodes[i];
            cur = cur.next;
        }
        cur.next = null;
        return header;
    }

    private static void arrPartition(Node[] nodes, int pivot) {
        int smaller = -1;
        int index = 0;
        int big = nodes.length;
        while (index != big) {
            if (nodes[index].value < pivot) {
                swap(nodes, ++smaller, index++);
            } else if (nodes[index].value == pivot) {
                index++;
            } else {
                swap(nodes, --big, index);
            }
        }
    }

    private static void swap(Node[] nodes, int smaller, int index) {
        Node temp = nodes[smaller];
        nodes[smaller] = nodes[index];
        nodes[index] = temp;
    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
}
