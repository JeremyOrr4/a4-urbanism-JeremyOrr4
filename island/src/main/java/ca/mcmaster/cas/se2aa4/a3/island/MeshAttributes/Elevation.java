package ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Elevation{

    public String GenerateElevation() {
        Random random = new Random();
        return ""+random.nextInt(300);
    }

    public Structs.Mesh addPolyElevation(Structs.Mesh aMesh){
        List<Structs.Polygon> polys = aMesh.getPolygonsList();
        List<Structs.Vertex> verts = aMesh.getVerticesList();
        List<Structs.Segment> segs = aMesh.getSegmentsList();
        List<Structs.Polygon> polysNew = new ArrayList<>();

        for (Structs.Polygon p: polys){
            if (Tiles.getTileType(p).equals("Land")){
                int ElevTrack=0;
                int vertCount=0;
                for (int n: p.getSegmentIdxsList()){
                    ElevTrack += getVertexElevation(verts.get(segs.get(n).getV1Idx()));
                    ElevTrack += getVertexElevation(verts.get(segs.get(n).getV2Idx()));
                    vertCount+=2;
                }
                ElevTrack=ElevTrack/vertCount;
                Structs.Property Elev = Structs.Property.newBuilder().setKey("Elevation").setValue(""+ElevTrack).build();
                polysNew.add(Structs.Polygon.newBuilder(p).addProperties(Elev).build());
            }else{
                polysNew.add(p);
            }
        }
        return Structs.Mesh.newBuilder().addAllVertices(verts).addAllSegments(segs).addAllPolygons(polysNew).build();
    }

    public int getVertexElevation(Structs.Vertex v){
        List<Structs.Property> Props = v.getPropertiesList();
        for (Structs.Property prop: Props){
            if(prop.getKey().equals("Elevation")){
                return Integer.parseInt(prop.getValue());
            }
        }
        return 0;

    }

    public int getPolyElevation(Structs.Polygon p){
        List<Structs.Property> Props = p.getPropertiesList();
        for (Structs.Property prop: Props){
            if(prop.getKey().equals("Elevation")){
                return Integer.parseInt(prop.getValue());
            }
        }
        return 0;

    }
}
