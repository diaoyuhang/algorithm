package 训练营3;

/**
 * LRU内存替换算法的实现
 *
 * @author: Mr.diao
 * @date: 10:36 2021/6/17
 */
public class Code32_LeastRecentlyUsedCache {
    public static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> lastNode;
        public Node<K, V> nextNode;
    }

    public static class NodeDoubleLinkedList<K, V> {
        public Node<K, V> head;
        public Node<K, V> tail;

        //添加节点到末尾
        public void add(Node<K, V> node) {
            if (head == null) {
                head = node;
            } else {
                tail.nextNode = node;
                node.lastNode = tail;
            }
            tail = node;
        }

        //将节点移动到末尾
        public void moveNodeToTail(Node<K, V> node) {
            if (node == tail) {
                return;
            }

            if (node == this.head) {
                Node<K, V> nextNode = head.nextNode;
                head.lastNode = tail;
                tail.nextNode = head;
                head.nextNode = null;
                tail = head;
                head = nextNode;
            } else {
                Node<K, V> lastNode = node.lastNode;
                Node<K, V> nextNode = node.nextNode;
                lastNode.nextNode = nextNode;
                nextNode.lastNode = lastNode;

                tail.nextNode = node;
                node.lastNode = tail;
                tail = node;
            }
        }

        public Node<K, V> removeHeader() {
            if (this.head == null) {
                return null;
            }
            Node<K, V> res = this.head;
            if (this.head == this.tail) {
                this.head = null;
                this.tail = null;
            } else {
                Node<K, V> nextNode = this.head.nextNode;
                nextNode.lastNode = null;
                this.head.nextNode = null;
                this.head = nextNode;
            }
            return res;
        }
    }
}
