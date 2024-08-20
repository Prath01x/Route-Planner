package routing;

import java.util.Iterator;
import java.util.List;

public class Routelegimpl extends RouteLegBase {
    private final List<Node> nodes;
    private final double distance;
    private final TravelType travelType;

    public Routelegimpl(List<Node> nodes, double distance, TravelType travelType) {
        this.nodes = nodes;
        this.distance = distance;
        this.travelType = travelType;
    }

    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public Node getEndNode() {
        if (nodes.isEmpty()) {
            return null;
        }
        return nodes.get(nodes.size() - 1);
    }

    @Override
    public Node getStartNode() {
        if (nodes.isEmpty()) {
            return null;
        }
        return nodes.get(0);
    }

    @Override
    public Iterator<Node> iterator() {
        return nodes.iterator();
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public String toJSON() {
        // Use the provided JSONHelper class to build the JSON representation
        return super.toJSON();
    }
}
