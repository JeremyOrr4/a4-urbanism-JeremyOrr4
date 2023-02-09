package ca.mcmaster.cas.se2aa4.a2.generator;

import java.io.IOException;
import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;



public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    private final int rowSize = width/square_size; 



    public Mesh generate() {
        Collection<Vertex> vertices = new ArrayList<>();
        Collection<Segment> segments = new ArrayList<>();

       
        // Create all the vertices
        for(int x = 0; x < width; x += square_size) {
            for(int y = 0; y < height; y += square_size) {
                 vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
                 //add segments between vertices, skip edge segments (first segment after exceeding row )
                 if(vertices.size() < 624 && (vertices.size()%rowSize !=rowSize-1) ) segments.add(Segment.newBuilder().setV1Idx(vertices.size()).setV2Idx(vertices.size()+1).build());  
                 if(vertices.size() < 600 && (vertices.size()%rowSize !=rowSize-1) ) segments.add(Segment.newBuilder().setV1Idx(vertices.size()).setV2Idx(vertices.size()+rowSize).build());    
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

       


        ArrayList<Segment> segmentsWithColors = new ArrayList<>(); 
        for(Segment s: segments){           
            Property color = averageColor( verticesWithColors.get(s.getV1Idx()), verticesWithColors.get(s.getV2Idx())); 
            //Property color = Property.newBuilder().setKey("rgb_color").setValue("0,0,0").build();

            Segment colored = Segment.newBuilder(s).addProperties(color).build(); 
            segmentsWithColors.add(colored); 
           
           
            
        }

        System.out.println("Num verts: "+ verticesWithColors.size());
        return Mesh.newBuilder().addAllVertices(verticesWithColors).addAllSegments(segmentsWithColors).build();
    }




    /**
     * 
     * @param v1 a colored Vertex
     * @param v2 a colored Vertex
     * @return the average color between the two vertices
     */
    private Property averageColor(Vertex v1, Vertex v2){

        //Initialize default color to black. Attempt to find color in vertex properties
        String val1="0,0,0"; 
        for(Property p : v1.getPropertiesList()){
            if (p.getKey().equals("rgb_color"))  val1 = p.getValue(); 
        }

        String val2="0,0,0"; 
        for(Property p : v2.getPropertiesList()){
            if (p.getKey().equals("rgb_color")) val2 = p.getValue(); 
        }

       //parse numeric data from color string, compute average values
       String[] s1 = val1.split(",", 3); 
       String[] s2 = val2.split(",", 3); 
       int rgb[] = new int[3]; 
       for(int i=0;i<3;i++) rgb[i] = (Integer.parseInt(s1[i]) + Integer.parseInt(s2[i]))/2; 
        
        //rebuild string with new average
        String colorCode = rgb[0]+","+rgb[1]+","+rgb[2]; 
                
        return Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
    }
    

}
