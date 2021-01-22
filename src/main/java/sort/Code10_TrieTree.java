package sort;

/**
 * @author: Mr.diao
 * @date: 10:35 2020/12/25
 */
public class Code10_TrieTree {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("abc");
        trie.insert("abcd");
        trie.insert("cacfd");
        System.out.println(trie.search("abc"));
        trie.delete("abc");
        System.out.println(trie.search("abc"));
    }

    public static class Node {
        public int pass;
        public int end;
        public Node[] next;

        public Node() {
            pass = 0;
            end = 0;
            next = new Node[26];
        }
    }

    public static class Trie {
        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null || "".equals(word)) {
                return;
            }
            Node temp = root;
            temp.pass++;
            char[] arr = word.toCharArray();
            for (char ch : arr) {
                int index = ch - 'a';
                if (temp.next[index] == null) {
                    temp.next[index] = new Node();
                }
                temp = temp.next[index];
                temp.pass++;
            }
            temp.end++;
        }

        public Boolean search(String word) {
            if (word == null || "".equals(word)) {
                return false;
            }
            Node temp = root;

            char[] chars = word.toCharArray();
            for (char ch : chars) {
                int index = ch - 'a';
                if (temp.next[index] == null) {
                    return false;
                }
                temp = temp.next[index];
            }
            return temp.end > 0;
        }

        public void delete(String word) {
            if (!search(word)) {
                return;
            }

            Node temp = root;
            temp.pass--;
            char[] chars = word.toCharArray();
            for (char ch : chars) {
                int index = ch - 'a';
                //对应的下个节点的pass为0，说明没有字符串经过这个节点
                if (--temp.next[index].pass == 0) {
                    temp.next[index] = null;
                    return;
                }
                temp = temp.next[index];
            }
            temp.end--;
            if (temp.pass == 0) {
                temp.next = null;
            }
        }
    }
}
