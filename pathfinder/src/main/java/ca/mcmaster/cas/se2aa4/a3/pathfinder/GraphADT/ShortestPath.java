package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;

import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class ShortestPath implements PathAlgorithms {

    public Map<Node, Integer> findPath(GraphADT graph, Node startNode) {
        
        Map<Node, Integer> distance = new HashMap<>();
        
        for (Node node : graph.AdjacencyList.keySet()) {
            distance.put(node, Integer.MAX_VALUE);
        }
        distance.put(startNode, 0);

        Set<Node> visited = new HashSet<>();

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return distance.get(node1) - distance.get(node2);
            }
        });

        pq.add(startNode);

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            visited.add(node);

            for (Node adjacentNode : graph.AdjacencyList.get(node)) {
                if (!visited.contains(adjacentNode)) {
                    int newDistance = distance.get(node) + 1;
                    if (newDistance < distance.get(adjacentNode)) {
                        distance.put(adjacentNode, newDistance);
                        pq.remove(adjacentNode);
                        pq.add(adjacentNode);
                    }
                }
            }
        }

        return distance;
    }
















    // public List<Node> findPath(GraphADT Graph, Node startNode, Node endNode){
        
        
    //     HashMap<Node, Double> cost = new HashMap<>();
    //     HashMap<Node, Integer> path = new HashMap<>();


    //     for (Node i : Graph.AdjacencyList.keySet()){
    //         cost.put(i,1000.0);
    //         path.put(i,0);
    //     }

    //     cost.replace(startNode,0.0);
    //     path.replace(startNode,startNode.GetNodeID());

    //     PriorityQueue<Double> Q = new PriorityQueue<>();
    //     List<Node> nodeHolder = new ArrayList<>();

    //     Q.add(cost.get(startNode));
    //     nodeHolder.add(startNode);

    //     while(Q.size() != 0){
    //         double current = Q.remove();
    //         int k = 0;
    //         for (Node n : nodeHolder){
    //             if (cost.get(n.GetNodeID()) == current){                   
    //                     k = n.GetNodeID();
    //                     nodeHolder.remove(n);
    //                     break;
                      
    //             }
    //         }
            
    //         for (Edge e : Graph.GetNodesConnectedSetAsEdge(Node.get())){
    //             if (e.GetStartNode() == k && cost.get(e.GetStartNode()) +e.Weight < cost.get(e.GetEndNode())) {
    //                 Q.remove(cost.get(e.GetStartNode()));
    //                 path.replace(e.GetEndNode()), e.GetStartNode());
    //                 cost.replace(e.GetEndNode(),cost.get(e.GetStartNode()) + e.Weight);
    //                 Q.add(cost.get(e.GetEndNode()));
    //                 nodeHolder.add(Graph.Nodes.get(e.GetEndNode()));
    //             }
    //             else if(e.GetEndNode() == k && cost.get(e.GetEndNode()) + e.Weight < cost.get(e.GetStartNode())){
    //                 Q.remove(cost.get(e.GetStartNode()));
    //                 path.replace(e.GetEndNode()), e.GetEndNode());
    //                 cost.replace(e.GetEndNode(),cost.get(e.GetEndNode()) + e.Weight);
    //                 Q.add(cost.get(e.GetStartNode()));
    //                 nodeHolder.add(Graph.Nodes.get(e.GetStartNode()));
    
    
    //             }
    //         }
    //     }

        

    //     List<Integer> Route = new ArrayList<>();
    //     Route.add(end.NodeID)
    //     int tracer = end.NodeID;

    //     int i = 0;

    //     while (i<10){
    //         tracer = path.get(tracer);
    //         Route.add(tracer);
    //         Route.add(tracer);
    //         i++;
    //     }
    //     return Route;


    //     // ArrayList<Integer> path = new ArrayList<Integer>();
    //     // ArrayList<Integer> cost = new ArrayList<Integer>();

        // path.set(startNode.GetNodeID(),0);
        // cost.set(startNode.GetNodeID(),0);

        // PriorityQueue<Integer> PriorityQueue = new PriorityQueue<Integer>();

        // PriorityQueue.add(startNode);

        // while (PriorityQueue.isEmpty() == true){
        //     for(Node LowestWeightNode : Get Nodes wieght){
        //         if{
        //             weight is Min
        //             store the node as RemovedNode
        //         }
        //     }
        //     PriorityQueue.remove(RemovedNode);

        //     for all edges that ndoe has in adjaceny list{
        //         if cost of Removed node
        //     }
        // }
        // path = underfined NUM;
        // cost = infitie Number

        // path first is start
        // cost is 0 at first Node

        // Minimum Pirotity QUeue that holds node s with priorty of 0

        // while queie is not EmptyNodeSet
        //     Remove node m with lwoest prioty of QUeue

        //     for all edges from m to n 

        //     if cost of node m + weight of m to n < cost of n
        //         path at n =m and cost at n = weigth((m,n + cost at m))

    //     //         update n in Q that has prioty cost n in Q
    // }
}
