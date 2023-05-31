import java.util.*;

public class BreadthFirstSearch<V> implements Search<V>{
    private WeightedGraph<V> graph;
    public BreadthFirstSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }
    @Override
    public List<V> path(Vertex<V> source, Vertex<V> destination) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();
        queue.add(source);
        parentMap.put(source, null);
        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            if (current.equals(destination)) {
                return constructPath(parentMap, destination);
            }
            for (WeightedGraph.WeightedEdge<V> edge : graph.getEdges(current)) {
                Vertex<V> neighbor = (Vertex<V>) edge.getDestination();
                if (!parentMap.containsKey(neighbor)) {
                    queue.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<V> constructPath(Map<Vertex<V>,Vertex<V>> map, Vertex<V> destination) {
        return null;
    }
}