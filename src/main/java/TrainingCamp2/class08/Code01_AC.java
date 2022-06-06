package TrainingCamp2.class08;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Code01_AC {

    public static class ACAutomation {
        private Node root;

        public ACAutomation() {
            root = new Node();
        }

        public void insert(String str) {
            if (str == null || str.trim().length() == 0) {
                return;
            }
            char[] chars = str.toCharArray();
            Node cur = root;
            for (char c : chars) {
                int index = c - 'a';

                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
            cur.end = str;
        }

        public void build() {
            LinkedList<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur = null;
            Node cFail = null;
            while (!queue.isEmpty()) {
                cur = queue.pop();

                for (int i = 0; i < 26; i++) {
                    if (cur.nexts[i] != null) {
                        queue.add(cur.nexts[i]);
                        cur.nexts[i].fail = root;
                        cFail = cur.fail;

                        while (cFail != null) {
                            if (cFail.nexts[i] != null) {
                                cur.nexts[i].fail = cFail.nexts[i];
                                break;
                            }
                            cFail = cFail.fail;
                        }
                    }
                }

            }
        }

        public List<String> containWords(String content) {
            ArrayList<String> ans = new ArrayList<>();
            char[] contentChars = content.toCharArray();
            Node cur = root;

            for (char c : contentChars) {
                int index = c - 'a';

                while (cur.nexts[index] == null && cur != root) {
                    cur = cur.fail;
                }

                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                Node follow = cur;

                while (follow != root) {
                    if (follow.endUse) {
                        break;
                    }

                    if (follow.end != null) {
                        follow.endUse = true;
                        ans.add(follow.end);
                    }
                    follow = follow.fail;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdheks");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }
    }

    public static class Node {
        // 如果一个node，end为空，不是结尾
        // 如果end不为空，表示这个点是某个字符串的结尾，end的值就是这个字符串
        public String end;
        // 只有在上面的end变量不为空的时候，endUse才有意义
        // 表示，这个字符串之前有没有加入过答案
        public boolean endUse;
        public Node fail;
        public Node[] nexts;

        public Node() {
            endUse = false;
            end = null;
            fail = null;
            nexts = new Node[26];
        }
    }


}
