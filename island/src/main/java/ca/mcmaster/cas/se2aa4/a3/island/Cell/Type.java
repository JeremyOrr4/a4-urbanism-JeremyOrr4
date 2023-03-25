package ca.mcmaster.cas.se2aa4.a3.island.Cell;

public enum Type {


    LAND("41,185,81", "Land"),
    WATER("0,0,151", "Water", true),

    BEACH("201,185,151", "Beach"),

    TROPICALDESERT("174,146,87","Tropical Desert"),

    LAKE("24,112,188", "Lake", true ),
    TAIGA("34,96,69","Taiga"),
    SEASONALFOREST("138,162,50","Seasonal Forest"),
    DECIDUOUS("66,146,118","Deciduous"),
    SAVANNA("138,162,50","Savanna"),

    GRASSLAND("215,196,41","Grassland Desert"),

    TROPICAL("68,193,25","Tropical"),
    LAGOON("24,112,188", "Lagoon", true ); 



    String color; 
    String name; 
    boolean isWater; 

    Type(String color, String name){
        this.color = color; 
        this.name = name; 
        this.isWater = false; 
    }    

    Type(String color, String name, boolean isWater){
       this(color, name); 
       this.isWater = true; 
        
    }




    
}
