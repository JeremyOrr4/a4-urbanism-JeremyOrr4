package ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class Humidity {

    public Structs.Polygon addHumidity(Structs.Polygon p, String HumidityValue){
        Structs.Property Humidity = Structs.Property.newBuilder().setKey("Humidity").setValue(HumidityValue).build();
        return Structs.Polygon.newBuilder(p).addProperties(Humidity).build();
    }

    public String GenerateHumidities(Structs.Mesh aMesh,Structs.Polygon p){
        List<Structs.Polygon> polys = aMesh.getPolygonsList();
        for (int i: p.getNeighborIdxsList()){
            if (Tiles.getTileType(polys.get(i)).equals("Lake")){
                return "629";
            }
        }
        return "300";
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
