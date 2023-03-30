package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;

public class Node {
    int NodeID;

    public Node (int NodeID){
        this.NodeID = NodeID;
    }

    public int GetNodeValue(Node Node){
        return this.NodeID;
    }

    public void PrintNode(Node Node) {
        System.out.println("Node ID: " + Node.NodeID);
    }
}
