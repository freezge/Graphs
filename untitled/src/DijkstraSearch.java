import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }
    @Override
    public List<V> path(Vertex<V> source, Vertex<V> destination) {
        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>(Comparator.comparingDouble(Vertex<V>::getDistance));
        Map<Vertex<V>, Double> distances = new HashMap<>();
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();
        for (Vertex<V> vertex : graph.getVertices()) {
            if (vertex.equals(source)) {
                vertex.setDistance(0.0);//change first vertexes distance to 0
            } else {
                vertex.setDistance(Double.POSITIVE_INFINITY);//others to infinity
            }
            distances.put(vertex, vertex.getDistance());//for storing
            queue.add(vertex);//add the vertices
        }
        while (!queue.isEmpty()) {//get the vertex with the smallest distance
            Vertex<V> current = queue.poll();
            if (current.equals(destination)) {//If vertex is found creating path and returning it
                return createPath(parentMap, destination);
            }
            for (WeightedGraph.WeightedEdge<V> edge : graph.getEdges(current)) {
                Vertex<V> neighbor = (Vertex<V>) edge.getDestination();//neighbor vertex
                double newDistance = current.getDistance() + edge.getWeight();
                if (newDistance < neighbor.getDistance()) {queue.remove(neighbor);
                    neighbor.setDistance(newDistance); //changing distance of neighbor
                    distances.put(neighbor, newDistance);//change distance in storage
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);//add neighbor to q
                }
            }
        }
        return Collections.emptyList();//return an empty list if path is not found
    }
    private List<V> createPath(Map<Vertex<V>,Vertex<V>> map, Vertex<V> destination) {
        List<V> path = new LinkedList<>();
        Vertex<V> current = destination;
        while (current != null) {
            path.add(0, current.getData());//add data of vertex to the path
            current = map.get(current);//moveto parent
        }
        return path;
    }
}