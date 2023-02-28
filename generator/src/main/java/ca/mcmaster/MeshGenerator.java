package ca.mcmaster;

import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.generator.CommArgs;
import ca.mcmaster.cas.se2aa4.a2.generator.MeshData;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.generator.PropertyAdd;


import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

public class MeshGenerator {
    //mesh properties dependent on command line inputs
    private final int width = CommArgs.width;
    private final int height = CommArgs.height;
    private final int square_size = CommArgs.square_size;
    private final int offset = CommArgs.offset;

    MeshData TestMesh = new MeshData();
    public Mesh generate() {
        //generate randomly placed points, amount of which depends on grid size and square size
        List<Coordinate> coords = populatePoints(width, height, square_size, offset); //generate grid points with a random x,y offset
        //apply voronoi diagram creation and lloyd relaxation
        voronoi(coords);
        lloyd(4);
        //trim canvas so that all polygons are inside bounds
        trimVertexPosition();
        //create final mesh
        return Mesh.newBuilder().addAllVertices(TestMesh.vertexData).addAllSegments(TestMesh.segmentData).addAllPolygons(TestMesh.polygonData).build();
    }


    /**
     *
     * @param width  width of point generation area
     * @param height  height of point generation area
     * @param square_size frequency of grid points
     * @param offset deviation from absolute grid position
     * @return list of x,y coordinates
     */
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


    /**
     * Creates vertex object from coordinate
     * @param c source coordinate
     * @return Vertex with coordinate x,y data
     */

    /**
     * Add random color to a vertex
     * @param v source vertex
     * @return randomly colored vertex
     */


    /**
     * @return random color property
     */




    /**
     * Used to determine the average color between two vertices in a mesh
     * @param v1 a colored Vertex
     * @param v2 a colored Vertex
     * @return the average color between the two vertices
     */

    /**
     * Move all off-canvas vertices to canvas edge
     */

    public void trimVertexPosition(){
            for (int i = 0; i < TestMesh.vertexData.size(); i++) {
                Vertex v = TestMesh.vertexData.get(i);

                List<Property> properties = v.getPropertiesList();
                //check if each vertex exceeds grid bounds
                if (v.getX() > width || v.getY() > height) {
                    //compute new x,y values
                    //if coordinate is greater than bound, set it equal to bound
                    double x = (v.getX() > width) ? width : v.getX();
                    double y = (v.getY() > height) ? height : v.getY();

                    //replace current vertex with new one on canvas edge
                    TestMesh.vertexData.set(i, Vertex.newBuilder().setX(x).setY(y).addAllProperties(properties).build());
                }
            }
    }



    public List<Coordinate> getCentroids(List<Polygon>polygons){

        List<Coordinate> centroids = new ArrayList<Coordinate>();


        for(Polygon p: polygons){
            //get the centroid index of each polygon and access its coordinates to return centroids as coordinate values
            Vertex v = TestMesh.vertexData.get(p.getCentroidIdx());
            Coordinate c = new Coordinate(v.getX(), v.getY());
            centroids.add(c);
        }

        return centroids;
    }



    public void voronoi(List<Coordinate> coords){
        //clear all data
        TestMesh.vertexData.clear();
        TestMesh.segmentData.clear();
        TestMesh.polygonData.clear();

        //create new voronoi builder based on previously generated coordinates
        VoronoiDiagramBuilder voronoi = new VoronoiDiagramBuilder();
        voronoi.setSites(coords); //perform voronoi calculations for generated points

        GeometryFactory factory = new GeometryFactory();
        Geometry rawVoronoiGeometry = voronoi.getDiagram(factory);//convert voronoi diagram into Geometry object
        List<Geometry> voronoiCells = new ArrayList<Geometry>(); //store individual voronoi cells

        //iterate over each voronoi cell contained within parent geometry
        for(int i=0; i<rawVoronoiGeometry.getNumGeometries();  i++){
            voronoiCells.add(rawVoronoiGeometry.getGeometryN(i));
        }


        //convert voronoi geometry into Polygons
        for(Geometry cell : voronoiCells){
            //store cell id's of all cells that the current cell touches
            List<Integer> neighbour = new ArrayList<>();
            for (int i=0;i<voronoiCells.size();i++){
                if (cell.touches(voronoiCells.get(i))){
                    neighbour.add(i);
                }
            }
            //store number of vertices,segments to make index operations simple and relative to current polygon
            int vertexOffset=TestMesh.vertexData.size();
            int segmentOffset=TestMesh.segmentData.size();

            List<Integer> segId = new ArrayList<Integer>();
            Coordinate[] cellCoords = cell.getCoordinates(); //get coordinate x,y pairs

            for(int i=0; i<cellCoords.length;i++){
                //if in debug mode add red color property to vertices,otherwise create it normally with default color
                if (CommArgs.debug){
                    TestMesh.vertexData.add(PropertyAdd.debugRed(TestMesh.createVertex(cellCoords[i]))); //create vertex from x,y data
                }else{
                    TestMesh.vertexData.add(TestMesh.createVertex(cellCoords[i]));
                }

                //prevent OOB error by skipping the last vertex
                if(i!= cellCoords.length-1){
                    segId.add(i+segmentOffset);
                    //add red color to segments if in debug otherwise set to black
                    if (!CommArgs.debug){
                        TestMesh.segmentData.add(PropertyAdd.AddSegmentProperties(TestMesh.createSegment(i+vertexOffset,i+1+vertexOffset)));
                    }else{
                        TestMesh.segmentData.add(PropertyAdd.debugRed(TestMesh.createSegment(i+vertexOffset,i+1+vertexOffset)));
                    }
                }
            }

            Property current_color = PropertyAdd.randomColor();
            //get polygon centroid location and create vertex with randomized color
            //if in debug mode, set vertex color to red and polygon color to black
            //if not set centroid and polygon to same random color
            if (!CommArgs.debug){
                TestMesh.vertexData.add(PropertyAdd.setToPolygon(TestMesh.createVertex(cell.getCentroid().getCoordinate()),current_color));
                TestMesh.polygonData.add(PropertyAdd.polyColor(TestMesh.createPolygon(segId,TestMesh.vertexData.size()-1,neighbour),current_color));
            }else{
                TestMesh.vertexData.add(PropertyAdd.debugRed(TestMesh.createVertex(cell.getCentroid().getCoordinate())));
                TestMesh.polygonData.add(PropertyAdd.debugBlack(TestMesh.createPolygon(segId,TestMesh.vertexData.size()-1,neighbour)));
            }

            //create polygon with segment data extracted from cell and neighbour ID values^^
            neighbour.clear();
        }
        for (Polygon p: TestMesh.polygonData){
            //draw segments for each neighbour relation, set to transparent if not in debug and light gray if in debug
            for (int n: p.getNeighborIdxsList()){
                if (!CommArgs.debug){
                    TestMesh.segmentData.add(PropertyAdd.setTransparent(TestMesh.createSegment(p.getCentroidIdx(),TestMesh.polygonData.get(n).getCentroidIdx())));
                }else{
                    TestMesh.segmentData.add(PropertyAdd.debugGrey(TestMesh.createSegment(p.getCentroidIdx(),TestMesh.polygonData.get(n).getCentroidIdx())));
                }
            }
        }


    }


    public void lloyd( int iterations) {


        for (int i = 0; i < iterations; i++) {

            List<Coordinate> coords = getCentroids(TestMesh.polygonData);

            voronoi(coords);


        }

    }



}
