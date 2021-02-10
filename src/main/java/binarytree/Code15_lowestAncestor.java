package binarytree;

/**
 * @author: Mr.diao
 * @date: 14:58 2021/2/10
 */
public class Code15_lowestAncestor {
    public static void main(String[] args) {

    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
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
