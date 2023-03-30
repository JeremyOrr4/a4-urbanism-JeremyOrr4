package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;

public class Node {
    int NodeID;
    int XCoordinate;
    int YCoordinate;

    public Node (int NodeID, int XCoordinate, int YCoordinate){
        this.NodeID = NodeID;
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
    }

    public String GetNodeInfo(){
        return "ID:" + NodeID + " XCoord:" + XCoordinate + " YCoord:" + YCoordinate; 
    }

    
    public String PrintNode() {
        return "Node ID: " + this.NodeID;
    }
}
