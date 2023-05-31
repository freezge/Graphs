# ADS Assignment 6
### Assignment 6 | Graphs
### Task
```
Implement BFS and Djakstra for Edge-weighted graph with Vertex instead of Edge.
Provide your own implementation for classes: 
-Vertex, 
-WeightedGraph, 
-Search, 
-BreadthFirstSearch, 
-DijkstraSearch
-Main (with example usage) 
```
### Code for Main
```
import java.util.List;

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();//tests
        Vertex<String> A = new Vertex<>("A");//tests
        Vertex<String> B = new Vertex<>("B");//tests
        Vertex<String> C = new Vertex<>("C");//tests
        Vertex<String> D = new Vertex<>("D");//tests
        Vertex<String> E = new Vertex<>("E");//tests
        graph.addVertex(A);//tests
        graph.addVertex(B);//tests
        graph.addVertex(C);//tests
        graph.addVertex(D);//tests
        graph.addVertex(E);//tests
        graph.addEdge(A, B, 35.0);//tests
        graph.addEdge(A, C, 17.0);//tests
        graph.addEdge(B, D, 11.0);//tests
        graph.addEdge(C, D, 61.0);//tests
        graph.addEdge(C, E, 19.0);//tests
        Search<String> b = new BreadthFirstSearch<>(graph);//tests
        List<String> path = b.path(A, D);//tests
        System.out.println(path);//tests
//        Search<String> d = new DijkstraSearch<>(graph);//tests
//        List<String> path = d.path(A, D);//tests
//        System.out.println(path);//tests
    }
}
```
### Code for Vertex
```
import java.util.HashMap;
import java.util.Map;

public class Vertex<V> {
    private V data;
    private Map<Vertex<V>, Double> adjacentVertices;
    private double distance = Double.POSITIVE_INFINITY;
    public Vertex(V data) {//constructor
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }
    public V getData() {//getter
        return data;
    }

    public double getDistance() {//getter
        return distance;
    }
    public void setDistance(double distance){//setter
        this.distance = distance;
    }

    public Map<Vertex<V>, Double> getAdjacentVertices(){//getter
        return adjacentVertices;
    }
    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }
}
```
### Code for WeightedGraph
```
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
```
### Code for Search
```
import java.util.List;
public interface Search <V>{
    List<V> path(Vertex<V> source, Vertex<V> destination);
}
```
### Code for BreadthFirstSearch
```
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
```
### Code for DijkstraSearch
```
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
```
