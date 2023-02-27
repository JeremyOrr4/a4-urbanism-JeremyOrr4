package ca.mcmaster.cas.se2aa4.a2.generator;

import java.io.IOException;
import java.util.*;

import ca.mcmaster.MeshGenerator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import java.util.ArrayList;

import org.locationtech.jts.geom.Coordinate;



public class DotGenNew {

    


    public static List<Coordinate> populatePoints(int width, int height, int square_size, double offset) {





        List<Coordinate> coordinates = new ArrayList<Coordinate>();

        //create a grid bound by width,height, and divided by square_size
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {

                //offset grid points by += offset value
                double xPos = offset*(Math.random()*2-1) + x;
                double yPos = offset*(Math.random()*2-1) + y;

                //add point to coordinate list
                coordinates.add(new Coordinate(xPos,yPos));
            }
        }
        return coordinates;

    }

    
}
