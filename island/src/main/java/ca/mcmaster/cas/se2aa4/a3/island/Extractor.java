package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;

public class Extractor {

    public static int getVertexElevation(Structs.Vertex v){
        List<Structs.Property> Props = v.getPropertiesList();
        for (Structs.Property prop: Props){
            if(prop.getKey().equals("Elevation")){
                return Integer.parseInt(prop.getValue());
            }
        }
        return 0;

    }

    public static int getPolyElevation(Structs.Polygon p){
        List<Structs.Property> Props = p.getPropertiesList();
        for (Structs.Property prop: Props){
            if(prop.getKey().equals("Elevation")){
                return Integer.parseInt(prop.getValue());
            }
        }
        return 0;

    }

    public static int getPolyHumidity(Structs.Polygon p){
        List<Structs.Property> Props = p.getPropertiesList();
        for (Structs.Property prop: Props){
            if(prop.getKey().equals("Humidity")){
                return Integer.parseInt(prop.getValue());
            }
        }
        return 0;

    }

    public static boolean CompareElev(Structs.Vertex v1, Structs.Vertex v2){
        if (getVertexElevation(v1)>getVertexElevation(v2)){
            return true;
        }else{
            return false;
        }
    }
}
