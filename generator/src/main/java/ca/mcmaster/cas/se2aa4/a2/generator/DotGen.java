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


public class DotGen {

    private final int width = CommArgs.width;
    private final int height = CommArgs.height;
    private final int square_size = CommArgs.square_size;

    private final int rowSize = width/square_size;



    public Mesh generate() {
        MeshGenerator g = new MeshGenerator();
        return g.generate();
//        MeshData GridMesh = new MeshData();
//        Random random = new Random();
//
    //    for(int x = 0; x < width; x += square_size) {
    //        for(int y = 0; y < height; y += square_size) {
    //            GridMesh.createVertex(x,y,0,random);
    //            //add segments between vertices, skip edge segments (first segment after exceeding row )
    //            if(GridMesh.vertexData.size() < (rowSize*rowSize) && (GridMesh.vertexData.size()%rowSize !=0)) GridMesh.segmentData.add(GridMesh.createSegment(GridMesh.vertexData.size()-1,GridMesh.vertexData.size()));
    //            if(GridMesh.vertexData.size() < (rowSize*rowSize-rowSize+1)) GridMesh.segmentData.add(GridMesh.createSegment(GridMesh.vertexData.size()-1,GridMesh.vertexData.size()+rowSize-1));

    //        }

    //    }
//
//
//
//        // Distribute colors randomly. Vertices are immutable, need to enrich them
//        int vertexPointer=0;
//        for(Vertex v: GridMesh.vertexData){
//            GridMesh.vertexData.set(vertexPointer,GridMesh.AddVertexProperties(v,"6"));
//            vertexPointer+=1;
//        }
//
//        int segmentPointer=0;
//        for(Segment s: GridMesh.segmentData){
//            //Assign segment color based on average of associated vertices
//            GridMesh.segmentData.set(segmentPointer,GridMesh.AddSegmentProperties(s,"2"));
//            segmentPointer+=1;
//        }

//
//
//
//
//        int size=GridMesh.segmentData.size();
//        int last_row=0;
//        for (int k = 0; k<size-28;k+=1){
//
//            GridMesh.polygonData.add(GridMesh.createPolygon(k,size,last_row));
//            if (k>size-76 && k%2==0){
//                last_row+=1;
//            }
//
//            List<Segment> colorSegments = new ArrayList<Segment>();
//
//            for(int id: GridMesh.polygonData.get(k).getSegmentIdxsList()){
//                colorSegments.add(GridMesh.segmentData.get(id));
//            }
//
//            //System.out.println("color segments" + colorSegments.size());
//
//            Property averageColor = averageColor(colorSegments);
//
//            Property color = Property.newBuilder().setKey("rgb_color").setValue("0,0,0,0").build();
//            Polygon colored = Polygon.newBuilder(GridMesh.polygonData.get(k)).addProperties(averageColor).build();
//
//            GridMesh.polygonData.set(k,colored);
//        }
//
//
//
//        return Mesh.newBuilder().addAllVertices(GridMesh.vertexData).addAllSegments(GridMesh.segmentData).addAllPolygons(GridMesh.polygonData).build();
    }




    /**
     * Used to determine the average color between two vertices in a mesh
     * @param v1 a colored Vertex
     * @param v2 a colored Vertex
     * @return the average color between the two vertices
     */
    public static Property averageColor(Vertex v1, Vertex v2){

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
        rgba[3] = ((s1.length==4 ? Integer.parseInt(s1[3]) : 255 ) + (s2.length==4 ? Integer.parseInt(s2[3]) : 255 ))/2 ;

        //rebuild string with new average
        String colorCode = rgba[0]+","+rgba[1]+","+rgba[2] + "," + rgba[3];

        return Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
    }




    public static Property averageColor(List<Segment> segments ){



        int rgba[] = new int[4];

        for(Segment s : segments){
            String val = "0,0,0,0";
            for(Property p : s.getPropertiesList()){

                if (p.getKey().equals("rgb_color"))  val = p.getValue();

            }

            String[] sColor=val.split(",");
            for(int i=0;i<3;i++) rgba[i] += Integer.parseInt(sColor[i]);
            rgba[3] += (sColor.length==4 ? Integer.parseInt(sColor[3]) : 255 );



        }

        for(int i=0; i<4;i++){
            rgba[i] = rgba[i]/segments.size();



        }

        rgba[3]  = rgba[3]/2;

        //rebuild string with new average
        String colorCode = rgba[0]+","+rgba[1]+","+rgba[2] +","+rgba[3] ;


        return Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
    }






}
