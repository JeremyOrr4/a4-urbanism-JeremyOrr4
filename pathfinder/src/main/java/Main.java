import java.io.IOException;
import java.util.Map;

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

        Edge Edge1 = CreateEdge(Graph, node1, node2);
        Edge Edge2 = CreateEdge(Graph, node2, node3);
        Edge Edge3 = CreateEdge(Graph, node3, node4);

        
    

        ShortestPath Path = new ShortestPath();

        Map<Node, Integer> shortestDistances = Path.findPath(Graph, node1);
        for (Node node : shortestDistances.keySet()) {
            System.out.println("Distance from node " + node.GetNodeID() + " to node 1: " + shortestDistances.get(node));
        }


    }

    public static Edge CreateEdge(GraphADT Graph, Node StartNode, Node EndNode) {
        Graph.AddEdge(StartNode,EndNode);
        Graph.AddEdge(EndNode,StartNode);
        Edge Edge = new Edge(StartNode, EndNode,1);
        return Edge;
    }
}
