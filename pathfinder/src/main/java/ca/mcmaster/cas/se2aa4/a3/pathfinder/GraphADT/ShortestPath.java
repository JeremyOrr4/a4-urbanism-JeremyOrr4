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

    public Set<Node> findPathBetweenNode(GraphADT graph, Node startNode, Node EndNode) {
        
        Map<Node, Integer> distance = new HashMap<>();
        // Set<Node> path = new HashSet<>();

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

        Node node = new Node(-1);

        while (!pq.isEmpty() && node.GetNodeID() != EndNode.GetNodeID()) {
            node = pq.poll();
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

        return visited;
    }
}
