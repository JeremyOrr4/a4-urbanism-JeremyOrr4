package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class Tiles {

    public enum TileType{

        LAND("41,185,81", "Land"),
        WATER("0,0,151", "Water"),
        BEACH("246,0,210", "Beach"); 



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
        return Structs.Polygon.newBuilder(p).addProperties(color).addProperties(typeString).build();
    }


    //ALL OF THESE CAN BE merged into setType for efficiency

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