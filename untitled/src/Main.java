import java.util.List;

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();
        Vertex<String> A = new Vertex<>("A");
        Vertex<String> B = new Vertex<>("B");
        Vertex<String> C = new Vertex<>("C");
        Vertex<String> D = new Vertex<>("D");
        Vertex<String> E = new Vertex<>("E");
        graph.addVertex(A);
        graph.addVertex(B);
        graph.addVertex(C);
        graph.addVertex(D);
        graph.addVertex(E);
        graph.addEdge(A, B, 35.0);
        graph.addEdge(A, C, 17.0);
        graph.addEdge(B, D, 11.0);
        graph.addEdge(C, D, 61.0);
        graph.addEdge(C, E, 19.0);
        Search<String> b = new BreadthFirstSearch<>(graph);
        List<String> path = b.path(A, D);
        System.out.println(path);
//        Search<String> d = new DijkstraSearch<>(graph);
//        List<String> path = d.path(A, D);
//        System.out.println(path);
    }
}