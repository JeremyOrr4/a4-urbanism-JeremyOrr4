import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class tiles {
    public static Structs.Polygon setToLand(Structs.Polygon p){
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue("201,185,151").build(); //make polygon brown
        Structs.Property type = Structs.Property.newBuilder().setKey("TileType").setValue("Land").build();
        return Structs.Polygon.newBuilder(p).addProperties(color).addProperties(type).build();
    }

    public static Structs.Polygon setToWater(Structs.Polygon p){
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue("0,0,151").build();//make polygon brown
        Structs.Property type = Structs.Property.newBuilder().setKey("TileType").setValue("Water").build();
        return Structs.Polygon.newBuilder(p).addProperties(color).addProperties(type).build();
    }

    public static Structs.Polygon setToBeach(Structs.Polygon p){
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue("246,0,0").build();//make polygon brown
        Structs.Property type = Structs.Property.newBuilder().setKey("TileType").setValue("Beach").build();
        return Structs.Polygon.newBuilder(p).addProperties(color).addProperties(type).build();
    }

    public static String getType(Structs.Polygon p){
        List<Structs.Property> Props = p.getPropertiesList();
        for (Structs.Property prop: Props){
            if(prop.getKey().equals("TileType")){
                return prop.getValue();
            }
        }
        return "Void Type";

    }
}