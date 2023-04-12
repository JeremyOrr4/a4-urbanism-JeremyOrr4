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

    public List<Node> findPathBetweenNode(GraphADT graph, Node startNode, Node endNode) {
        Map<Node, Double> Distance = new HashMap<>();
        Map<Node, Node> ParentNodes = new HashMap<>();

        for (Node node : graph.AdjacencyList.keySet()) {
            Distance.put(node, Double.MAX_VALUE);
        }
        Distance.put(startNode, 0.0);

        Set<Node> visited = new HashSet<>();

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return (int) (Distance.get(node1) - Distance.get(node2));
            }
        });

        pq.add(startNode);

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            visited.add(node);

            if (node.equals(endNode)) {
                break;
            }

            for (Node adjacentNode : graph.AdjacencyList.get(node)) {
                if (!visited.contains(adjacentNode)) {
                    double newDistance = Distance.get(node) + Math.sqrt(Math.pow(node.GetXCoordinate() - adjacentNode.GetXCoordinate(),2) 
                                                    + Math.pow(node.GetYCoordinate() - adjacentNode.GetYCoordinate(),2));
                                                    
                    if (newDistance < Distance.get(adjacentNode)) {
                        Distance.put(adjacentNode, newDistance);
                        ParentNodes.put(adjacentNode, node);
                        pq.remove(adjacentNode);
                        pq.add(adjacentNode);
                    }
                }
            }
        }

        List<Node> shortestPath = new ArrayList<>();
        Node currentNode = endNode;
        while (ParentNodes.containsKey(currentNode)) {
            shortestPath.add(currentNode);
            currentNode = ParentNodes.get(currentNode);
        }
        shortestPath.add(startNode);
        Collections.reverse(shortestPath);

        return shortestPath;
    }
}
