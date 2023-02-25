package ca.mcmaster;

import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.generator.MeshData;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;



import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import org.locationtech.jts.triangulate.quadedge.QuadEdgeSubdivision;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;

public class GenTest {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 40;
    private final int offset = 15;

    MeshData TestMesh = new MeshData();
    public Mesh generate() {

        List<Coordinate> coords = populatePoints(width, height, square_size, offset); //generate grid points with a random x,y offset

        voronoi(coords);
        lloyd(4);
        // Delaunay(coords); JEREMY

        //OPTIONAL: Filters all polygons whose centers are outside canvas area
        //filterPolygons(polygons,vertices );

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
    public static Vertex createVertex(Coordinate c){
        return Vertex.newBuilder().setX(c.x).setY(c.y).build();
    }

    /**
     * Add random color to a vertex
     * @param v source vertex
     * @return randomly colored vertex
     */
    public static Vertex randomized(Vertex v){
        Vertex modified = Vertex.newBuilder(v).addProperties(randomColor()).build();
        return modified;
    }


    /**
     * @return random color property
     */
    public static Property randomColor(){

        Random bag = new Random();
        int red = bag.nextInt(255);
        int green = bag.nextInt(255);
        int blue = bag.nextInt(255);
        int alpha = 255;



        String colorCode = String.format("%d,%d,%d,%d",red,green,blue,alpha);
        return Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();

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


    public  void filterPolygons(List<Polygon> polygons ){
        //iterate over all polygons backwards
        for (int i = polygons.size()-1; i >=0 ; i--) {

            Polygon p = polygons.get(i);
            Vertex centroid = TestMesh.vertexData.get(p.getCentroidIdx()); //get the centroid vertex from each polygon

            if(centroid.getX()>width || centroid.getX()<0 || centroid.getY()>height || centroid.getY()<0 ){
                polygons.remove(i);  //filter polygons outside of canvas dimensions
            }
        }
    }


    /**
     * Move all off-canvas vertices to canvas edge
     */
    public void trimVertexPosition(){

        for(int i=0; i<TestMesh.vertexData.size();i++){
            Vertex v = TestMesh.vertexData.get(i);

            List<Property> properties = v.getPropertiesList();

            if (v.getX() > width || v.getY() > height){

                //compute new x,y values
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

            Vertex v = TestMesh.vertexData.get(p.getCentroidIdx());
            Coordinate c = new Coordinate(v.getX(), v.getY());
            centroids.add(c);
        }

        return centroids;
    }



    public void voronoi(List<Coordinate> coords){

        TestMesh.vertexData.clear();
        TestMesh.segmentData.clear();
        TestMesh.polygonData.clear();


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
                TestMesh.vertexData.add(TestMesh.createVertex(cellCoords[i])); //create vertex from x,y data

                //prevent OOB error by skipping the last vertex
                if(i!= cellCoords.length-1){
                    segId.add(i+segmentOffset);
                    TestMesh.segmentData.add(TestMesh.createSegment(i+vertexOffset,i+1+vertexOffset));
                }
            }


            //get polygon centroid location and create vertex with randomized color
            TestMesh.vertexData.add(TestMesh.randomized(TestMesh.createVertex(cell.getCentroid().getCoordinate())));


            //create polgon with segment data extracted from cell
            TestMesh.polygonData.add(TestMesh.createPolygon(segId,TestMesh.vertexData.size()-1));
        }


    }


    public void lloyd( int iterations) {


        for (int i = 0; i < iterations; i++) {

            List<Coordinate> coords = getCentroids(TestMesh.polygonData);

            voronoi(coords);


        }

    }

    // // Getting Neighbours of polygon JEREMY
    // public QuadEdgeSubdivision Delaunay(List<Coordinate> coords) {

    //     DelaunayTriangulationBuilder builder = new DelaunayTriangulationBuilder();
    //     builder.setSites(coords);
    //     QuadEdgeSubdivision subDiv = builder.getSubdivision();
    //     return subDiv;
    // }



}
