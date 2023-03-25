package ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.*;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.Elevation;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.ExampleElevationProfile;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.VolcanoProfile;
import ca.mcmaster.cas.se2aa4.a3.island.Water.riverFactory;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor;

import java.util.ArrayList;
import java.util.List;

public class Tiles {

    public enum TileType{

        LAND("41,185,81", "Land"),
        WATER("0,0,151", "Water"),

        BEACH("201,185,151", "Beach"),

        TROPICALDESERT("174,146,87","Tropical Desert"),

        LAKE("24,112,188", "Lake"),
        TAIGA("34,96,69","Taiga"),
        SEASONALFOREST("138,162,50","Seasonal Forest"),
        DECIDUOUS("66,146,118","Deciduous"),
        SAVANNA("138,162,50","Savanna"),

        GRASSLAND("215,196,41","Grassland Desert"),

        TROPICAL("68,193,25","Tropical"),
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


    public static Structs.Polygon setColor(Structs.Polygon p, String rgb){
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(rgb).build(); 
        return Structs.Polygon.newBuilder(p).clearProperties().addProperties(color).build();
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

    public static Structs.Mesh MasterPropertyFactory(Structs.Mesh aMesh,int river,String Profile){

        if (Profile.equals("Volcano")){
            aMesh = Elevation.SetVertexElevation(aMesh, new VolcanoProfile());
        }else{
            aMesh = Elevation.SetVertexElevation(aMesh, new ExampleElevationProfile());
        }

        Elevation Elev = new Elevation();
        aMesh = Elev.addPolyElevation(aMesh);

        riverFactory rf = new riverFactory();
        aMesh = rf.riverGenerator(aMesh,river);

        Humidity Humid = new Humidity();
        aMesh = Humid.GenerateHumidities(aMesh);

        // BiomeFactory biome = new BiomeFactory();
        // aMesh = biome.BiomeSetter(aMesh);

        return aMesh;
    }

    public static Structs.Polygon setBiome(Structs.Polygon p, TileType type){
        Elevation Elev = new Elevation();
        Humidity Humid = new Humidity();
        String ElevVal = ""+ Extractor.getPolyElevation(p);
        String HumidVal = ""+Extractor.getPolyHumidity(p);
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(type.color).build();
        Structs.Property typeString = Structs.Property.newBuilder().setKey("TileType").setValue(type.name).build();
        Structs.Property Elevation = Structs.Property.newBuilder().setKey("Elevation").setValue(ElevVal).build();
        Structs.Property Humidity = Structs.Property.newBuilder().setKey("Humidity").setValue(HumidVal).build();
        return Structs.Polygon.newBuilder(p).clearProperties().addProperties(color).addProperties(typeString).addProperties(Elevation).addProperties(Humidity).build();
    }
}
