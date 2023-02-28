package ca.mcmaster.cas.se2aa4.a2.generator;
import java.io.IOException;
import java.util.*;

import ca.mcmaster.MeshGenerator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.locationtech.jts.geom.Coordinate;
import java.awt.Color;

import java.util.ArrayList;

public class MeshData {
    //array lists to hold all mesh data
    public List<Vertex> vertexData= new ArrayList<>();
    public List<Segment> segmentData=new ArrayList<>();
    public List<Polygon> polygonData=new ArrayList<>();

    public Vertex createVertex(int x,int y, double offset, Random random){
        //create a vertex based on x and y values and a random offset (set to zero currently as grid mesh is purely square)
        Vertex v = (Vertex.newBuilder().setX((double) x + offset * (random.nextDouble(15)- random
                .nextDouble(15))).setY((double) y + offset * (random.nextDouble(15) - random
                .nextDouble(15)) ).build());
        //add to list and return vertex
        this.vertexData.add(v);
        return v;
    }

    public Polygon createPolygon(int S1,int S2,int S3,int S4){
        //given four segment values, add to a list and build a polygon with given list
        ArrayList<Integer> segmentID = new ArrayList<>();
        segmentID.add(S1);
        segmentID.add(S2);
        segmentID.add(S3);
        segmentID.add(S4);
        Polygon p = Polygon.newBuilder().addAllSegmentIdxs(segmentID).build();
        return p;
    }

    //create vertex given x and y info in a coordinate
    public Vertex createVertex(Coordinate c){return (Vertex.newBuilder().setX(c.x).setY(c.y).build());}

    //create segment given V1 and V2 indexes
    public Segment createSegment(int V1, int V2){return (Segment.newBuilder().setV1Idx(V1).setV2Idx(V2).build()); }

    //create polygon given a list of segment ID's, a centroid ID and a list of neighbour ID's
    public Polygon createPolygon(List<Integer> segId,int CentroidID,List<Integer> neighbours){return Polygon.newBuilder().addAllSegmentIdxs(segId).setCentroidIdx(CentroidID).addAllNeighborIdxs(neighbours).build();}

    //create polygon given an original polygon and a centroid id to be added
    public Polygon createPolygon(Polygon p,int centroid){return Polygon.newBuilder().addAllSegmentIdxs(p.getSegmentIdxsList()).setCentroidIdx(centroid).build();}



}
