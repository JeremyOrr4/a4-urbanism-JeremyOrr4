import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.batik.parser.PathArrayProducer;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.Edge;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.GraphADT;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.Node;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.ShortestPath;

public class Main {
    public static void main(String[] args) throws IOException {
        String mesh = "img/irregular.mesh";
        Structs.Mesh Mesh = new MeshFactory().read(mesh);

        // GraphADT Graph = new GraphADT();

        // Node Node1 = new Node(1);
        // Node Node2 = new Node(2);
        // Node Node3 = new Node(3);
        // Node Node4 = new Node(4);

        // Edge Edge1 = CreateEdge(Graph, Node1, Node2);
        // Edge Edge2 = CreateEdge(Graph, Node1, Node3);


        

        // Node1.PrintNodeInfo();
        // Edge2.PrintEdge();
        // Graph.PrintPathOfNode(Node1);
        // Graph.PrintNodesConectedSet(Node1);


        GraphADT Graph = new GraphADT();

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        Edge Edge3 = CreateEdge(Graph, node1, node2);
        Edge Edge1 = CreateEdge(Graph, node4, node3);
        Edge Edge2 = CreateEdge(Graph, node3, node1);

        

        
    

        ShortestPath Path = new ShortestPath();

        List<Node> shortestDistances = Path.findPathBetweenNode(Graph, node1, node4);
        for (Node node : shortestDistances) {
            System.out.println(node.GetNodeID());
        }


    }

    public static Edge CreateEdge(GraphADT Graph, Node StartNode, Node EndNode) {
        Graph.AddEdge(StartNode,EndNode);
        Graph.AddEdge(EndNode,StartNode);
        Edge Edge = new Edge(StartNode, EndNode,1);
        return Edge;
    }
}
