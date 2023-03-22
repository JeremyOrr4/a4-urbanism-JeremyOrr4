package ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Elevation{

    public String GenerateElevation() {
        Random random = new Random();
        int str = random.nextInt(600);
        return ""+str;
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
                    ElevTrack += Extractor.getVertexElevation(verts.get(segs.get(n).getV1Idx()));
                    ElevTrack += Extractor.getVertexElevation(verts.get(segs.get(n).getV2Idx()));
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
}
