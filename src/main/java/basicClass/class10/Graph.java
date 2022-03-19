package basicClass.class10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Graph {
    public Map<String, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
