package ca.mcmaster.cas.se2aa4.a2.generator;
import java.io.IOException;
import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import java.util.ArrayList;

public class MeshData {
    public ArrayList<Vertex> vertexData= new ArrayList<>();
    public ArrayList<Segment> segmentData=new ArrayList<>();
    public ArrayList<Polygon> polygonData=new ArrayList<>();

    public void vertexCreate(int x,int y, double offset, Random random){
        Vertex v = (Vertex.newBuilder().setX((double) x + offset * (random.nextDouble(15)- random
                .nextDouble(15))).setY((double) y + offset * (random.nextDouble(15) - random
                .nextDouble(15)) ).build());
        this.vertexData.add(v);
    }
    public Segment segmentCreate(int v1, int v2){
        Segment s = (Segment.newBuilder().setV1Idx(v1).setV2Idx(v2).build());
        return s;
    }

    public Polygon polygonCreate(int k,int size,int last_row){
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

    public Vertex AddVertexProperties(Vertex v,String thicknessString){
        Random bag = new Random();
        int red = bag.nextInt(255);
        int green = bag.nextInt(255);
        int blue = bag.nextInt(255);
        String colorCode = red + "," + green + "," + blue;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        Property thickness = Property.newBuilder().setKey("thickness").setValue(thicknessString).build();

        Vertex thickened = Vertex.newBuilder(v).addProperties(thickness).build();
        Vertex colored = Vertex.newBuilder(thickened).addProperties(color).build();
        return colored;
    }

    public Segment AddSegmentProperties(Segment s, String thicknessString){
        //Assign segment color based on average of associated vertices
        Property color = DotGen.averageColor(vertexData.get(s.getV1Idx()),vertexData.get(s.getV2Idx()));
        Property thickness = Property.newBuilder().setKey("thickness").setValue(thicknessString).build();

        Segment thickened =  Segment.newBuilder(s).addProperties(thickness).build();
        Segment colored = Segment.newBuilder(thickened).addProperties(color).build();
        return colored;

    }


}
