import java.util.HashMap;
import java.util.Map;

public class Vertex<V> {
    private V data;
    private Map<Vertex<V>, Double> adjacentVertices;
    private double distance = Double.POSITIVE_INFINITY;
    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }
    public V getData() {
        return data;
    }

    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance){
        this.distance = distance;
    }

    public Map<Vertex<V>, Double> getAdjacentVertices(){
        return adjacentVertices;
    }
    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }
}