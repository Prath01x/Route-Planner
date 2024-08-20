package routing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GraphFactory {

    public static Graph createGraphFromMap(String fileName) throws IOException {
        GraphImpl graph = new GraphImpl();
        Map<Long, Node> nodes = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts[0].equals("N")) {
                    long id = Long.parseLong(parts[1]);
                    double lat = Double.parseDouble(parts[2]);
                    double lon = Double.parseDouble(parts[3]);
                    Node node = new NodeImpl(id, new Coordinate(lat, lon));
                    nodes.put(id, node);
                    graph.addNode(node);
                } else if (parts[0].equals("E")) {
                    long sourceId = Long.parseLong(parts[1]);
                    long destId = Long.parseLong(parts[2]);
                    double length = Double.parseDouble(parts[3]);
                    boolean carAccessible = parts[4].equals("1");
                    boolean bikeAccessible = parts[5].equals("1");
                    boolean footAccessible = parts[6].equals("1");

                    Node source = nodes.get(sourceId);
                    Node destination = nodes.get(destId);

                    if (source != null && destination != null) {
                        Edge toEdge = new EdgeImpl(source, destination, length, carAccessible, bikeAccessible, footAccessible);
                        Edge fromEdge = new EdgeImpl(destination, source, length, carAccessible, bikeAccessible, footAccessible);

                        graph.addEdge(toEdge);
                        graph.addEdge(fromEdge);
                    }
                }
            }
        }

        return graph;
    }
}
