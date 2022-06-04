package TrainingCamp2.class03;

public class AbstractBinarySearchTree {
    public Node root;

    protected int size;

    public Node searchNode(int value) {
        Node searchNode = root;
        while (searchNode != null) {
            if (searchNode.value == value) {
                break;
            }

            if (searchNode.value > value) {
                searchNode = searchNode.left;
            } else {
                searchNode = searchNode.right;
            }
        }
        return searchNode;
    }

    public Node delete(int value) {
        Node node = searchNode(value);
        if (node != null) {
            deleteNode(node);
        }
        return node;
    }

    private void deleteNode(Node node) {
        if (node != null) {

            if (node.left == null) {
                transplant(node, node.right);
            } else if (node.right == null) {
                transplant(node, node.left);
            } else {
                Node successorNode = getMinimum(node.right);
                if (successorNode.parent != node) {
                    transplant(successorNode, successorNode.right);
                    successorNode.right = node.right;
                    successorNode.right.parent = successorNode;
                }
                transplant(node, successorNode);
                successorNode.left = node.left;
                successorNode.left.parent = successorNode;
            }
            size--;
        }
    }

    private Node getMinimum(Node cur) {
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    private void transplant(Node node, Node transplantNode) {
        if (node.parent == null) {
            root = transplantNode;
        } else if (node == node.parent.left) {
            node.parent.left = transplantNode;
        } else {
            node.parent.right = transplantNode;
        }

        if (transplantNode != null) {
            transplantNode.parent = node.parent;
        }
    }

    public Node insert(int value) {
        if (root == null) {
            root = createNode(value, null, null, null);
            size++;
            return root;
        }

        Node searchTempNode = root;
        Node insertParentNode = null;
        while (searchTempNode != null) {
            insertParentNode = searchTempNode;
            if (searchTempNode.value > value) {
                searchTempNode = searchTempNode.left;
            } else {
                searchTempNode = searchTempNode.right;
            }
        }

        Node createNode = createNode(value, insertParentNode, null, null);
        if (insertParentNode.value > value) {
            insertParentNode.left = createNode;
        } else {
            insertParentNode.right = createNode;
        }
        size++;
        return createNode;
    }

    private Node createNode(int value, Node parent, Node left, Node right) {
        return new Node(value, parent, left, right);
    }

    public static class Node {
        public Node(Integer value, Node parent, Node left, Node right) {
            super();
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public Integer value;
        public Node parent;
        public Node left;
        public Node right;

        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (!value.equals(other.value))
                return false;
            return true;
        }

    }
}
