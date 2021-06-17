package 训练营3;

/**
 * 求完全二叉树节点的个数
 * <p>
 * 要求时间复杂度低于O(N)
 *
 * @author: Mr.diao
 * @date: 9:47 2021/6/17
 */
public class Code31_CompleteTreeNodeNumber {

    public static class Node {
        public Node left;
        public Node right;
        public Integer value;

        public Node(Integer value) {
            this.value = value;
        }
    }

    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftNodeLevel(head, 1));
    }

    private static int bs(Node head, int level, Integer h) {
        if (level == h) {
            return 1;
        }
        //如果当前节点的右节点的最左高度==h，说明当前节点的左边是满二叉树
        if (mostLeftNodeLevel(head.right, level + 1) == h) {
            return (1 << (h - level)) + bs(head.right, level + 1, h);
        } else {
            //当前节点的右节点是满二叉树
            return (1 << (h - level - 1)) + bs(head.left, level + 1, h);
        }
    }

    private static Integer mostLeftNodeLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));

    }
}
