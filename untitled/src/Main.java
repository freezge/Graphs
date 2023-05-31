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