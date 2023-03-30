import java.io.IOException;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.Edge;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.GraphADT;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.Node;

public class Main {
    public static void main(String[] args) throws IOException {
        String mesh = "img/irregular.mesh";
        Structs.Mesh Mesh = new MeshFactory().read(mesh);
        
        GraphADT Graph = new GraphADT();
        Node Node = new Node(1);
        Node EndNode = new Node(2);
        Edge Edge = new Edge(EndNode,1);

        Graph.AddEdge(Node, Edge);

        // Edge.PrintEdge(Edge);
        // Node.PrintNode(EndNode);

        Graph.PrintPathOfNode(Node);

    }
}
