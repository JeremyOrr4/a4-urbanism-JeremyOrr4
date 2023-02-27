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
    public List<Vertex> vertexData= new ArrayList<>();
    public List<Segment> segmentData=new ArrayList<>();
    public List<Polygon> polygonData=new ArrayList<>();

    public void createVertex(int x,int y, double offset, Random random){
        Vertex v = (Vertex.newBuilder().setX((double) x + offset * (random.nextDouble(15)- random
                .nextDouble(15))).setY((double) y + offset * (random.nextDouble(15) - random
                .nextDouble(15)) ).build());
        this.vertexData.add(v);
    }

    public Polygon createPolygon(int k,int size,int last_row){
        ArrayList<Integer> segmentID = new ArrayList<>();
        if (k>size-74)k+=1;
        segmentID.add(k);

        segmentID.add(k+1);

        segmentID.add(k+3);
        if (k>size-76){
            segmentID.add(k+50-last_row);
        }else{
            segmentID.add(k+51);
        }
        Polygon p = Polygon.newBuilder().addAllSegmentIdxs(segmentID).build();
        return p;
    }

    public Polygon createPolygon(int S1,int S2,int S3,int S4){
        ArrayList<Integer> segmentID = new ArrayList<>();
        segmentID.add(S1);
        segmentID.add(S2);
        segmentID.add(S3);
        segmentID.add(S4);
        Polygon p = Polygon.newBuilder().addAllSegmentIdxs(segmentID).build();
        return p;
    }

    public Vertex createVertex(Coordinate c){return (Vertex.newBuilder().setX(c.x).setY(c.y).build());}

    public Segment createSegment(int V1, int V2){return (Segment.newBuilder().setV1Idx(V1).setV2Idx(V2).build()); }

    public Polygon createPolygon(List<Integer> segId,int CentroidID,List<Integer> neighbours){return Polygon.newBuilder().addAllSegmentIdxs(segId).setCentroidIdx(CentroidID).addAllNeighborIdxs(neighbours).build();}



}
