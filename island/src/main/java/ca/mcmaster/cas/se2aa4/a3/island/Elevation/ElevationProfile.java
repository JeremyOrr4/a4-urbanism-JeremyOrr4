package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor;

import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;

public abstract class ElevationProfile {




 

 





    abstract double ProduceElevation(double x, double y, double centerX, double centerY);



    public Structs.Mesh GenerateIslandElevations(Structs.Mesh aMesh){
        List<Structs.Vertex> newVertices = new ArrayList<>();
        for (Structs.Vertex v: aMesh.getVerticesList()){

            Structs.Property elevation = Structs.Property.newBuilder().setKey("Elevation").setValue(""+ProduceElevation(v.getX(), v.getY(),0 ,0 )).build();
            newVertices.add(Structs.Vertex.newBuilder(v).addProperties(elevation).build());
        }
        return Structs.Mesh.newBuilder().addAllVertices(newVertices).addAllSegments(aMesh.getSegmentsList()).addAllPolygons(aMesh.getPolygonsList()).build();
    }

    
public Structs.Mesh generatePolygonElevations(Structs.Mesh aMesh){
    List<Structs.Polygon> oldPolygons = aMesh.getPolygonsList();
    List<Structs.Vertex> vertices = aMesh.getVerticesList();
    List<Structs.Segment> segments = aMesh.getSegmentsList();
    List<Structs.Polygon> newPolygons = new ArrayList<>();

    for (Structs.Polygon p: oldPolygons){
        if (Tiles.getTileType(p).equals("Land")){
            int sum=0;
            int vertexCount=0;
            for (int n: p.getSegmentIdxsList()){
                sum += Extractor.getVertexElevation(vertices.get(segments.get(n).getV1Idx()));
                sum += Extractor.getVertexElevation(vertices.get(segments.get(n).getV2Idx()));
                vertexCount+=2;
            }
            sum=sum/vertexCount;
            Structs.Property elevation = Structs.Property.newBuilder().setKey("Elevation").setValue(""+sum).build();
            newPolygons.add(Structs.Polygon.newBuilder(p).addProperties(elevation).build());
        }else{
            newPolygons.add(p);
        }
    }
    return Structs.Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(newPolygons).build();

    
}









    
}
