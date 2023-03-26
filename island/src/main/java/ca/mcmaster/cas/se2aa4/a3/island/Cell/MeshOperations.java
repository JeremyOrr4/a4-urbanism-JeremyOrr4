package ca.mcmaster.cas.se2aa4.a3.island.Cell;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

public class MeshOperations {

    public static List<Integer> getVertexIdxs(Polygon p, Mesh mesh) {

        List<Integer> idxs = new ArrayList<Integer>();

        List<Vertex> vertices = mesh.getVerticesList();

        for (Integer i : p.getSegmentIdxsList()) {

            Segment s = mesh.getSegments(i);

            idxs.add(s.getV1Idx());
            idxs.add(s.getV2Idx());

        }

        return idxs;

    }


    public static List<Point> vertexToPoint(List<Vertex> vertices){

        List<Point> points = new ArrayList<Point>(); 

        for(Vertex v : vertices){

            points.add(new Point(v.getX(), v.getY())); 

        }

        return points; 


    }

}
