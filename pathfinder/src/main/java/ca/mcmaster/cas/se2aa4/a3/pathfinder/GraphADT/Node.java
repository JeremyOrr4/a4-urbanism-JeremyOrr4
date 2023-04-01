package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;

public class Node {
    private int NodeID;
    private int XCoordinate;
    private int YCoordinate;

    public Node(int NodeID) {
        this.NodeID = NodeID;
        // , int XCoordinate, int YCoordinate
        // this.XCoordinate = XCoordinate;
        // this.YCoordinate = YCoordinate;
    }

    //Getters and setters

    public int GetNodeID() {
        return NodeID;
    }


    public int GetXCoordinate() {
        return XCoordinate;
    }

    public int GetYCoordinate() {
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
