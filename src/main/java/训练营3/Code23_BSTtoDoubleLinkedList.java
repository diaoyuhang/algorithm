package 训练营3;

/**
 * 双向链表节点结构和二叉树节点结构是一样的，如果你把last认为是left，next认为是next的话。
 * 给定一个搜索二叉树的头节点head，请转化成一条有序的双向链表，并返回链表的头节点。
 *
 * @author: Mr.diao
 * @date: 16:31 2021/5/24
 */
public class Code23_BSTtoDoubleLinkedList {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
    }

    public static class Info {
        public Node start;
        public Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Info convert(Node head) {
        if (head == null) {
            return new Info(null, null);
        }
        Info leftInfo = convert(head.left);
        Info rightInfo = convert(head.right);

        if (leftInfo.end != null) {
            leftInfo.end.right = head;
        }
        head.left = leftInfo.end;
        head.right = rightInfo.start;
        if (rightInfo.start != null) {
            rightInfo.start.left = head;
        }

        return new Info(leftInfo.start != null ? leftInfo.start : head,
                rightInfo.end != null ? rightInfo.end : head);
    }
}
