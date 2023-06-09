package ca.mcmaster.cas.se2aa4.a3.island.City;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.GraphADT;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.Node;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.Edge;

//Class to create the graph that will be implented by the city
public class CreateCity {

    public List<Node> CreateCityGraph(Structs.Mesh Mesh, GraphADT Graph) {
        HashSet<Node> NodeSet = new HashSet<Node>();
        HashSet<Edge> EdgeSet = new HashSet<Edge>();

        int Vertex1;
        int Vertex2;

        double Vertex1XCoordinate;
        double Vertex1YCoordinate;
        double Vertex2XCoordinate;
        double Vertex2YCoordinate;

        Node FirstNode;
        Node SecondNode;

        Edge TempEdge;

        for (Structs.Polygon Polygons : Mesh.getPolygonsList()) { // Creating Nodes and Edges from mesh to Graph
            if (!(Tiles.getTileType(Polygons).equalsIgnoreCase("WATER"))) {
                for (int IndidualSegmentsID : Polygons.getSegmentIdxsList()) {
                    Structs.Segment IndidualSegments = Mesh.getSegmentsList().get(IndidualSegmentsID);

                    Vertex1 = IndidualSegments.getV1Idx();
                    Structs.Vertex V1 = Mesh.getVertices(IndidualSegments.getV1Idx());
                    Vertex1XCoordinate = V1.getX();
                    Vertex1YCoordinate = V1.getY();
                    FirstNode = new Node(Vertex1, Vertex1XCoordinate, Vertex1YCoordinate);
                    NodeSet.add(FirstNode);

                    Vertex2 = IndidualSegments.getV2Idx();
                    Structs.Vertex V2 = Mesh.getVertices(IndidualSegments.getV2Idx());
                    Vertex2XCoordinate = V2.getY();
                    Vertex2YCoordinate = V2.getY();
                    SecondNode = new Node(Vertex2, Vertex2XCoordinate, Vertex2YCoordinate);
                    NodeSet.add(SecondNode);

                    // Create edge here
                    TempEdge = new Edge(FirstNode, SecondNode,
                            Math.sqrt(Math.pow(Vertex2XCoordinate - Vertex1XCoordinate, 2)
                                    + Math.pow(Vertex2YCoordinate - Vertex1YCoordinate, 2)));
                    Graph.AddEdge(FirstNode, SecondNode);
                    EdgeSet.add(TempEdge);
                }

            }

        }

        List<Node> NodeList = new ArrayList<>(NodeSet);
        return NodeList;
    }

    public List<Segment> GetSegmentPathList(List<Node> NodeList, List<Segment> SegmentList, Structs.Mesh Mesh,
            GraphADT Graph) {
        int Vertex1;
        int Vertex2;

        for (int i = 0; i < NodeList.size() - 1; i++) {
            for (Structs.Polygon Polygons : Mesh.getPolygonsList()) { // Creating Nodes and Edges from Graph
                if (!(Tiles.getTileType(Polygons).equalsIgnoreCase("WATER"))) {
                    for (int IndidualSegmentsID : Polygons.getSegmentIdxsList()) {
                        Structs.Segment IndidualSegments = Mesh.getSegmentsList().get(IndidualSegmentsID);

                        Vertex1 = IndidualSegments.getV1Idx();
                        Vertex2 = IndidualSegments.getV2Idx();

                        if (Vertex1 == NodeList.get(i).GetNodeID() && Vertex2 == NodeList.get(i + 1).GetNodeID()) {
                            Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue("0,0,0")
                                    .build();
                            IndidualSegments = Structs.Segment.newBuilder(IndidualSegments).addProperties(color)
                                    .build();
                            SegmentList.set(IndidualSegmentsID, IndidualSegments);

                        }

                        else if (Vertex2 == NodeList.get(i).GetNodeID() && Vertex1 == NodeList.get(i + 1).GetNodeID()) {
                            Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue("0,0,0")
                                    .build();
                            IndidualSegments = Structs.Segment.newBuilder(IndidualSegments).addProperties(color)
                                    .build();
                            SegmentList.set(IndidualSegmentsID, IndidualSegments);
                        }
                    }

                }

            }
        }

        return SegmentList;
    }

    public List<Vertex> AddColorToVertex(Structs.Mesh Mesh, Node BaseNode) {
        List<Vertex> NewVertexList = new ArrayList<Vertex>();

        for (Vertex vertex : Mesh.getVerticesList()) {
            if (Mesh.getVerticesList().indexOf(vertex) == BaseNode.GetNodeID()) {
                Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue("255,0,0").build();
                vertex = Structs.Vertex.newBuilder(vertex).addProperties(color).build();
                NewVertexList.add(vertex);
            } else {
                NewVertexList.add(vertex);
            }
        }

        return NewVertexList;
    }

}
