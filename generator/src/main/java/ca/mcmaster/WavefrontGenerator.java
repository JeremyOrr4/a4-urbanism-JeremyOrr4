package ca.mcmaster;

import java.util.*;
import java.util.ArrayList;



import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WavefrontGenerator {

    List<Vertex> vertices = new ArrayList<Vertex>();
    List<Polygon> polygons = new ArrayList<Polygon>();
    List<Segment> segments = new ArrayList<Segment>();



    public WavefrontGenerator(Mesh myMesh) throws IOException {
        FileWriter fw = new FileWriter("Wavefront.obj", false); // Creating the file to write to.
        BufferedWriter writer = new BufferedWriter(fw);

        vertices = myMesh.getVerticesList();// Getting each vertex
        polygons = myMesh.getPolygonsList(); 
        segments = myMesh.getSegmentsList();

        // Creating the Vertext list comment in the file
        
        writer.write("o BonusMesh\n");

        writer.write("# Vertex list\n");

        for (Vertex vertex : vertices) {

            try {
                writer.write("v " + vertex.getX() + " " + vertex.getY() + " 0");
                writer.newLine();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        writer.write("# Next Section \n");



        for(Polygon p : polygons){
            
            
            writer.write("f ");
            for(int s : p.getSegmentIdxsList()){

                Segment segment = segments.get(s); 

                writer.write(segment.getV1Idx()+1 +" ");
                writer.write(segment.getV2Idx()+1 +" ");

                
            
            }

            writer.newLine();
        
         
        }

        writer.write("# Next Section \n");

        writer.write("# Next Section \n");

        writer.write("# Next Section \n");

        writer.close();
        fw.close();
    }
}
