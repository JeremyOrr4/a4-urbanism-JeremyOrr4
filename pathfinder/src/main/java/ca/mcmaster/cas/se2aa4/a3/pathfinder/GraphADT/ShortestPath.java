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

public class ShortestPath implements PathAlgorithms {

    public List<Node> findPathBetweenNode(GraphADT graph, Node startNode, Node endNode) {
        Map<Node, Double> distance = new HashMap<>();
        Map<Node, Node> parentNodes = new HashMap<>();

        for (Node node : graph.AdjacencyList.keySet()) {
            distance.put(node, Double.MAX_VALUE);
        }
        distance.put(startNode, 0.0);

        Set<Node> visited = new HashSet<>();

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return (int) (distance.get(node1) - distance.get(node2));
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
                    double newDistance = distance.get(node) + Math.sqrt(Math.pow(node.GetXCoordinate() - adjacentNode.GetXCoordinate(),2) 
                                                    + Math.pow(node.GetYCoordinate() - adjacentNode.GetYCoordinate(),2));
                    if (newDistance < distance.get(adjacentNode)) {
                        distance.put(adjacentNode, newDistance);
                        parentNodes.put(adjacentNode, node);
                        pq.remove(adjacentNode);
                        pq.add(adjacentNode);
                    }
                }
            }
        }

        List<Node> shortestPath = new ArrayList<>();
        Node currentNode = endNode;
        while (parentNodes.containsKey(currentNode)) {
            shortestPath.add(currentNode);
            currentNode = parentNodes.get(currentNode);
        }
        shortestPath.add(startNode);
        Collections.reverse(shortestPath);

        return shortestPath;
    }
}
