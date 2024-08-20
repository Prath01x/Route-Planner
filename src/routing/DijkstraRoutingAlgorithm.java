package routing;

import java.util.*;

public class DijkstraRoutingAlgorithm implements RoutingAlgorithm {

    @Override
    public Route computeRoute(Graph g, List<Node> waypoints, TravelType tt) throws NoSuchRouteException {
        if (waypoints == null || waypoints.isEmpty()) {
            throw new NoSuchRouteException("No waypoints provided");
        }

        List<RouteLeg> routeLegs = new ArrayList<>();
        for (int i = 0; i < waypoints.size() - 1; i++) {
            RouteLeg leg = computeRouteLeg(g, waypoints.get(i), waypoints.get(i + 1), tt);
            routeLegs.add(leg);
        }
        return new RouteImpl(tt, routeLegs);
    }

    @Override
    public RouteLeg computeRouteLeg(Graph g, long startId, long endId, TravelType tt) throws NoSuchRouteException {
        Node startNode = g.getNode(startId);
        Node endNode = g.getNode(endId);
        if (startNode == null || endNode == null) {
            throw new NoSuchRouteException("Invalid start or end node");
        }
        return computeRouteLeg(g, startNode, endNode, tt);
    }

    @Override
    public RouteLeg computeRouteLeg(Graph g, Node startNode, Node endNode, TravelType tt) throws NoSuchRouteException {
        Map<Node, Double> distances = new HashMap<>();
        Map<Node, Node> predecessors = new HashMap<>();
        PriorityQueue<NodeDistancePair> queue = new PriorityQueue<>(Comparator.comparingDouble(NodeDistancePair::getDistance));
        Set<Node> visited = new HashSet<>();

        distances.put(startNode, 0.0);
        queue.add(new NodeDistancePair(startNode, 0.0));

        while (!queue.isEmpty()) {
            NodeDistancePair current = queue.poll();
            Node currentNode = current.getNode();

            if (visited.contains(currentNode)) {
                continue;
            }
            visited.add(currentNode);

            if (currentNode.equals(endNode)) {
                return constructRouteLeg(startNode, endNode, predecessors, distances.get(endNode));
            }

            for (int i = 0; i < currentNode.numEdges(); i++) {
                Edge edge = currentNode.getEdge(i);
                if (!edge.allowsTravelType(tt, Direction.FORWARD)) {
                    continue;
                }
                Node neighbor = edge.getEnd();
                double newDist = distances.get(currentNode) + edge.getLength();
                if (newDist < distances.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    distances.put(neighbor, newDist);
                    predecessors.put(neighbor, currentNode);
                    queue.add(new NodeDistancePair(neighbor, newDist));
                }
            }
        }

        throw new NoSuchRouteException("No route found from start to end");
    }

    private RouteLeg constructRouteLeg(Node startNode, Node endNode, Map<Node, Node> predecessors, double distance) {
        List<Node> path = new LinkedList<>();
        Node step = endNode;
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return new Routelegimpl(path, distance, TravelType.ANY);
    }

    @Override
    public boolean isBidirectional() {
        return false;
    }

    private static class NodeDistancePair {
        private final Node node;
        private final double distance;

        public NodeDistancePair(Node node, double distance) {
            this.node = node;
            this.distance = distance;
        }

        public Node getNode() {
            return node;
        }

        public double getDistance() {
            return distance;
        }
    }
}
