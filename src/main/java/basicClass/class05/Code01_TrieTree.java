package basicClass.class05;

import java.util.HashMap;

public class Code01_TrieTree {

    public static void main(String[] args) {
        Trie1 trie1 = new Trie1();
        trie1.insert("abthgwrgec");
        trie1.insert("yhrtgabcderth");
        trie1.insert("abce");
        trie1.insert("taexcgsdfgertbc");
        trie1.pop("taexcgsdfgertbc");
        System.out.println(trie1.search("taexcgsdfgertbc"));
    }

    public static class Node {
        public int pass;
        public int end;
        public Node[] arr;

        public Node() {
            int pass = 0;
            int end = 0;
            arr = new Node[26];
        }
    }

    public static class Node2 {
        public int pass;
        public int end;
        public HashMap<Character, Node2> map;

        public Node2() {
            pass = 0;
            end = 0;
            map = new HashMap<>();
        }
    }

    public static class Trie2 {
        private Node2 node;

        public Trie2() {
            node = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }

            char[] arr = word.toCharArray();
            Node2 nextNode = node;
            nextNode.pass++;
            for (char c : arr) {
                Node2 node = nextNode.map.get(c);
                if (node == null) {
                    nextNode.map.put(c, new Node2());
                }
                nextNode = nextNode.map.get(c);
                nextNode.pass++;
            }
            nextNode.end--;
        }

        public Integer search(String word) {
            if (word == null) {
                return 0;
            }
            char[] arr = word.toCharArray();

            Node2 nextNode = node;

            for (char c : arr) {

                Node2 node2 = node.map.get(c);
                if (node2 == null) {
                    return 0;
                }
                nextNode = node2.map.get(c);
            }
            return nextNode.end;
        }

    }

    public static class Trie1 {
        private Node node;

        public Trie1() {
            node = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] arr = word.toCharArray();
            Node nextNode = node;
            nextNode.pass++;

            for (char c : arr) {
                int index = c - 'a';

                if (nextNode.arr[index] == null) {
                    nextNode.arr[index] = new Node();
                }
                nextNode = nextNode.arr[index];
                nextNode.pass++;
            }
            nextNode.end++;
        }

        public void pop(String word) {
            if (search(word) > 0) {
                char[] arr = word.toCharArray();
                Node nextNode = node;
                nextNode.pass--;

                for (char c : arr) {
                    int index = c - 'a';
                    nextNode = nextNode.arr[index];
                    nextNode.pass--;
                }
                nextNode.end--;
            }
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] arr = word.toCharArray();
            Node nextNode = node;

            for (char c : arr) {
                int index = c - 'a';
                if (nextNode.arr[index] == null || nextNode.arr[index].pass == 0) {
                    return 0;
                }
                nextNode = nextNode.arr[index];
            }
            return nextNode.end;
        }
    }
}
