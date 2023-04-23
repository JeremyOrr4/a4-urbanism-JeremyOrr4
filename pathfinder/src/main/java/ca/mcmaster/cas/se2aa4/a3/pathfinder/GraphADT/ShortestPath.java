package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;

import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.Collections;
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


//Shorest path algorithm for our GraphADT
public class ShortestPath implements PathAlgorithms {

    public List<Node> findPathBetweenNode(GraphADT Graph, Node StartNode, Node EndNode) {
        Map<Node, Double> Distance = new HashMap<>();
        Map<Node, Node> ParentNodes = new HashMap<>();

        for (Node node : Graph.AdjacencyList.keySet()) {
            Distance.put(node, Double.MAX_VALUE);
        }
        Distance.put(StartNode, 0.0);

        Set<Node> Visited = new HashSet<>();

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return (int) (Distance.get(node1) - Distance.get(node2));
            }
        });

        pq.add(StartNode);

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            Visited.add(node);

            if (node.equals(EndNode)) {
                break;
            }

            for (Node AdjacentNode : Graph.AdjacencyList.get(node)) {
                if (!Visited.contains(AdjacentNode)) {
                    double newDistance = Distance.get(node) + Distance.get(node) + Math.abs(node.GetXCoordinate() - AdjacentNode.GetXCoordinate()) 
                                        + Math.abs(node.GetYCoordinate() - AdjacentNode.GetYCoordinate());
                                                    
                    if (newDistance < Distance.get(AdjacentNode)) {
                        Distance.put(AdjacentNode, newDistance);
                        ParentNodes.put(AdjacentNode, node);
                        pq.remove(AdjacentNode);
                        pq.add(AdjacentNode);
                    }
                }
            }
        }

        List<Node> ShortestPath = new ArrayList<>();
        Node CurrentNode = EndNode;
        while (ParentNodes.containsKey(CurrentNode)) {
            ShortestPath.add(CurrentNode);
            CurrentNode = ParentNodes.get(CurrentNode);
        }
        ShortestPath.add(StartNode);
        Collections.reverse(ShortestPath);

        return ShortestPath;
    }
}
