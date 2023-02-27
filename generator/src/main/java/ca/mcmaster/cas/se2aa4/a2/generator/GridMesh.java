package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.MeshGenerator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.awt.PointShapeFactory.Point;
import org.locationtech.jts.geom.Coordinate;

public class GridMesh {



    List<Vertex> vertices = new ArrayList<Vertex>(); 
    List<Segment> segments = new ArrayList<Segment>(); 
    List<Polygon> polygons = new ArrayList<Polygon>(); 


    int width = CommArgs.width; 
    int height = CommArgs.height; 
    int square_size = CommArgs.square_size; 

    int rowSize = width/square_size; 


    public Mesh generate(){

        List<Coordinate> coords = DotGenNew.populatePoints(width,height,square_size, 0); 
        
        vertices = GeometryConverter.vertex(coords); 

        
        createGridSegments();
        return Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build(); 

    }

    private void createGridSegments(){
    
        for(int i=0; i<vertices.size();i++){

                if(vertices.size() < (rowSize*rowSize) && (vertices.size()%rowSize !=0)) segments.add(createSegment(vertices.size()-1,vertices.size()));
                if(vertices.size() < (rowSize*rowSize-rowSize+1))segments.add(createSegment(vertices.size()-1,vertices.size()+rowSize-1));        
        }


        
    }


    public Segment createSegment(int v1, int v2){return (Segment.newBuilder().setV1Idx(v1).setV2Idx(v2).build()); }



    

    
}
