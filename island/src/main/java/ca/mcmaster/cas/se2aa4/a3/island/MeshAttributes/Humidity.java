package ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Humidity {

    public Structs.Polygon addHumidity(Structs.Polygon p, String HumidityValue){
        Structs.Property Humidity = Structs.Property.newBuilder().setKey("Humidity").setValue(HumidityValue).build();
        return Structs.Polygon.newBuilder(p).addProperties(Humidity).build();
    }

    public String GenerateHumidities(Structs.Mesh aMesh,Structs.Polygon p){
        Random bad = new Random();
        int baseHumidity = 200;
        List<Structs.Polygon> polys = aMesh.getPolygonsList();
        List<Structs.Segment> segs = aMesh.getSegmentsList();
        for (int i: p.getNeighborIdxsList()){
            if (Tiles.getTileType(polys.get(i)).equals("Lake")){
                baseHumidity+=(100+bad.nextInt(100));
            }
        }
        for (int j: p.getSegmentIdxsList()){
            if (Extractor.isRiver(segs.get(j))){
                baseHumidity+=(50+bad.nextInt(100));
            }
        }
        if (baseHumidity==200)baseHumidity+=200;
        return ""+baseHumidity;
    }

    public Structs.Mesh MasterHumidity(Structs.Mesh aMesh){
        List<Structs.Polygon> polys = aMesh.getPolygonsList();
        List<Structs.Polygon> polysNew = new ArrayList<>();
        for (Structs.Polygon p: polys){
            String Humidity = GenerateHumidities(aMesh,p);
            p=addHumidity(p,Humidity);
            polysNew.add(p);
        }
        return Structs.Mesh.newBuilder().addAllVertices(aMesh.getVerticesList()).addAllSegments(aMesh.getSegmentsList()).addAllPolygons(polysNew).build();
    }
}
