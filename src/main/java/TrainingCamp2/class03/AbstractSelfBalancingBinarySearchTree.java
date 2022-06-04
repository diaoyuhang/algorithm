package TrainingCamp2.class03;

public abstract class AbstractSelfBalancingBinarySearchTree extends AbstractBinarySearchTree {

    protected Node rotateLeft(Node node) {
        Node temp = node.right;
        temp.parent = node.parent;

        temp.left = node;
        node.parent = temp;

        node.right = temp.left;
        if (node.right != null) {
            node.right.parent = node;
        }

        if (temp.parent != null) {
            if (temp.parent.left == node) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }

        return temp;
    }

    protected Node rotateRight(Node node) {
        Node temp = node.left;
        temp.parent = node.parent;

        temp.right = node;
        node.parent = temp;
        node.left = temp.right;

        if (node.left != null) {
            node.left.parent = node;
        }

        if (temp.parent != null) {
            if (temp.parent.left == node) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
        return temp;
    }
}
