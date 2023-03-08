import ca.mcmaster.cas.se2aa4.a2.generator.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;

public class circle implements shape {
    public ArrayList<Structs.Polygon> shapeFunction(ArrayList<Structs.Polygon> polys, ArrayList<Structs.Vertex> vertices) {
        int MidX = 1920/2; //HARD CODED RIGHT NOW we need to chang eventually to accomodate grid size
        int MidY = 1080/2;
        for (int i=0;i<polys.size()-1;i++) {
            //radius is arbitrary, check if polygon centroids are inside given circle function
            if (Math.pow(MidX - vertices.get(polys.get(i).getCentroidIdx()).getX(), 2) + Math.pow(MidY - vertices.get(polys.get(i).getCentroidIdx()).getY(), 2) < Math.pow(500, 2)) {
                polys.set(i, tiles.setToLand(polys.get(i)));
            }else{
                polys.set(i, tiles.setToWater(polys.get(i)));
            }
        }
        for (int i=0; i<polys.size()-1;i++){
            if (tiles.getType(polys.get(i)).equals("Land")){
                polys.set(i,checkBeach(polys.get(i),polys));
            }
        }
        return polys;
    }

    public Structs.Polygon checkBeach(Structs.Polygon p,ArrayList<Structs.Polygon> polys){
        for (int i: p.getNeighborIdxsList()){
            if (tiles.getType(polys.get(i)).equals("Water")){
                return tiles.setToBeach(p);
            }
        }
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue("0,255,0").build();//make polygon brown
        Structs.Property type = Structs.Property.newBuilder().setKey("TileType").setValue("Beach").build();
        return Structs.Polygon.newBuilder(p).addProperties(color).addProperties(type).build();
    }
}
