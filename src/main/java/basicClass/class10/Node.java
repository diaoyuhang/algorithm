package basicClass.class10;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public String value;
    public int in;
    public int out;

    public List<Node> next;
    public List<Edge> edges;


    public Node(String value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.next = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
