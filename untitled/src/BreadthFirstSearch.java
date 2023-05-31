import java.util.*;

public class BreadthFirstSearch<V> implements Search<V>{
    private WeightedGraph<V> graph;
    public BreadthFirstSearch(WeightedGraph<V> graph) {//constructor
        this.graph = graph;
    }
    @Override
    public List<V> path(Vertex<V> source, Vertex<V> destination) {
        Queue<Vertex<V>> queue = new LinkedList<>();//Queue for BreadthFirstSearch
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();// Map for parent vertices
        queue.add(source);//starting from source
        parentMap.put(source, null);
        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();//next vertex
            if (current.equals(destination)) {//If vertex is found creating path and returning it
                return createPath(parentMap, destination);
            }
            for (WeightedGraph.WeightedEdge<V> edge : graph.getEdges(current)) {
                Vertex<V> neighbor = (Vertex<V>) edge.getDestination();//neighbor vertex
                if (!parentMap.containsKey(neighbor)) {
                    queue.add(neighbor);//add neighbor to q
                    parentMap.put(neighbor, current);
                }
            }
        }
        return Collections.emptyList();//return an empty list if path is not found
    }

    private List<V> createPath(Map<Vertex<V>,Vertex<V>> map, Vertex<V> destination) {
        List<V> path = new LinkedList<>();
        Vertex<V> current = destination;
        while (current != null) {
            path.add(0, current.getData());//Add data of vertex to the path
            current = map.get(current);//moveto parent
        }
        return path;
    }
}