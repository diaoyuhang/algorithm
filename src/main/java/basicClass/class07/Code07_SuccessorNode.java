package basicClass.class07;

public class Code07_SuccessorNode {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }


    public static Node getSuccessorNode(Node node) {

        if (node == null) {
            return node;
        }

        if (node.left != null) {
            return getLeftMost(node);
        } else {
            Node parent = node.parent;
            while (parent != null && parent.left != node) {
                node = parent;
                parent = parent.parent;
            }
            return parent;
        }

    }

    private static Node getLeftMost(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
