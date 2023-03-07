import ca.mcmaster.cas.se2aa4.a2.generator.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;

public class circle implements shape {
    public ArrayList<Structs.Polygon> shapeFunction(ArrayList<Structs.Polygon> polys, ArrayList<Structs.Vertex> vertices) {
        int MidX = 1920/2; //HARD CODED RIGHT NOW we need to chang eventually to accomodate grid size
        int MidY = 1080/2;
        int polyCount = 0;
        for (Structs.Polygon p : polys) {
            //radius is arbitrary, check if polygon centroids are inside given circle function
            if (Math.pow(MidX - vertices.get(p.getCentroidIdx()).getX(), 2) + Math.pow(MidY - vertices.get(p.getCentroidIdx()).getY(), 2) < Math.pow(500, 2)) {
                Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue("201,185,151").build(); //make polygon brown
                polys.set(polyCount, Structs.Polygon.newBuilder(p).addProperties(color).build());
            }else{
                Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue("0,0,220").build(); //make polygon blue
                polys.set(polyCount, Structs.Polygon.newBuilder(p).addProperties(color).build());
            }
            polyCount += 1;
        }
        return polys;
    }
}
