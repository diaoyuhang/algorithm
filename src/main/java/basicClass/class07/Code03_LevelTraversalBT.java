package basicClass.class07;

import java.util.LinkedList;
import java.util.Queue;

public class Code03_LevelTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void level(Node head) {
        if (head == null) {
            return ;
        }
        Queue<Node> queue=new LinkedList<>();
        queue.add(head);

        while(!queue.isEmpty()){
            Node node = queue.poll();
            System.out.println(node.value);
            if (node.left!=null){
                queue.add(node.left);
            }
            if (node.right!=null){
                queue.add(node.right);
            }
        }
    }

}
