package ca.mcmaster;

import java.util.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

// import org.locationtech.jts.geom.PrecisionModel;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

public class GenTest {

    private final int width = 500;
    private final int height = 500;

    private final int square_size = 40;

    public List<Vertex> vertices = new ArrayList<Vertex>();

    
    public List<Segment> segments = new ArrayList<Segment>(); 

    public Mesh generate() {

        DelaunayTriangulationBuilder delaunay = new DelaunayTriangulationBuilder(); 
        VoronoiDiagramBuilder voronoi = new VoronoiDiagramBuilder(); 

        
        vertices = populateVertices(width, height, square_size, 15); 

        List<Coordinate> coords = new ArrayList<Coordinate>(); 

        
        for(Vertex v : vertices){
            coords.add(new Coordinate(v.getX(), v.getY())); 
        }

      
        voronoi.setSites(coords);
        

        GeometryFactory factory = new GeometryFactory(); 
        Geometry rawVoronoiGeometry = voronoi.getDiagram(factory); 

        List<Geometry> voronoiCells = new ArrayList<Geometry>(); 

        for(int i=0; i<rawVoronoiGeometry.getNumGeometries();  i++){
            voronoiCells.add(rawVoronoiGeometry.getGeometryN(i)); 
        }
        

        for(Geometry cell : voronoiCells){
            

            int offset=vertices.size(); 
            Coordinate[] cellCoords = cell.getCoordinates(); 
            for(int i=0; i<cellCoords.length;i++){
                vertices.add(createVertex(cellCoords[i])); 
                if(i!= cellCoords.length-1) segments.add(Segment.newBuilder().setV1Idx(i+offset).setV2Idx(i+1+offset).build()); 
                

            }

        }


        // for(Coordinate c: rawVoronoiGeometry.getCoordinates()){
        //     vertices.add(createVertex(c)); 
        // }
        
        

        // for(int i=vertices.size()-rawVoronoiGeometry.getCoordinates().length; i<vertices.size()-1;i++){
        //     segments.add(Segment.newBuilder().setV1Idx(i).setV2Idx(i+1).build()); 
        // }

        return Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).build();
    }

    public static List<Vertex> populateVertices(int width, int height, int square_size, double offset) {
         List<Vertex> vertices = new ArrayList<Vertex>();

        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {

                    double xPos = offset*(Math.random()*2-1) + x; 
                    double yPos = offset*(Math.random()*2-1) + y; 

                    vertices.add(Vertex.newBuilder().setX(xPos).setY(yPos).addProperties(randomColor()).build()); 
            }

        }


        return vertices; 

    }


    public static Vertex createVertex(Coordinate c){

        return Vertex.newBuilder().setX(c.x).setY(c.y).build(); 
    }

    public static Property randomColor(){

        Random bag = new Random();
        int red = bag.nextInt(255);
        int green = bag.nextInt(255);
        int blue = bag.nextInt(255);
        int alpha = 255; 


        String colorCode = String.format("%d,%d,%d,%d",red,green,blue,alpha); 
        return Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();



    }



}
