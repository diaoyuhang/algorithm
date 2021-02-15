package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 15:09 2021/2/15
 */
public class Code02_BFS {

    //宽度优先遍历
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        //保存哪些结点遍历过
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.value);
            for (Node n : poll.nexts) {
                if (!set.contains(n)) {
                    set.add(n);
                    queue.add(n);
                }
            }
        }
    }
}
