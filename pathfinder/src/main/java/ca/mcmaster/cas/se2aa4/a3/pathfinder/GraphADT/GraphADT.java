package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;
import ca.mcmaster.cas.se2aa4.a2.io.*;
import java.util.HashMap;


public class GraphADT {
    int NumberOfNodes;
    boolean[][] AdjacneyMatrix = new boolean[0][0];
    
    HashMap<Integer, Structs.Vertex> NodeId = new HashMap<>();

    
    GraphADT(int NumberOfNodes){
        this.NumberOfNodes = NumberOfNodes;
        this.AdjacneyMatrix = new boolean[NumberOfNodes][NumberOfNodes];
    }
 
}
