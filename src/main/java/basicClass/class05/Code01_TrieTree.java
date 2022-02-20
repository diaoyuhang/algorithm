package basicClass.class05;

public class Code01_TrieTree {

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
    }
}
