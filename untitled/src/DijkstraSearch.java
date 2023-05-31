import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DijkstraSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }
    @Override
    public List<V> path(Vertex<V> source, Vertex<V> destination) {
        return null;
    }
}
