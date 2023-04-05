package ca.mcmaster.cas.se2aa4.a3.island.City;


import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.GraphADT;
import java.util.ArrayList;
import java.util.List;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.Node;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.ShortestPath;

public class PathFinderEntry {
    public Structs.Mesh CreateCity(Structs.Mesh Mesh){
        // String mesh = "img/irregular.mesh";
        // Structs.Mesh Mesh = new MeshFactory().read(mesh);

        GraphADT Graph = new GraphADT();

        CreateCity CityGraphCreator = new CreateCity();
        List<Node> NodeList = new ArrayList<Node>();
        NodeList = CityGraphCreator.CreateCityGraph(Mesh,Graph);

        ShortestPath Path = new ShortestPath();
        List<Node> shortestDistance = Path.findPathBetweenNode(Graph, NodeList.get(2), NodeList.get(4));
        
        List<Segment> SegmentListOfPath = new ArrayList<>();
        for (Segment s: Mesh.getSegmentsList()){
            SegmentListOfPath.add(s);
        }
        SegmentListOfPath = CityGraphCreator.GetSegmentPathList(shortestDistance, SegmentListOfPath,Mesh, Graph);

        // for (Segment s : SegmentListOfPath) {
        //     System.out.println("Segemnt: " + s.getV1Idx());
        // }   
        
        
        
        // for (Node node : shortestDistance) {
        //     System.out.println("Node List: "+ node.GetNodeID());
        // }

        return Structs.Mesh.newBuilder().addAllVertices(Mesh.getVerticesList()).addAllSegments(SegmentListOfPath)
                .addAllPolygons(Mesh.getPolygonsList()).build();  





    }
}
