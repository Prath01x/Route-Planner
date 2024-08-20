package routing;

public class NodeFinderImpl implements NodeFinder {
    private final Graph graph;

    public NodeFinderImpl(Graph graph) {
        this.graph = graph;
    }

    @Override
    public Node getNodeForCoordinates(Coordinate c) {
        Node closestNode = null;
        double closestDistance = Double.MAX_VALUE;

        for (Node node : graph) {
            double distance = c.getDistance(node.getCoordinate());
            if (distance < closestDistance) {
                closestDistance = distance;
                closestNode = node;
            }
        }

        return closestNode;
    }
}
