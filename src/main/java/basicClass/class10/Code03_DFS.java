package basicClass.class10;

import java.util.HashSet;
import java.util.Stack;

public class Code03_DFS {

    public static void dfs(Node head) {
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(head);
        set.add(head);
        System.out.println(head.value);
        while (!stack.isEmpty()) {
            Node no = stack.pop();
            for (Node node : no.next) {
                if (!set.contains(node)) {
                    set.add(node);
                    System.out.println(node.value);
                    stack.push(no);
                    stack.push(node);
                    break;
                }
            }
        }
    }
}
