package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;


import java.util.Map;

public interface PathAlgorithms {

    public Map<Node, Integer> findPath(GraphADT graph, Node startNode);
}
