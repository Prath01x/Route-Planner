package routing;

public class EdgeImpl implements Edge {
    private Node start;
    private Node end;
    private double length;
    private boolean carAccessible;
    private boolean bikeAccessible;
    private boolean footAccessible;

    public EdgeImpl(Node start, Node end, double length, boolean carAccessible, boolean bikeAccessible, boolean footAccessible) {
        this.start = start;
        this.end = end;
        this.length = length;
        this.carAccessible = carAccessible;
        this.bikeAccessible = bikeAccessible;
        this.footAccessible = footAccessible;
    }

    @Override
    public Node getStart() {
        return start;
    }

    @Override
    public Node getEnd() {
        return end;
    }

    @Override
    public double getLength() {
        return length;
    }

    @Override
    public boolean allowsTravelType(TravelType tt, Direction dir) {
        switch (tt) {
            case CAR:
                return carAccessible;
            case BIKE:
                return bikeAccessible;
            case FOOT:
                return footAccessible;
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return "EdgeImpl{start=" + start + ", end=" + end + ", length=" + length + '}';
    }
}
