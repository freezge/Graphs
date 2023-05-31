import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph<V>{
    public static class WeightedEdge<V>{
        private Vertex<V> source;
        private Vertex<V> destination;
        private double weight;
        public WeightedEdge(Vertex<V> source, Vertex<V> destination, double weight) { //constructor
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
        public Vertex<V> getSource(){//getter
            return source;
        }
        public Vertex<V> getDestination(){//getter
            return destination;
        }
        public double getWeight(){//getter
            return weight;
        }
    }
    private Map<Vertex<V>, List<WeightedEdge<V>>> map;//for storing
    public WeightedGraph() {//constructor
        this.map = new HashMap<>();
    }
    public void addVertex(Vertex<V> vertex) {//adds vertex to graph
        map.put(vertex, new ArrayList<>());
    }
    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {//adds edges to graph
        if (!checkVertex(source) && !checkVertex(destination)){//checking if source and dest are in graph
            return;
        }
        List<WeightedEdge<V>> edges = map.get(source);
        edges.add(new WeightedEdge<>(source, destination, weight));
    }
    public boolean checkVertex(Vertex<V> vertex){
        return map.containsKey(vertex);
    }//check vertexes
    public List<WeightedEdge<V>> getEdges(Vertex<V> vertex) {//get edge
        if (!checkVertex(vertex)){
            return null;
        }
        return map.get(vertex);
    }
    public List<Vertex<V>> getVertices() {//get vertices
        return new ArrayList<>(map.keySet());
    }
}