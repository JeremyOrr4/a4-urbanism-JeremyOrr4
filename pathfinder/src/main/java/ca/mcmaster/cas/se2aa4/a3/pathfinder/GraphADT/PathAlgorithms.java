package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;

import java.util.List;

public interface PathAlgorithms {
    public List<Node> findPathBetweenNode(GraphADT graph, Node startNode, Node endNode);
}
