package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.BoundedShape;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.TileType;

import java.util.ArrayList;
import java.util.List;

public class IslandShaper {

    BoundedShape shape; 

    public IslandShaper(BoundedShape shape){

        this.shape = shape; 

    }



    public Mesh shapedIsland(Mesh mesh){

        List <Vertex> vertices = mesh.getVerticesList();
        List<Segment> segments = mesh.getSegmentsList(); 
        List<Polygon> polygons = new ArrayList<Polygon>();

        for(Polygon p : mesh.getPolygonsList()){
            
            double x = vertices.get(p.getCentroidIdx()).getX(); 
            double y = vertices.get(p.getCentroidIdx()).getY();

            Polygon terrainCell = (shape.contains(x, y) ? 
            Tiles.setType(p, TileType.LAND) : 
            Tiles.setType(p, TileType.WATER));          
          
            polygons.add(terrainCell); 
        }

        

        return Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build(); 


    }





    
}
