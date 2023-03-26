package ca.mcmaster.cas.se2aa4.a3.island.Cell;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;

public class Cell {



    double x; 
    double y; 
    Type type = Type.NOP; 

    double humidity; 

    double elevation; 


    private Mesh parentMesh; 
    private Polygon polygon; 

    private boolean isTerrain=false; 


    List<Double> vertexElevations; 


    public Cell(Polygon p, Mesh mesh ){

        this.parentMesh = mesh;
        this.polygon = p;  

        

        


    }


    public List<Integer> getNeighborCells(){

        return this.polygon.getNeighborIdxsList(); 

    }

    public Point getCentroidPosition(){

        int index = polygon.getCentroidIdx(); 
        Vertex v = parentMesh.getVerticesList().get(index); 
        double x =v.getX(); 
        double y = v.getY(); 
        return new Point(x, y); 
    }


    public Type getType(){
        return this.type; 
    }


    public boolean isTerrain(){
        return this.isTerrain; 
    }

    public void setElevation(double elevation){
        this.elevation = elevation; 
    }

    public void setHumidity(double humidity){
        this.humidity = humidity; 
    }



    public void setType(Type type){
        this.type = type; 
    }



    public void setToTerrain(){

        this.isTerrain = true; 

        this.type = Type.DEBUG_TERRAIN; 

    }


    public void setToOcean(){
        this.isTerrain = false; 
        this.type = Type.DEBUG_WATER;
    }



    public List<Point> getVertexPoints(){

        List<Point> points = new ArrayList<Point>(); 

        // for(Vertex v : polygon.get)


    }



    public Polygon toPolygon(){


        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(type.color).build(); 
        return Structs.Polygon.newBuilder(this.polygon).clearProperties().addProperties(color).build(); 
    }



    



   
    
}
