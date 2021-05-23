package 训练营3;

/**
 *给你一个字符串类型的数组arr，譬如:
 * String[] arr = { "b\st", "d\", "a\d\e", "a\b\c" };
 * 把这些路径中蕴含的目录结构给打印出来，子目录直接列在父目录下面，并比父目录向右进两格，就像这样:
 * a
 *   b
 *     c
 *   d
 *     e
 * b
 *   s
 * d
 * 同一级的需要按字母顺序排列不能乱。
 * @Author: Mr.diao
 * @Description:
 * @Date: 22:06 2021/5/23
 */
public class Code22_GetFolderTree {

    public static class Node {
        public Character value;
        public Node[] chars = new Node[26];

        public Node(Character value) {
            this.value = value;
        }
    }

    public static void print(String[] arr) {
        Node root = new Node(null);
        for (String str : arr) {
            String[] splits = str.split("/");
            Node cur = root;
            for (String c : splits) {
                char letter = c.charAt(0);
                if (cur.chars[letter - 'a'] == null) {
                    Node node = new Node(letter);
                    cur.chars[letter - 'a'] = node;
                    cur = node;
                } else {
                    cur = cur.chars[letter - 'a'];
                }
            }
        }
        //深度优先遍历
        process(root, 0);
    }

    private static void process(Node root, int level) {
        if (root.value != null) {
            System.out.println(getSpace(level) + root.value);
        }
        for (Node node : root.chars) {
            if (node != null) {
                process(node, level + 1);
            }
        }
    }

    public static String getSpace(int level) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < level; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        print(new String[]{"b/st", "d/", "a/d/e", "a/b/c"});
    }
}
