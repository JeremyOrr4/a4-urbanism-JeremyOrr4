package ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.triangulate.polygon.PolygonHoleJoiner;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class SoilAbsorption {
    String SoilAbsorptionValue;
    String IntensityFactor; // Will itegrate alter using command line

    public SoilAbsorption() {
       SoilAbsorptionValue = "";    
       IntensityFactor = "";    
    }
    
    public Structs.Polygon AddSoilAbsorption (Structs.Polygon polygon, String Soil){
        Structs.Property SoilAbsorption = Structs.Property.newBuilder().setKey("SoilAbsorption").setValue(
                SoilAbsorptionValue).build();
        return Structs.Polygon.newBuilder(polygon).addProperties(SoilAbsorption).build();
    }
    
    // What I am doing is trying to set polygon as the next polygon
    
    public String GenerateSoilAbsorption(Structs.Mesh aMesh, Structs.Polygon polygon) {
        int distanceFromWater = 0;
        List <Integer> polygonNeighbourList = new ArrayList<Integer>();

        if (Tiles.getTileType(polygon).equals("Lake")){
            return distanceFromWater + "";
        }
    
        // while ((GetNeighbourTileType(aMesh, polygon) == false)){
        //     distanceFromWater++;
        //     polygonNeighbourList = polygon.getNeighborIdxsList(); 

        //     polygon = polygon.getNeighborIdxs(polygonNeighbourList.get(0));
        // }

        return distanceFromWater + "";
        
    }

    public Boolean GetNeighbourTileType (Structs.Mesh aMesh, Structs.Polygon polygon) {
        Boolean TileIsLake = false;

        List<Structs.Polygon> polys = aMesh.getPolygonsList();

        for (int i : polygon.getNeighborIdxsList()) {
            if (Tiles.getTileType(polys.get(i)).equals("Lake")) {
                TileIsLake = true;
            }
        }

        return TileIsLake;
    }

    public Structs.Mesh MasterSoilAbsoption (Structs.Mesh aMesh){
        List<Structs.Polygon> polys = aMesh.getPolygonsList();
        List<Structs.Polygon> polysNew = new ArrayList<>();
        
        for (Structs.Polygon p: polys){
            String SoilAbsoprtion = GenerateSoilAbsorption(aMesh,p);
            p = AddSoilAbsorption(p, SoilAbsoprtion);
            polysNew.add(p);
        }

        return Structs.Mesh.newBuilder().addAllVertices(aMesh.getVerticesList()).addAllSegments(aMesh.getSegmentsList()).addAllPolygons(polysNew).build();
    }

}
