package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;

public class Edge {
    Node NextNode;
    int Weight;
    

    public Edge (Node NextNode, int Weight){
        this.Weight = Weight;
        this.NextNode = NextNode;
    }

    public void PrintEdge(Edge Edge){
        System.out.println("Next Node ID:" + Edge.NextNode.NodeID + " Weight: " + Edge.Weight);

    }

    public int GetNodeValue(Node Node) {
        return this.NextNode.NodeID;
    }
}
