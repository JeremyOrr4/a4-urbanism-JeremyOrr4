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
        if(!this.type.isWater) this.isTerrain = true; 

     
    }



    public void setToTerrain(){

        this.isTerrain = true; 
        this.type = Type.DEBUG_TERRAIN; 

    }


    public void setToOcean(){
        this.isTerrain = false; 
        this.type = Type.DEBUG_WATER;
    }



    public void setCellElevation(List<Double> elevations){



        List<Integer> indices = MeshOperations.getVertexIdxs(polygon, parentMesh); 

        int sum=0; 
        for(Integer i : indices){
            sum+= elevations.get(i); 
        }
       this.elevation = sum/indices.size(); 


        //water has no elevation 
        if(!this.isTerrain) this.elevation = 0; 

    }



    public Polygon toPolygon(){


       



        int elevationColor = Math.max(0, Math.min((int)(elevation), 255)); 


        String[]srbg = this.type.color.split(","); 
        
        int[]rgb = {Integer.parseInt(srbg[0]),Integer.parseInt(srbg[1]),Integer.parseInt(srbg[2]) }; 

        String color2 = String.format("%d,%d,%d", elevationColor, rgb[1], rgb[2]); 

        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(color2).build(); 
        return Structs.Polygon.newBuilder(this.polygon).clearProperties().addProperties(color).build(); 
    }



    



   
    
}
