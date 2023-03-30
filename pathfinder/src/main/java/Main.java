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

        Node Node1 = new Node(1);
        Node Node2 = new Node(2);
        Node Node3 = new Node(3);
        Node Node4 = new Node(4);

        Edge Edge1 = CreateEdge(Graph, Node1, Node2);
        Edge Edge2 = CreateEdge(Graph, Node1, Node3);


        

        Node1.PrintNodeInfo();
        Edge2.PrintEdge();
        Graph.PrintPathOfNode(Node1);
        Graph.PrintNodesConectedSet(Node1);

    }

    public static Edge CreateEdge(GraphADT Graph, Node StartNode, Node EndNode) {
        Graph.AddEdge(StartNode,EndNode);
        Graph.AddEdge(EndNode,StartNode);
        Edge Edge = new Edge(StartNode, EndNode);
        return Edge;
    }
}
