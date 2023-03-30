package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;





import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class GraphADT {

    Node Node;
    Edge Edge;
    ArrayList<Edge> PathList;
    HashMap<Node, ArrayList<Edge>> AdjacencyList;

    public GraphADT() {
        this.Node = new Node(0);
        this.Edge = new Edge(this.Node, 0);
        this.PathList = new ArrayList<Edge>();
        this.AdjacencyList = new HashMap<Node, ArrayList<Edge>>();
    }

    public void AddEdge(Node StartNode, Edge EndNode) {
        if (AdjacencyList.containsKey(StartNode)) {
            this.PathList = this.AdjacencyList.get(StartNode);
            PathList.add(EndNode);
            this.AdjacencyList.put(StartNode, PathList);
        }

        else {
            this.AdjacencyList.put(StartNode, this.PathList);
            this.PathList.add(EndNode);
            this.AdjacencyList.put(StartNode, PathList);
            System.out.println(Arrays.asList(PathList));
        }
    }

    public void PrintPathOfNode(Node Node) {
        if (AdjacencyList.containsKey(Node)) {
            System.out.println("*******************");
            System.out.println("Node Start: " + Node.GetNodeValue(Node));
            this.PathList = AdjacencyList.get(Node);

            for (int i = 0; i < PathList.size(); i++) {
                Edge PrintedEdge = PathList.get(i);

                Edge.PrintEdge(PrintedEdge);
            }
            System.out.println("*******************");
        }

        else {
            System.out.println("Node is Not in Graph.");
        }
    }

}
