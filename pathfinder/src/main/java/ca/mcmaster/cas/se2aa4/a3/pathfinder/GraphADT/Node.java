package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;

public class Node {
    private int NodeID;
    private double XCoordinate;
    private double YCoordinate;
    
    public Node(int NodeID, double XCoordinate, double YCoordinate ) {
        this.NodeID = NodeID;
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
    }

    //Getters and setters

    public int GetNodeID() {
        return NodeID;
    }


    public double GetXCoordinate() {
        return XCoordinate;
    }

    public double GetYCoordinate() {
        return YCoordinate;
    }

    public void SetXCoordinate(int XCoordValue) {
        XCoordinate = XCoordValue;
    }

    public void SetYCoordinate(int YCoordValue) {
        YCoordinate = YCoordValue;
    }

    public void SetNodeID(int NodeIDValue) {
        NodeID = NodeIDValue;
    }

    //Printing

    public void PrintNodeInfo() {
        System.out.println("NODE INFO ID:" + NodeID + " XCoord:" + XCoordinate + " YCoord:" + YCoordinate);
    }



}
