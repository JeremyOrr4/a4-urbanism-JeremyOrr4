package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Circle1;

import java.util.ArrayList;

public class EntryPoint {
    public static Mesh meshTest(Mesh aMesh){
        //Make our own data structures for vertices, segments...
        ArrayList<Structs.Vertex> vertices = new ArrayList<>();
        ArrayList<Structs.Segment> segments = new ArrayList<>();
        ArrayList<Structs.Polygon> polygons = new ArrayList<>();

       


        for (Structs.Vertex v: aMesh.getVerticesList()){
            vertices.add(v);
        }
        for (Structs.Segment s: aMesh.getSegmentsList()){
            segments.add(s);
        }
        for (Structs.Polygon p: aMesh.getPolygonsList()){
            polygons.add(p);
        }

     


        // circle island = new circle();
        // polygons = island.shapeFunction(polygons,vertices);
        // return Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build();


        Circle1 c = new Circle1(500, 500, 400); 
        IslandShaper shaper = new IslandShaper(c);


        BeachGenerator bg = new BeachGenerator(shaper.shapedIsland(aMesh));
        
        return bg.beachMesh(); 

       // return shaper.shapedIsland(aMesh);  

    }

}

