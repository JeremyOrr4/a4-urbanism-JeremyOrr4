package ca.mcmaster.cas.se2aa4.a3.island.IslandWater;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class riverFactory {
    public Structs.Mesh riverGenerator(Structs.Mesh aMesh,int riverNum){
        List<Structs.Segment> segs = new ArrayList<>();
        Random bag = new Random();
        for (Structs.Segment s: aMesh.getSegmentsList()){
            segs.add(s);
        }
        for (Structs.Polygon p: aMesh.getPolygonsList()){
            if (Tiles.getTileType(p).equals("Land")|| Tiles.getTileType(p).equals("tropical")){
                for (int n: p.getSegmentIdxsList()){
                    Structs.Segment s = aMesh.getSegmentsList().get(n);
                    if (Extractor.getVertexElevation(aMesh.getVerticesList().get(s.getV1Idx()))>100 && riverNum>0 && bag.nextInt()>90){
                        if (!isRiver(s)){
                            s = SetasRiver(s);
                            riverNum-=1;
                            segs.set(n,s);
                        }
                    }
                }
            }
        }
        return Structs.Mesh.newBuilder().addAllVertices(aMesh.getVerticesList()).addAllSegments(segs).addAllPolygons(aMesh.getPolygonsList()).build();
    }

    public Structs.Segment SetasRiver(Structs.Segment s){
        Structs.Property isRiver = Structs.Property.newBuilder().setKey("IsRiver").setValue("True").build();
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue("0,0,255").build();
        return Structs.Segment.newBuilder(s).addProperties(isRiver).addProperties(color).build();
    }

    public boolean isRiver(Structs.Segment s){
        List<Structs.Property> Props = s.getPropertiesList();
        for (Structs.Property prop: Props){
            if(prop.getKey().equals("IsRiver")){
                return true;
            }
        }
        return false;

    }
}
