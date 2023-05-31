import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph<V>{
    public static class WeightedEdge<V>{
        private Vertex<V> source;
        private Vertex<V> destination;
        private double weight;
        public WeightedEdge(Vertex<V> source, Vertex<V> destination, double weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
        public Vertex<V> getSource(){
            return source;
        }
        public Vertex<V> getDestination(){
            return destination;
        }
        public double getWeight(){
            return weight;
        }
    }
    private Map<Vertex<V>, List<WeightedEdge<V>>> map;
    public WeightedGraph() {
        this.map = new HashMap<>();
    }
    public void addVertex(Vertex<V> vertex) {
        map.put(vertex, new ArrayList<>());
    }
    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        if (!checkVertex(source) && !checkVertex(destination)){
            return;
        }
        List<WeightedEdge<V>> edges = map.get(source);
        edges.add(new WeightedEdge<>(source, destination, weight));
    }
    public boolean checkVertex(Vertex<V> vertex){
        return map.containsKey(vertex);
    }
    public List<WeightedEdge<V>> getEdges(Vertex<V> vertex) {
        if (!checkVertex(vertex)){
            return null;
        }
        return map.get(vertex);
    }
    public List<Vertex<V>> getVertices() {
        return new ArrayList<>(map.keySet());
    }
}