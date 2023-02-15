package ca.mcmaster.cas.se2aa4.a2.generator;

import java.io.IOException;
import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import java.util.ArrayList;



public class DotGen {

    private final int width = 520;
    private final int height = 520;
    private final int square_size = 20;

    private final int rowSize = width/square_size; 



    public Mesh generate() {
        ArrayList<Vertex> vertices = new ArrayList<>();
        ArrayList<Segment> segments = new ArrayList<>();
        ArrayList<Polygon> polygons = new ArrayList<>();
        ArrayList<Integer> segmentID = new ArrayList<>();
        // Create all the vertices
        for(int x = 0; x < width; x += square_size) {
            for(int y = 0; y < height; y += square_size) {
                 vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
                 //add segments between vertices, skip edge segments (first segment after exceeding row )
                 if(vertices.size() < (rowSize*rowSize-1) && (vertices.size()%rowSize !=rowSize-1) ) segments.add(Segment.newBuilder().setV1Idx(vertices.size()).setV2Idx(vertices.size()+1).build());
                 if(vertices.size() < (rowSize*rowSize-rowSize) && (vertices.size()%rowSize !=rowSize-1) ) segments.add(Segment.newBuilder().setV1Idx(vertices.size()).setV2Idx(vertices.size()+rowSize).build());

            }

        }
        //for (int k = 0; k<segments.size()-27;k++){
        //    segmentID.add(k);
        //    segmentID.add(k+3);
        //    if (segments.size()-k < 51){
        //        segmentID.add(k+26);
        //    }else{
        //        segmentID.add(k+50);
        //    }
        //    segmentID.add(k+1);
        //    polygons.add(Polygon.newBuilder().addAllSegmentIdxs(segmentID).build());
        //    segmentID.removeAll(segmentID);
        //    k+=1;
        //}
        int size=segments.size();
        for (int k = 0; k<size;k++){
            segmentID.add(k);
           // segments.add(Segment.newBuilder().setV1Idx(segments.get(k).getV2Idx()).setV2Idx(segments.get(k+1).getV2Idx()).build());
            segmentID.add(k+size);
            segmentID.add(k+1);
            //if (segments.size()-k < 51){
            //    segmentID.add(k+26);
            //}else{
            //    segmentID.add(k+50);
            //}

            String colorCode = "150,150,150,100";
            
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Polygon p = Polygon.newBuilder().addAllSegmentIdxs(segmentID).build();
            Polygon colored = Polygon.newBuilder(p).addProperties(color).build(); 

            polygons.add(colored);
            

            segmentID.removeAll(segmentID);
            k+=1;
        }


     

        // Distribute colors randomly. Vertices are immutable, need to enrich them
        ArrayList<Vertex> verticesWithColors = new ArrayList<>();
        Random bag = new Random();
        for(Vertex v: vertices){
            int red = bag.nextInt(255);
            int green = bag.nextInt(255);
            int blue = bag.nextInt(255);
            String colorCode = red + "," + green + "," + blue;

            String thicknessString = "3";
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Property thickness = Property.newBuilder().setKey("thickness").setValue(thicknessString).build();

            Vertex thickened = Vertex.newBuilder(v).addProperties(thickness).build();
            Vertex colored = Vertex.newBuilder(thickened).addProperties(color).build();
            verticesWithColors.add(colored);
        }

       


        ArrayList<Segment> segmentsWithColors = new ArrayList<>(); 
        for(Segment s: segments){           
            
            //Assign segment color based on average of associated vertices
            Property color = averageColor( verticesWithColors.get(s.getV1Idx()), verticesWithColors.get(s.getV2Idx())); 

            String thicknessString="3" ; 
            Property thickness = Property.newBuilder().setKey("thickness").setValue(thicknessString).build();

            Segment thickened =  Segment.newBuilder(s).addProperties(thickness).build(); 
            Segment colored = Segment.newBuilder(thickened).addProperties(color).build(); 
            segmentsWithColors.add(colored); 
           
           
            
        }
        return Mesh.newBuilder().addAllVertices(verticesWithColors).addAllSegments(segmentsWithColors).addAllPolygons(polygons).build();
    }




    /**
     * Used to determine the average color between two vertices in a mesh 
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
       String[] s1 = val1.split(","); 
       String[] s2 = val2.split(","); 
       int rgba[] = new int[4]; 
       for(int i=0;i<3;i++) rgba[i] = (Integer.parseInt(s1[i]) + Integer.parseInt(s2[i]))/2;
       
       //identifies if the both color strings have an alpha value. If absent, specify default of 255 (No transparency)
       rgba[3] = ((s1.length==4 ? Integer.parseInt(s1[4]) : 255 ) + (s2.length==4 ? Integer.parseInt(s2[4]) : 255 ))/2 ; 
        
        //rebuild string with new average
        String colorCode = rgba[0]+","+rgba[1]+","+rgba[2] + "," + rgba[3]; 
                
        return Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
    }
    

}
