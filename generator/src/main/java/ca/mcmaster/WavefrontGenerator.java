package ca.mcmaster;

import java.util.*;
import java.util.ArrayList;

import org.locationtech.jts.geom.Coordinate;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WavefrontGenerator {

    List<Vertex> Vertices = new ArrayList<Vertex>();

    public WavefrontGenerator(Mesh myMesh) throws IOException {
        FileWriter fw = new FileWriter("Wavefront.obj", false); // Creating the file to write to.
        BufferedWriter writer = new BufferedWriter(fw);

        Vertices = myMesh.getVerticesList();// Getting each vertex

        // Creating the Vertext list comment in the file
        
        writer.write("o BonusMesh\n");

        writer.write("# Vertex list\n");

        for (Vertex vertex : Vertices) {

            try {
                writer.write("v " + vertex.getX() + " " + vertex.getY() + " 0");
                writer.newLine();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        writer.write("# Next Section \n");

        writer.write("# Next Section \n");

        writer.write("# Next Section \n");

        writer.write("# Next Section \n");

        writer.close();
        fw.close();
    }
}
