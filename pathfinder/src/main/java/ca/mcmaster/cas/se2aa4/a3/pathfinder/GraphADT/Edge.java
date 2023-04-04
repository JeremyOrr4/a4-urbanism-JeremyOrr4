package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;

public class Edge {
    Node StartNode;
    Node EndNode;
    int EdgeID;
    double Weight;

    public Edge(Node StartNode, Node EndNode, double Weight) {
        this.StartNode = StartNode;
        this.EndNode = EndNode;
        this.Weight = Weight;
        // this.EdgeID = EdgeID;
    }

    public void PrintEdge() {
        System.out.println("Edges: Start Node: " + StartNode.GetNodeID() + " End Node: " + EndNode.GetNodeID());
    }

    //Getters and setters
    public void SetStartNode(Node StartNode){
        this.StartNode = StartNode;
    }

    public void SetEndNode(Node EndNode){
        this.EndNode = EndNode;
    }

    public void SetEdgeWeight(Node EndNode, int Weight){
        this.Weight = Weight;
        this.EndNode = EndNode;
    }

    public Node GetStartNode (){
        return StartNode;
    }

    public Node GetEndNode (){
        return EndNode;
    }

    public double GetWeight(){
        return Weight;
    }
}
