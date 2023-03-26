package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/** Creates elevations for vertices based on given profile and distributes elevations to polygons**/
public class Elevation{
  

    public static Structs.Mesh SetVertexElevation(Structs.Mesh aMesh, ElevationProfile profile){
        List<Structs.Vertex> NewVertices = new ArrayList<>();
        Elevation ElevSetter = new Elevation();
        for (Structs.Vertex v: aMesh.getVerticesList()){
            Structs.Property Elevation = Structs.Property.newBuilder().setKey("Elevation").setValue(""+profile.ProduceElevation(v.getX(), v.getY(), 1920/2,1080/2 )).build();
            NewVertices.add(Structs.Vertex.newBuilder(v).addProperties(Elevation).build());
        }
        return Structs.Mesh.newBuilder().addAllVertices(NewVertices).addAllSegments(aMesh.getSegmentsList()).addAllPolygons(aMesh.getPolygonsList()).build();
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
