package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;
/**Utility class which provides access to tile attributes**/
public class Extractor {
    public static int MeshHeight;
    public static int MeshWidth;

    public static int MinDimension;
    public static double getVertexElevation(Structs.Vertex v){
        List<Structs.Property> Props = v.getPropertiesList();
        for (Structs.Property prop: Props){
            if(prop.getKey().equals("Elevation")){
                return Double.parseDouble(prop.getValue());
            }
        }
        return 0;

    }

    public static double getPolyElevation(Structs.Polygon p){
        List<Structs.Property> Props = p.getPropertiesList();
        for (Structs.Property prop: Props){
            if(prop.getKey().equals("Elevation")){
                return Double.parseDouble(prop.getValue());
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

    public static boolean isRiver(Structs.Segment s){
        List<Structs.Property> Props = s.getPropertiesList();
        for (Structs.Property prop: Props){
            if(prop.getKey().equals("IsRiver")){
                return true;
            }
        }
        return false;

    }
}
