
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

public class GeometryConverter {

    


    public static Vertex vertex(Coordinate c){
    return Vertex.newBuilder().setX(c.x).setY(c.y).build(); 
    }


    public static List<Vertex> vertex( List<Coordinate> coordinates){

        List<Vertex> vertices = new ArrayList<Vertex>(); 

        for(Coordinate c : coordinates){
            vertices.add(vertex(c)); 
        }

        return vertices; 

    }


    


}
