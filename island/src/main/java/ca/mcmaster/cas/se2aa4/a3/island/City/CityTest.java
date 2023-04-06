package ca.mcmaster.cas.se2aa4.a3.island.City;


import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.junit.*;

import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.Node;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.Edge;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.GraphADT;


public class CityTest {
    public Node SetUpNode(int NodeID){
        Node NewNode = new Node(NodeID,1,1);

        return NewNode;
    }

    public Edge SetUpEdge (Node StartNode, Node EndNode){
        Edge NewEdge = new Edge(StartNode, EndNode, 1);

        return NewEdge;
    }

    public GraphADT SetUpGraph(Node node1, Node node2,Node node3,Node node4){
        GraphADT newGraph = new GraphADT();

        newGraph.AddEdge(node1, node2);
        newGraph.AddEdge(node2, node3);
        newGraph.AddEdge(node3, node4);

        return newGraph;
    }

    public Set<Node> SetUpPath(GraphADT NewGraph, Node node){
        return NewGraph.GetNodesConnectedSet(node);

    }


    public void TestNodeCreation(){
        System.out.println("Testing Node Creation");
        Node context1 = SetUpNode(0);
        Node context2 = SetUpNode(1);

        Assert.assertEquals(context1.GetNodeID(), new Node(0, 1, 1).GetNodeID());
        Assert.assertEquals(context2.GetNodeID(),new Node(1, 1, 1).GetNodeID());
        System.out.println("Test Passed! -> Nodes Created");
    }
    
    public void TestEdgeCreation(){
        System.out.println("Testing Edge Creation");
        Node node1 = SetUpNode(0);
        Node node2 = SetUpNode(1);
        Edge context = new Edge(node1, node2, 1);
        Assert.assertEquals(context.GetStartNode().GetNodeID(), new Edge(node1, node2, 1).GetStartNode().GetNodeID());
        System.out.println("Test Passed! -> Edge Created");
    }

    public void TestGraphandPath(){
        Node node1 = new Node(1, 0, 0);
        Node node2 = new Node(1, 0, 0);
        Node node3 = new Node(1, 0, 0);
        Node node4 = new Node(1, 0, 0);
        GraphADT context = new GraphADT();
        context.AddEdge(node1, node2);
        context.AddEdge(node2, node3); 
        context.AddEdge(node3, node4); 

        GraphADT NewGraph = SetUpGraph( node1,  node2, node3, node4);
        System.out.println("Testing Graph Creation");
        Assert.assertEquals(context.getClass(), NewGraph.getClass());
        System.out.println("Test Passed! -> Graph Created");

        Set<Node> context2 = NewGraph.GetNodesConnectedSet(node1);
        System.out.println("Testing Path Creation");
        Assert.assertEquals(context2, SetUpPath(NewGraph, node1));
        System.out.println("Test Passed! -> Correct Created");

    }

    public void TestSuite(){
        TestNodeCreation();
        TestEdgeCreation();
        TestGraphandPath();
    }

}
