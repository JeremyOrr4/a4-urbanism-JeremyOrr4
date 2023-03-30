package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;

public class Edge {
    Node StartNode;
    Node EndNode;
    int EdgeID;
    int Weight;

    public Edge(Node StartNode, Node EndNode) {
        this.StartNode = StartNode;
        this.EndNode = EndNode;
        // this.EdgeID = EdgeID;
    }

    public void PrintEdge() {
        System.out.println("Edges: Start Node: " + StartNode.GetNodeID() + " End Node: " + EndNode.GetNodeID());
    }

}
