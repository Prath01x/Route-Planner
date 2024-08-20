package routing;

import java.util.Iterator;
import java.util.List;

public class RouteImpl extends RouteBase {
    private final List<RouteLeg> legs;
    private final TravelType travelType;
    public RouteImpl(TravelType travelType, List<RouteLeg> legs) {
       this.travelType=travelType;
        this.legs = legs;
    }

    @Override
    public double distance() {
        return legs.stream().mapToDouble(RouteLeg::getDistance).sum();
    }

    @Override
    public Node getEndNode() {
        if (legs.isEmpty()) {
            return null;
        }
        return legs.get(legs.size() - 1).getEndNode();
    }

    @Override
    public Node getStartNode() {
        if (legs.isEmpty()) {
            return null;
        }
        return legs.get(0).getStartNode();
    }

    @Override
    public Iterator<RouteLeg> iterator() {
        return legs.iterator();
    }

    @Override
    public int size() {
        return legs.size();
    }

    @Override
    public TravelType getTravelType() {
        return travelType;
    }
}
