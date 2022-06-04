package TrainingCamp2.class03;

public class AVLTree extends AbstractSelfBalancingBinarySearchTree {

    @Override
    public Node insert(int value) {
        Node node = super.insert(value);
        reBalance((AVLNode) node);
        return node;
    }



    private void reBalance(AVLNode node) {
        while (node != null) {
            Node parent = node.parent;

            int leftHeight = node.left == null ? 0 : ((AVLNode) node.left).height;
            int rightHeight = node.right == null ? 0 : ((AVLNode) node.right).height;

            if (leftHeight - rightHeight > 1) {
                //LL型
                if (node.left.left != null) {
                    node = avlRotateRight(node);
                } else { //LR型
                    node = doubleAvlRotateLeftAndRight(node);
                }
            } else if (rightHeight - leftHeight > 1) {
                //RR型
                if (node.right.right != null) {
                    node = avlRotateLeft(node);
                } else {//RL型
                    node = doubleAvlRotateRightAndLeft(node);
                }
            } else {
                updateHeight(node);
            }
            node = (AVLNode) parent;
        }
    }

    private AVLNode doubleAvlRotateRightAndLeft(AVLNode node) {
        node.right = avlRotateRight(node.right);
        return avlRotateLeft(node);
    }

    private AVLNode doubleAvlRotateLeftAndRight(AVLNode node) {
        node.left = avlRotateLeft(node.left);
        return avlRotateRight(node);
    }

    private AVLNode avlRotateLeft(Node node) {
        Node temp = super.rotateLeft(node);
        updateHeight((AVLNode) temp.left);
        updateHeight((AVLNode) temp);
        return (AVLNode) temp;
    }

    private AVLNode avlRotateRight(Node node) {
        Node temp = super.rotateRight(node);
        updateHeight((AVLNode) temp.right);
        updateHeight((AVLNode) temp);
        return (AVLNode) temp;
    }

    private void updateHeight(AVLNode node) {
        int leftHeight = (node.left == null) ? 0 : ((AVLNode) node.left).height;
        int rightHeight = (node.right == null) ? 0 : ((AVLNode) node.right).height;
        node.height = 1 + Math.max(leftHeight, rightHeight);
    }

    protected static class AVLNode extends Node {
        public int height;

        public AVLNode(int value, Node parent, Node left, Node right) {
            super(value, parent, left, right);
        }
    }
}
