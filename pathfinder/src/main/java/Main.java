import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.locationtech.jts.index.VertexSequencePackedRtree;
import org.testng.Assert.ThrowingRunnable;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.*;
import ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT.GraphADT;

public class Main {
    public static void main(String[] args) throws IOException {
        // Get mesh in the Thing 
        // Get the Vertices number
        String mesh = "img/irregular.mesh";
        Structs.Mesh Mesh = new MeshFactory().read(mesh);
        ArrayList<Integer> SegmentsIDsList = new ArrayList<Integer>();
        
        // double Vertex1X;
        // double Vertex1Y;
        // double Vertex2X;
        // double Vertex2Y;


        // for (Structs.Polygon Polygon : Mesh.getPolygonsList()) {
        //     for(int SegmentID : Polygon.getSegmentIdxsList()){
        //         Structs.Segment s = Mesh.getSegments(SegmentID);

        //         Structs.Vertex V1 = Mesh.getVertices(s.getV1Idx());
        //         Structs.Vertex V2 = Mesh.getVertices(s.getV2Idx());

        //         Vertex1X = V1.getX();
        //         Vertex1Y = V1.getY();
        //         Vertex2X = V2.getY();
        //         Vertex2Y = V2.getY();

        //     }

        // }


        GraphADT Graph = new GraphADT(6000); // Replace with vertices size of list

        Graph.CreateWholeGraph(Mesh);
        System.out.println(Arrays.deepToString(Graph.AdjacencyMatrix));
    }
}
