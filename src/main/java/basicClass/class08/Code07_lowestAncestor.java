package basicClass.class08;

/**
 *
 * @author: diaoyuhang
 * @date 2022/3/7
 */
public class Code07_lowestAncestor {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info{
        public Node node;
        public boolean findO1;
        public boolean find02;

        public Info(Node node, boolean findO1, boolean find02) {
            this.node = node;
            this.findO1 = findO1;
            this.find02 = find02;
        }
    }

    public static Node findLowestAncestor(Node node,Node o1,Node o2){
        if (node==null){
            return null;
        }
        return process(node,o1,o2).node;
    }

    private static Info process(Node node, Node o1, Node o2) {
        if (node==null){
            return new Info(null,false,false);
        }

        Info leftInfo = process(node.left, o1, o2);
        Info rightInfo = process(node.right, o1, o2);

        return null;
    }
}
