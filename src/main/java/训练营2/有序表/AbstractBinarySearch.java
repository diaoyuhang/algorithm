package 训练营2.有序表;

/**
 * 二叉搜索树
 *
 * @author: Mr.diao
 * @date: 10:17 2021/4/21
 */
public class AbstractBinarySearch {
    public Node root;
    public Integer size;

    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new Node(value, parent, left, right);
    }

    public Node search(int element) {
        Node node = root;
        while (node != null) {
            if (node.value < element) {
                node = node.right;
            } else if (node.value > element) {
                node = node.left;
            } else {
                break;
            }
        }
        return node;
    }

    public void insert(int element) {
        if (root == null) {
            root = createNode(element, null, null, null);
            return;
        }

        Node insertParentNode = null;
        Node searchNode = root;
        while (searchNode != null) {
            insertParentNode = searchNode;
            if (insertParentNode.value < element) {
                searchNode = searchNode.right;
            } else {
                searchNode = searchNode.left;
            }
        }
        Node newNode = createNode(element, insertParentNode, null, null);
        if (insertParentNode.value > element) {
            newNode.left = newNode;
        } else {
            newNode.right = newNode;
        }
        size++;
    }

    public Node delete(int value) {
        Node delete = search(value);
        if (delete != null) {
            delete(delete);
        }
        return delete;
    }

    private void delete(Node deleteNode) {
        if (deleteNode != null) {
            if (deleteNode.left == null) {
                transplant(deleteNode, deleteNode.right);
            } else if (deleteNode.right == null) {
                transplant(deleteNode, deleteNode.left);
            } else {
                //找到删除节点的后继节点，
                Node successorNode = getMinNum(deleteNode.right);
                //如果删除节点的后继节点的父节点不是删除节点
                if (successorNode.parent != deleteNode) {
                    transplant(successorNode, successorNode.right);
                    successorNode.right = deleteNode.right;
                    successorNode.right.parent = successorNode;
                }
                transplant(deleteNode, successorNode);
                successorNode.left = deleteNode.left;
                successorNode.left.parent = successorNode;
            }
        }
    }

    private void transplant(Node nodeToPlace, Node newNode) {
        if (nodeToPlace.parent == null) {
            this.root = newNode;
        } else if (nodeToPlace == nodeToPlace.parent.left) {
            nodeToPlace.parent.left = newNode;
        } else {
            nodeToPlace.parent.right = newNode;
        }
        if (newNode != null) {
            newNode.parent = nodeToPlace.parent;
        }
    }

    private Node getMinNum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
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
