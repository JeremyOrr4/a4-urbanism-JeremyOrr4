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
import org.locationtech.jts.geom.Coordinate;


    public class DotGen {
        //mesh properties dependent on command line inputs
        private final int width = CommArgs.width;
        private final int height = CommArgs.height;
        private final int square_size = CommArgs.square_size;

        private final int rowSize = width/square_size;



        public Mesh generate() {
            //Based on command line, generate grid mesh or enter irregular mesh generation
            if (CommArgs.irregular){
                MeshGenerator IrregularMesh = new MeshGenerator();
                return IrregularMesh.generate();
            }else{
                MeshData GridMesh = new MeshData();
                Random random = new Random();

                for(int x = 0; x < width; x += square_size) {
                    for(int y = 0; y < height; y += square_size) {
                        //Generate vertices based on square size one square at a time and add to ADT
                        GridMesh.createVertex(x,y,0,random);
                        GridMesh.createVertex(x+square_size,y,0,random);
                        GridMesh.createVertex(x+square_size,y+square_size,0,random);
                        GridMesh.createVertex(x,y+square_size,0,random);

                        //connect consecutive vertices with segments and add to ADT
                        GridMesh.segmentData.add(GridMesh.createSegment(GridMesh.vertexData.size()-4,GridMesh.vertexData.size()-3));
                        GridMesh.segmentData.add(GridMesh.createSegment(GridMesh.vertexData.size()-3,GridMesh.vertexData.size()-2));
                        GridMesh.segmentData.add(GridMesh.createSegment(GridMesh.vertexData.size()-2,GridMesh.vertexData.size()-1));
                        GridMesh.segmentData.add(GridMesh.createSegment(GridMesh.vertexData.size()-1,GridMesh.vertexData.size()-4));
                        //Generate one polygon per square and add to ADT
                        GridMesh.polygonData.add(GridMesh.createPolygon(GridMesh.segmentData.size()-4,GridMesh.segmentData.size()-3,GridMesh.segmentData.size()-2,GridMesh.segmentData.size()-1));

                    }

                }



                //give vertices random colors and a thickness
                int vertexPointer=0;
                for(Vertex v: GridMesh.vertexData){
                    GridMesh.vertexData.set(vertexPointer,PropertyAdd.AddVertexProperties(v,"6"));
                    vertexPointer+=1;
                }
                //give each segment a random color
                int segmentPointer=0;
                for(Segment s: GridMesh.segmentData){
                    //Assign segment color based on average of associated vertices
                    GridMesh.segmentData.set(segmentPointer,PropertyAdd.RandomSegmentColor(s));
                    segmentPointer+=1;
                }


                int polyPointer=0;
                for (Polygon p: GridMesh.polygonData){
                    List<Segment> colorSegments = new ArrayList<Segment>();

                    //get all segments in given polygon
                    for(int id: p.getSegmentIdxsList()){
                        colorSegments.add(GridMesh.segmentData.get(id));
                    }

                    //if not in debug mode, make color average of all segments and move on
                    if (!CommArgs.debug){
                        Property averageColor = PropertyAdd.averageColor(colorSegments);
                        Polygon colored = Polygon.newBuilder(p).addProperties(averageColor).build();

                        GridMesh.polygonData.set(GridMesh.polygonData.indexOf(p),colored);
                    }else{

                        //if in debug mode and a vertex which denotes the centroid of the square
                        double centroidX=0;
                        double centroidY=0;
                        for (int i=0; i<p.getSegmentIdxsList().size();i+=2){
                            centroidX+=(GridMesh.vertexData.get(GridMesh.segmentData.get(p.getSegmentIdxs(i)).getV1Idx()).getX());
                            centroidX+=(GridMesh.vertexData.get(GridMesh.segmentData.get(p.getSegmentIdxs(i)).getV2Idx()).getX());
                            centroidY+=(GridMesh.vertexData.get(GridMesh.segmentData.get(p.getSegmentIdxs(i)).getV1Idx()).getY());
                            centroidY+=(GridMesh.vertexData.get(GridMesh.segmentData.get(p.getSegmentIdxs(i)).getV2Idx()).getY());
                        }
                        //set color of this vertex to red
                        GridMesh.vertexData.add(PropertyAdd.debugRed(GridMesh.createVertex((int)centroidX/4,(int)centroidY/4,0,random)));
                        //add this vertex as polygon's centroid and made the polygon black
                        Polygon centered = GridMesh.polygonData.set(GridMesh.polygonData.indexOf(p),GridMesh.createPolygon(p,GridMesh.vertexData.size()-1));
                        GridMesh.polygonData.set(polyPointer,PropertyAdd.debugBlack(centered));
                    }
                    polyPointer+=1;
                }
                //build entire mesh
                return Mesh.newBuilder().addAllVertices(GridMesh.vertexData).addAllSegments(GridMesh.segmentData).addAllPolygons(GridMesh.polygonData).build();

            }

        }


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
