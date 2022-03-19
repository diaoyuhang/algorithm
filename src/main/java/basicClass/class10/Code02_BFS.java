package basicClass.class10;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Code02_BFS {

    public static void bfs(Node node){
        if (node==null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        set.add(node);
        queue.add(node);

        while(!queue.isEmpty()){
            Node n = queue.poll();
            System.out.println(n.value);

            for (Node nextNode:n.next){
                if (!set.contains(nextNode)){
                    set.add(nextNode);
                    queue.add(nextNode);
                }
            }
        }
    }
}
