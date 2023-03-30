package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GraphADT {

    Node Node;
    Edge Edge;
    HashMap<Node, Set<Node>> AdjacencyList;

    public GraphADT() {
        AdjacencyList = new HashMap<Node, Set<Node>>();
    }

    public void AddEdge(Node StartNode, Node EndNode) {
        if (AdjacencyList.containsKey(StartNode)) {
            AdjacencyList.get(StartNode).add(EndNode);
        }

        else {
            Set<Node> EmptySet = new HashSet<Node>();
            AdjacencyList.put(StartNode, EmptySet);
            AddEdge(StartNode, EndNode);
        }
    }

    public void PrintPathOfNode(Node NodeToPrint) {
        if (AdjacencyList.containsKey(NodeToPrint)) {
            System.out.println("*******************");
            System.out.println("Node Start: " + NodeToPrint.GetNodeID());

            for (Node TempNode : AdjacencyList.get(NodeToPrint)) {
                System.out.println("Conneted To: " + TempNode.GetNodeID());
            }
            System.out.println("*******************");
        }

        else {
            System.out.println("Node is Not in Graph.");
        }
    }

    public void PrintAdjacencyList() {

    }

    public Set<Node> GetNodesConnectedSet(Node NodeOfPathWanted) {
        if (AdjacencyList.containsKey(NodeOfPathWanted)) {
            return AdjacencyList.get(NodeOfPathWanted);
        } 
        else {
            System.out.println("Node is Not in Graph.");
        }
        
        Set<Node> EmptyNodeSet = new HashSet<Node>();
        return EmptyNodeSet;
    }

    public void PrintNodesConectedSet(Node NodeOfPathWanted){
        for (Node node : GetNodesConnectedSet(NodeOfPathWanted)){
            System.out.println(node.GetNodeID());
        }
    }

}
