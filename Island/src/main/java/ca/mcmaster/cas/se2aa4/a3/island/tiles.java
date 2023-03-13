package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class Tiles {

    public enum TileType{

        LAND("41,185,81", "Land"),
        WATER("0,0,151", "Water"),
        BEACH("201,185,151", "Beach"),  
        LAGOON("24,72,158", "Lagoon"); 



        String color; 
        String name; 

        TileType(String color, String name){
            this.color = color; 
            this.name = name; 
        }
         
    }; 

    public static Structs.Polygon setType(Structs.Polygon p, TileType type){
        
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(type.color).build(); 
        Structs.Property typeString = Structs.Property.newBuilder().setKey("TileType").setValue(type.name).build();
        return Structs.Polygon.newBuilder(p).clearProperties().addProperties(color).addProperties(typeString).build();
    }

    

    public static String getTileType(Structs.Polygon p){
        List<Structs.Property> Props = p.getPropertiesList();
        for (Structs.Property prop: Props){
            if(prop.getKey().equals("TileType")){
                return prop.getValue();
            }
        }
        return "Void Type";

    }
}