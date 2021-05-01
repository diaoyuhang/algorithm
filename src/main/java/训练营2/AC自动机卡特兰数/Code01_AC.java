package 训练营2.AC自动机卡特兰数;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: Mr.diao
 * @date: 10:26 2021/4/29
 */
public class Code01_AC {

    // 前缀树的节点
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

    public static class ACAutomation {
        private Node root;

        public ACAutomation() {
            root = new Node();
        }

        public void insert(String s) {
            char[] chars = s.toCharArray();
            Node cur = root;
            for (char c : chars) {
                int index = c - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
            cur.end = s;
        }

        public void build() {
            LinkedList<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur = null;
            Node cFail = null;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (int i = 0; i < 26; i++) {
                    if (cur.nexts[i] != null) {
                        cur.nexts[i].fail = root;
                        cFail = cur.fail;

                        while (cFail != null) {
                            if (cFail.nexts[i] != null) {
                                cur.nexts[i].fail = cFail.nexts[i];
                                break;
                            }
                            cFail = cFail.fail;
                        }

                        queue.add(cur.nexts[i]);
                    }

                }
            }
        }

        public List<String> containWord(String content) {
            char[] chars = content.toCharArray();
            ArrayList<String> res = new ArrayList<>();
            Node follow = null;
            Node cur = root;

            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                while (cur.nexts[index] == null && cur != root) {
                    cur = cur.fail;
                }

                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.endUse){
                        break;
                    }
                    if (follow.end!=null){
                        res.add(follow.end);
                        follow.endUse=true;
                    }
                    follow=follow.fail;
                }
            }

            return res;
        }
    }

    public static void main(String[] args){
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdhes");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containWord("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }
    }
}
