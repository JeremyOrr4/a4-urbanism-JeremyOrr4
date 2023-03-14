package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.BoundedShape;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.TileType;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * Creates an island with a specified shape by converting tiles within a boundary to land tiles
 * 
 * 
 */

public class IslandShaper {

    BoundedShape shape; 

    public IslandShaper(BoundedShape shape){
        this.shape = shape; 
    }



    public Mesh generateShape(Mesh mesh){

        List <Vertex> vertices = mesh.getVerticesList();
        List<Segment> segments = mesh.getSegmentsList(); 
        List<Polygon> polygons = new ArrayList<Polygon>();

        for(Polygon p : mesh.getPolygonsList()){
            
            double x = vertices.get(p.getCentroidIdx()).getX(); 
            double y = vertices.get(p.getCentroidIdx()).getY();

            Polygon terrainCell = (shape.contains(x, y) ? 
            Tiles.setType(p, TileType.LAND): 
            Tiles.setType(p, TileType.WATER));        
            
        
            polygons.add(terrainCell); 
        }

        return Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build(); 


    }


    

    public static Mesh fillRegion(Mesh mesh, BoundedShape region, TileType type){

        List <Vertex> vertices = mesh.getVerticesList();
        List<Segment> segments = mesh.getSegmentsList(); 
        List<Polygon> oldPolygons = mesh.getPolygonsList(); 
        List<Polygon> polygons = new ArrayList<Polygon>(); 

        for(int i=0; i<oldPolygons.size(); i++){

           Polygon p=oldPolygons.get(i); 
            
            double x = vertices.get(p.getCentroidIdx()).getX(); 
            double y = vertices.get(p.getCentroidIdx()).getY();

            if(region.contains(x, y)){        
                polygons.add(Tiles.setType(p, type));               
            }else{
                polygons.add(oldPolygons.get(i));
            }
        
           
        }

        Mesh theMesh = Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build(); 

      
        return theMesh; 

    }


    









    
}
