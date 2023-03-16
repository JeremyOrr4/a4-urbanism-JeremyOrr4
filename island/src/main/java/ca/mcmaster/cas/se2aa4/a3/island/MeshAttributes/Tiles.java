package ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.BiomeFactory;

import java.util.ArrayList;
import java.util.List;

public class Tiles {

    public enum TileType{

        LAND("41,185,81", "Land"),
        WATER("0,0,151", "Water"),
        BEACH("201,185,151", "Beach"),
        TROPICAL("205,87,52","Tropical"),
        LAGOON("24,112,188", "Lagoon"); 



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

    public static Structs.Mesh MasterPropertyFactory(Structs.Mesh aMesh){
        VertexProperties vert = new VertexProperties();
        aMesh = vert.SetVertexElevation(aMesh);

        Elevation Elev = new Elevation();
        aMesh = Elev.addPolyElevation(aMesh);

        Humidity Humid = new Humidity();
        aMesh = Humid.MasterHumidity(aMesh);

        BiomeFactory biome = new BiomeFactory();
        return aMesh;
    }

    public static Structs.Polygon setBiome(Structs.Polygon p, TileType type){
        Elevation Elev = new Elevation();
        Humidity Humid = new Humidity();
        String ElevVal = ""+Elev.getPolyElevation(p);
        String HumidVal = ""+Humid.getPolyHumidity(p);
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(type.color).build();
        Structs.Property typeString = Structs.Property.newBuilder().setKey("TileType").setValue(type.name).build();
        Structs.Property Elevation = Structs.Property.newBuilder().setKey("Elevation").setValue(ElevVal).build();
        Structs.Property Humidity = Structs.Property.newBuilder().setKey("Humidity").setValue(HumidVal).build();
        return Structs.Polygon.newBuilder(p).clearProperties().addProperties(color).addProperties(typeString).addProperties(Elevation).addProperties(Humidity).build();
    }
}
