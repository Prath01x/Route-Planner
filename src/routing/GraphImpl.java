package routing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GraphImpl implements Graph {
    private Map<Long, Node> nodes;
    private int numEdges;

    public GraphImpl() {
        nodes = new HashMap<>();
        numEdges = 0;
    }

    public void addNode(Node node) {
        nodes.put(node.getId(), node);
    }

    public void addEdge(Edge edge) {
        Node start = edge.getStart();
        start.addEdge(edge);
        numEdges++;
    }

    @Override
    public Node getNode(long id) {
        return nodes.get(id);
    }

    @Override
    public Coordinate getNWCoordinate() {
        // Implement logic to return the NW coordinate
        return null;
    }

    @Override
    public Coordinate getSECoordinate() {
        // Implement logic to return the SE coordinate
        return null;
    }

    @Override
    public Iterator<Node> iterator() {
        return nodes.values().iterator();
    }

    @Override
    public int numEdges() {
        return numEdges;
    }

    @Override
    public int numNodes() {
        return nodes.size();
    }

    @Override
    public int removeIsolatedNodes() {
        // Implement logic to remove isolated nodes
        return 0;
    }

    @Override
    public int removeUntraversableEdges(RoutingAlgorithm ra, TravelType tt) {
        // Implement logic to remove untraversable edges
        return 0;
    }

    @Override
    public boolean isOverlayGraph() {
        return false;
    }

    @Override
    public Node getNodeInUnderlyingGraph(long id) {
        return null;
    }
}
