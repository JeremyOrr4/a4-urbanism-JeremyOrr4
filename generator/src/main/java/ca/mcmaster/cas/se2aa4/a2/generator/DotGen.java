package ca.mcmaster.cas.se2aa4.a2.generator;

import java.io.IOException;
import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;



public class DotGen {

    private final int width = 520;
    private final int height = 520;
    private final int square_size = 20;



    public Mesh generate() {
        Collection<Vertex> vertices = new ArrayList<>();
        Collection<Segment> segments = new ArrayList<>();
        Collection<Polygon> polygons = new ArrayList<>();
        int seg=0;
        // Create all the vertices
        for(int x = 0; x < width; x += square_size) {
            for(int y = 0; y < height; y += square_size) {
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y).build());

                segments.add(Segment.newBuilder().setV1Idx(seg).setV2Idx(seg+1).build()); //segment between first two vertices

                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y+square_size).build());

                segments.add(Segment.newBuilder().setV1Idx(seg).setV2Idx(seg+2).build());//segment between first and third

                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y+square_size).build());

                segments.add(Segment.newBuilder().setV1Idx(seg+1).setV2Idx(seg+3).build());//segment between second and fourth

                segments.add(Segment.newBuilder().setV1Idx(seg+2).setV2Idx(seg+3).build()); //segment between third and fourth
                seg+=4;
            }
        }
        // Distribute colors randomly. Vertices are immutable, need to enrich them
        ArrayList<Vertex> verticesWithColors = new ArrayList<>();
        Random bag = new Random();
        for(Vertex v: vertices){
            int red = bag.nextInt(255);
            int green = bag.nextInt(255);
            int blue = bag.nextInt(255);
            String colorCode = red + "," + green + "," + blue;
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Vertex colored = Vertex.newBuilder(v).addProperties(color).build();
            verticesWithColors.add(colored);
        }

        return Mesh.newBuilder().addAllVertices(verticesWithColors).addAllSegments(segments).build();
    }

}
