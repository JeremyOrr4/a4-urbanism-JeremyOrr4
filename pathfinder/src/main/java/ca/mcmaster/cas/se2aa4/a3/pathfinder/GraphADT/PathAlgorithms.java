package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;

import java.util.Set;

public interface PathAlgorithms {
    public Set<Node> findPathBetweenNode(GraphADT graph, Node startNode, Node EndNode);
}
