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
import ca.mcmaster.cas.se2aa4.a2.generator.PropertyAdd;
import ca.mcmaster.cas.se2aa4.a2.generator.CommArgs;


public class DotGen {

    private final int width = CommArgs.width;
    private final int height = CommArgs.height;
    private final int square_size = CommArgs.square_size;

    private final int rowSize = width/square_size;



    public Mesh generate() {
        if (CommArgs.irregular){
            MeshGenerator IrregularMesh = new MeshGenerator();
            return IrregularMesh.generate();
        }else{
            MeshData GridMesh = new MeshData();
            Random random = new Random();

            for(int x = 0; x < width; x += square_size) {
                for(int y = 0; y < height; y += square_size) {
                    GridMesh.createVertex(x,y,0,random);
                    GridMesh.createVertex(x+square_size,y,0,random);
                    GridMesh.createVertex(x+square_size,y+square_size,0,random);
                    GridMesh.createVertex(x,y+square_size,0,random);
                    //add segments between vertices, skip edge segments (first segment after exceeding row )
                    GridMesh.segmentData.add(GridMesh.createSegment(GridMesh.vertexData.size()-4,GridMesh.vertexData.size()-3));
                    GridMesh.segmentData.add(GridMesh.createSegment(GridMesh.vertexData.size()-3,GridMesh.vertexData.size()-2));
                    GridMesh.segmentData.add(GridMesh.createSegment(GridMesh.vertexData.size()-2,GridMesh.vertexData.size()-1));
                    GridMesh.segmentData.add(GridMesh.createSegment(GridMesh.vertexData.size()-1,GridMesh.vertexData.size()-4));
                    GridMesh.polygonData.add(GridMesh.createPolygon(GridMesh.segmentData.size()-4,GridMesh.segmentData.size()-3,GridMesh.segmentData.size()-2,GridMesh.segmentData.size()-1));

                }

            }



            // Distribute colors randomly. Vertices are immutable, need to enrich them
            int vertexPointer=0;
            for(Vertex v: GridMesh.vertexData){
                GridMesh.vertexData.set(vertexPointer,PropertyAdd.AddVertexProperties(v,"6"));
                vertexPointer+=1;
            }

            int segmentPointer=0;
            for(Segment s: GridMesh.segmentData){
                //Assign segment color based on average of associated vertices
                GridMesh.segmentData.set(segmentPointer,PropertyAdd.RandomSegmentColor(s));
                segmentPointer+=1;
            }



            for (Polygon p: GridMesh.polygonData){
                List<Segment> colorSegments = new ArrayList<Segment>();
                for(int id: p.getSegmentIdxsList()){
                    colorSegments.add(GridMesh.segmentData.get(id));
                }

                //System.out.println("color segments" + colorSegments.size());

                Property averageColor = PropertyAdd.averageColor(colorSegments);
                Polygon colored = Polygon.newBuilder(p).addProperties(averageColor).build();

                GridMesh.polygonData.set(GridMesh.polygonData.indexOf(p),colored);
            }
            return Mesh.newBuilder().addAllVertices(GridMesh.vertexData).addAllSegments(GridMesh.segmentData).addAllPolygons(GridMesh.polygonData).build();

        }

    }










}
