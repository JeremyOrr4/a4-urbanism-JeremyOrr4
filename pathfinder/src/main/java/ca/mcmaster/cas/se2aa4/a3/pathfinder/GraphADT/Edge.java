package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;

public class Edge {
    Node StartNode;
    Node EndNode;
    int EdgeID;

    

    public Edge (Node StartNode, Node EndNode, int EdgeID){
        this.StartNode = StartNode;
        this.EndNode = EndNode;
        this.EdgeID = EdgeID;
    }

    public String PrintEdge(){
       return "Start Node: " + StartNode + " End Node: " + EndNode;

    }

}
