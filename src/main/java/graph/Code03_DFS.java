package graph;

import java.util.HashSet;
import java.util.Stack;

/**
 * @Author: Mr.diao
 * @Description:
 * @Date: 15:19 2021/2/15
 */
public class Code03_DFS {

    //深度优先遍历
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        HashSet<Node> set = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.add(node);
        System.out.println(node.value);
        set.add(node);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            for (Node n : pop.nexts) {
                if (!set.contains(n)) {
                    stack.push(pop);
                    stack.push(n);
                    set.add(n);
                    System.out.println(n.value);
                    break;
                }
            }
        }
    }
}
