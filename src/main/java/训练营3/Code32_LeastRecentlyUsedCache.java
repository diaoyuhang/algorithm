package 训练营3;

import java.util.HashMap;
import java.util.Map;

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

    public static class Cache<K, V> {
        NodeDoubleLinkedList<K, V> lru = null;
        Map<K, Node<K, V>> map = null;
        int size = 10;
        int cur = 0;

        public Cache(int size) {
            lru = new NodeDoubleLinkedList<>();
            map = new HashMap<>();
            this.size = size;
        }

        public V get(K key) {
            if (map.containsKey(key)) {
                Node<K, V> node = map.get(key);
                lru.moveNodeToTail(node);
                return node.value;
            }
            return null;
        }

        public void set(K key, V value) {
            if (map.containsKey(key)) {
                Node<K, V> node = map.get(key);
                node.value = value;
                lru.moveNodeToTail(node);
            } else {
                if (cur == size) {
                    lru.removeHeader();
                    cur--;
                }
                Node<K, V> node = new Node<>();
                node.key = key;
                node.value = value;
                map.put(key, node);
                lru.add(node);
                cur++;
            }

        }
    }
}
