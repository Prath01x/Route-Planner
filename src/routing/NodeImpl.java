package routing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NodeImpl implements Node {
    private long id;
    private Coordinate coordinate;
    private List<Edge> outgoingEdges;

    public NodeImpl(long id, Coordinate coordinate) {
        this.id = id;
        this.coordinate = coordinate;
        this.outgoingEdges = new ArrayList<>();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public Edge getEdge(int idx) {
        return outgoingEdges.get(idx);
    }

    @Override
    public int numEdges() {
        return outgoingEdges.size();
    }

    @Override
    public void addEdge(Edge edge) {
        outgoingEdges.add(edge);
    }

    @Override
    public void removeEdge(int i) {
        outgoingEdges.remove(i);
    }

    @Override
    public Iterator<Edge> iterator() {
        return outgoingEdges.iterator();
    }

    @Override
    public String toString() {
        return "NodeImpl{id=" + id + ", coordinate=" + coordinate + '}';
    }
}
