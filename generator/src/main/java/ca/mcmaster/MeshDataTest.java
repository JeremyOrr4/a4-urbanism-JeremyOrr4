package ca.mcmaster;
import java.util.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;



import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

public class MeshDataTest {
    public ArrayList<Vertex> vertexData= new ArrayList<>();
    public ArrayList<Segment> segmentData=new ArrayList<>();
    public ArrayList<Polygon> polygonData=new ArrayList<>();

    public Vertex createVertex(Coordinate c){return (Vertex.newBuilder().setX(c.x).setY(c.y).build());}

    public Vertex randomized(Vertex v){return Vertex.newBuilder(v).addProperties(GenTest.randomColor()).build();}

    public Segment createSegment(int V1, int V2){return (Segment.newBuilder().setV1Idx(V1).setV2Idx(V2).build()); }

    public Polygon createPolygon(List<Integer> segId,int CentroidID){return Polygon.newBuilder().addAllSegmentIdxs(segId).setCentroidIdx(CentroidID).build();}

    public static Property randomColor(){

        Random bag = new Random();
        int red = bag.nextInt(255);
        int green = bag.nextInt(255);
        int blue = bag.nextInt(255);
        int alpha = bag.nextInt(255);



        String colorCode = String.format("%d,%d,%d,%d",red,green,blue,alpha);
        return Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();

    }



}
