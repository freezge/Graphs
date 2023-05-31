import java.util.List;
public interface Search <V>{
    List<V> path(Vertex<V> source, Vertex<V> destination);
}