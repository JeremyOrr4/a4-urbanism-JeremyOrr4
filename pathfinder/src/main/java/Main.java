import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.batik.parser.PathArrayProducer;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.Edge;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.GraphADT;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.Node;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.ShortestPath;

import java.lang.Math;

public class Main {
    public static void main(String[] args) throws IOException {
        String mesh = "img/irregular.mesh";
        Structs.Mesh Mesh = new MeshFactory().read(mesh);

        GraphADT Graph = new GraphADT();

        HashSet<Node> NodeSet = new HashSet<Node>();
        HashSet<Edge> EdgeSet= new HashSet<Edge>();


        int Vertex1;
        int Vertex2;

        double Vertex1XCoordinate;
        double Vertex1YCoordinate;
        double Vertex2XCoordinate;
        double Vertex2YCoordinate;

        Node FirstNode;
        Node SecondNode;

        Edge TempEdge;


        for (Structs.Polygon Polygons : Mesh.getPolygonsList()){ // Creating Nodes and Edges from Graph
            if (!(Tiles.getTileType(Polygons).equalsIgnoreCase("WATER"))){
                for (int IndidualSegmentsID : Polygons.getSegmentIdxsList()) {
                    Structs.Segment IndidualSegments = Mesh.getSegmentsList().get(IndidualSegmentsID);

                    Vertex1 = IndidualSegments.getV1Idx();
                    Structs.Vertex V1 = Mesh.getVertices(IndidualSegments.getV1Idx());
                    Vertex1XCoordinate = V1.getX();
                    Vertex1YCoordinate = V1.getY();
                    FirstNode = new Node(Vertex1, Vertex1XCoordinate,Vertex1YCoordinate);
                    NodeSet.add(FirstNode);

                    Vertex2 = IndidualSegments.getV2Idx();
                    Structs.Vertex V2 = Mesh.getVertices(IndidualSegments.getV2Idx());
                    Vertex2XCoordinate = V2.getY();
                    Vertex2YCoordinate = V2.getY();
                    SecondNode = new Node(Vertex2, Vertex2XCoordinate,Vertex2YCoordinate);
                    NodeSet.add(SecondNode);

                    // Create edge here
                    TempEdge = new Edge(FirstNode, SecondNode, Math.sqrt(Math.pow(Vertex2XCoordinate-Vertex1XCoordinate,2) + Math.pow(Vertex2YCoordinate-Vertex1YCoordinate,2)));
                    Graph.AddEdge(FirstNode,SecondNode);
                    EdgeSet.add(TempEdge);
                }

            }

        }

        List<Node> NodeList = new ArrayList<>(NodeSet);

        // for (Node n : NodeList){
        //     System.out.println(n.GetNodeID());
        // }

        // for (Edge e : EdgeSet){
        //     System.out.println(e.GetWeight());
        // }

        ShortestPath Path = new ShortestPath();

        List<Node> shortestDistances = Path.findPathBetweenNode(Graph, NodeList.get(2), NodeList.get(4));
        for (Node node : shortestDistances) {
            System.out.println("Hello" + node.GetNodeID());
        }

        //THE ADAJCENY LIST IS ADDING ITSELF TO THE LIST MEANING PATH IS ALL WEIRD IDK WHY
    }
}
