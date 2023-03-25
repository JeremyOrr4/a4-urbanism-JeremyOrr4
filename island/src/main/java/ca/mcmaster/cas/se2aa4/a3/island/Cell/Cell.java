package ca.mcmaster.cas.se2aa4.a3.island.Cell;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;

public class Cell {



    double x; 
    double y; 
    Type type; 

    double humidity; 

    double elevation; 


    private Mesh parentMesh; 
    private Polygon polygon; 


    public Cell(Polygon p, Mesh mesh ){

        this.parentMesh = mesh;
        this.polygon = p;  
    }


    public Type getType(){
        return this.type; 
    }


    public void setElevation(double elevation){
        this.elevation = elevation; 
    }

    public void setHumidity(double humidity){
        this.humidity = humidity; 
    }



    public void setType(Type type){

        this.type = type; 
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(type.color).build(); 
        this.polygon =  Structs.Polygon.newBuilder(this.polygon).clearProperties().addProperties(color).build();
        
    }




   
    
}
