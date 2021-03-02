package 训练营1.kmp;

import java.util.ArrayList;

/**
 * 给定两棵二叉树的头节点head1和head2
 * 想知道head1中是否有某个子树的结构和head2完全一样
 *
 * @author: Mr.diao
 * @date: 9:23 2021/3/2
 */
public class Code02_TreeEqual {
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

    public static void main(String[] args) {
        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean ans1 = containsTree1(big, small);
            boolean ans2 = containsTree2(big, small);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }

    public static class Node {
        public Integer value;
        public Node left;
        public Node right;

        public Node(Integer v) {
            value = v;
        }
    }

    public static boolean containsTree2(Node head1, Node head2) {
        if (head2 == null) {
            return true;
        }
        if (head1 == null) {
            return false;
        }

        ArrayList<String> h1 = preSerial(head1);
        ArrayList<String> h2 = preSerial(head2);

        ArrayList<Integer> next = getNextArray(h2);

        int x = 0;
        int y = 0;
        while (x < h1.size() && y < h2.size()) {
            if (isEquals(h1.get(x), h2.get(y))) {
                x++;
                y++;
            } else if (y > 0) {
                y = next.get(y);
            } else {
                x++;
            }
        }

        return y == h2.size();
    }

    private static ArrayList<Integer> getNextArray(ArrayList<String> h2) {

        ArrayList<Integer> next = new ArrayList<>();
        next.add(-1);
        if (h2.size() == 1) {
            return next;
        }

        next.add(0);

        int index = 2;
        int cur = 0;
        while (index < h2.size()) {
            if (isEquals(h2.get(cur), (h2.get(index - 1)))) {
                next.add(index, cur + 1);
                cur++;
                index++;
            } else if (cur > 0) {
                cur = next.get(cur);
            } else {
                next.add(index++, 0);
            }
        }

        return next;
    }

    private static Boolean isEquals(String a, String b) {
        if (a == null && b == null) {
            return true;
        } else {
            if (a == null || b == null) {
                return false;
            } else {
                return a.equals(b);
            }
        }
    }

    private static ArrayList<String> preSerial(Node head) {
        ArrayList<String> res = new ArrayList<>();
        pre(head, res);
        return res;
    }

    private static void pre(Node head, ArrayList<String> res) {
        if (head == null) {
            return;
        }
        res.add(head.value.toString());
        if (head.left == null) {
            res.add(null);
        }
        pre(head.left, res);

        if (head.right == null) {
            res.add(null);
        }
        pre(head.right, res);
    }

    public static boolean containsTree1(Node big, Node small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        if (isSameValueStructure(big, small)) {
            return true;
        }
        return containsTree1(big.left, small) || containsTree1(big.right, small);
    }

    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }
}
