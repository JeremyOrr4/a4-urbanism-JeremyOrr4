package ca.mcmaster.cas.se2aa4.a3.island.City;


import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.GraphADT;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.Node;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.PathAlgorithms;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.ShortestPath;

public class PathFinderEntry {
    public Structs.Mesh CreateCity(Structs.Mesh Mesh, int StartNodeID, String citysize){
        GraphADT Graph = new GraphADT();

        Random random = new Random();

        CreateCity CityGraphCreator = new CreateCity();
        List<Node> NodeList = new ArrayList<Node>();
        NodeList = CityGraphCreator.CreateCityGraph(Mesh,Graph);

        Node CentreNode = NodeList.get(StartNodeID);

        int EndNodeID = random.nextInt(NodeList.size());
       
        PathAlgorithms Path = new ShortestPath();
        List<Node> shortestDistance = Path.findPathBetweenNode(Graph, CentreNode, NodeList.get(EndNodeID));

        List<Node> newShortestDistnace = new ArrayList<Node>();
        if (citysize.equalsIgnoreCase("small")){ 
            int NewLengthSegmentLength = (int) (shortestDistance.size()/5.0);

            for (int i = 0; i < NewLengthSegmentLength; i++){
                newShortestDistnace.add(shortestDistance.get(i));
            }
        }

        List<Segment> SegmentListOfPath = new ArrayList<>();
        for (Segment s: Mesh.getSegmentsList()){
            SegmentListOfPath.add(s);
        }

        if (citysize.equalsIgnoreCase("small")) {
        SegmentListOfPath = CityGraphCreator.GetSegmentPathList(newShortestDistnace, SegmentListOfPath,Mesh, Graph);
        }

        else{
            SegmentListOfPath = CityGraphCreator.GetSegmentPathList(shortestDistance, SegmentListOfPath,Mesh, Graph);
        }

        List<Vertex> ListOfVertices = new ArrayList<Vertex>();
        ListOfVertices = CityGraphCreator.AddColorToVertex(Mesh,CentreNode);

        return Structs.Mesh.newBuilder().addAllVertices(ListOfVertices).addAllSegments(SegmentListOfPath)
                .addAllPolygons(Mesh.getPolygonsList()).build();  

    }
}
