package binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 21:00 2021/2/9
 */
public class Code15_lowestAncestor {
    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node lowestAncestor1(Node head, Node n1, Node n2) {
        if (head == null) {
            return null;
        }

        HashMap<Node, Node> parentNodeMap = new HashMap<>();
        parentNodeMap.put(head, null);
        //将每个结点的父节点填充到map中
        fillParentMap(head, parentNodeMap);
        //用来存储n1的父结点有哪些
        HashSet<Node> parentSet = new HashSet<>();
        Node cur = n1;
        parentSet.add(cur);
        while (parentNodeMap.get(cur) != null) {
            cur = parentNodeMap.get(cur);
            parentSet.add(cur);
        }

        cur = n2;
        //如果你n1的父节点结合中包含了cur，那么最低公共祖先就是cur
        while (!parentSet.contains(cur)) {
            //如果不包含cur则继续向上找
            Node node = parentNodeMap.get(cur);
            cur = node;
        }
        return cur;
    }

    private static void fillParentMap(Node head, HashMap<Node, Node> parentNodeMap) {
        if (head.left != null) {
            parentNodeMap.put(head.left, head);
            fillParentMap(head.left, parentNodeMap);
        }
        if (head.right != null) {
            parentNodeMap.put(head.right, head);
            fillParentMap(head.right, parentNodeMap);
        }
    }

    public static class Info {
        public Node ans;
        public boolean findO1;
        public boolean findO2;

        public Info(Node ans, boolean findO1, boolean findO2) {
            this.ans = ans;
            this.findO1 = findO1;
            this.findO2 = findO2;
        }
    }

    public static Node lowestAncestor2(Node header, Node o1, Node o2) {
        if (header == null) {
            return null;
        }
        return process(header, o1, o2).ans;
    }

    private static Info process(Node header, Node o1, Node o2) {
        if (header == null) {
            return new Info(null, false, false);
        }
        Info leftInfo = process(header.left, o1, o2);
        Info rightInfo = process(header.right, o1, o2);

        boolean find01 = header == o1 || leftInfo.findO1 || rightInfo.findO1;
        boolean find02 = header == o2 || leftInfo.findO2 || rightInfo.findO2;
        //记录公共最低祖先
        Node ans = null;

        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        }
        if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        }
        //如果当前公共最低祖先还没找到
        if (ans == null) {
            //判断当前节点的左右子树有没有找到o1 o2
            if (find01 && find02) {
                ans = header;
            }
        }
        return new Info(ans, find01, find02);
    }
}
