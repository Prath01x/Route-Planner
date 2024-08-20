package routing;

import java.util.Iterator;
import java.util.Map;

public class SimpleGraph implements Graph {
    private final Map<Long, Node> nodes;
    private final Map<Node, Map<Node, Double>> edges;

    public SimpleGraph(Map<Long, Node> nodes, Map<Node, Map<Node, Double>> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    @Override
    public Node getNode(long id) {
        return nodes.get(id);
    }

    @Override
    public Coordinate getNWCoordinate() {
        // Compute the NW coordinate
        return null;
    }

    @Override
    public Coordinate getSECoordinate() {
        // Compute the SE coordinate
        return null;
    }

    @Override
    public Iterator<Node> iterator() {
        return nodes.values().iterator();
    }

    @Override
    public int numEdges() {
        int count = 0;
        for (Map<Node, Double> nodeEdges : edges.values()) {
            count += nodeEdges.size();
        }
        return count / 2; // Assuming undirected graph
    }

    @Override
    public int numNodes() {
        return nodes.size();
    }

    @Override
    public int removeIsolatedNodes() {
        // Remove isolated nodes
        return 0;
    }

    @Override
    public int removeUntraversableEdges(RoutingAlgorithm ra, TravelType tt) {
        // Remove untraversable edges
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
