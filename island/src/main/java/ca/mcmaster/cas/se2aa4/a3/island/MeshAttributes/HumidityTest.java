package ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static ca.mcmaster.cas.se2aa4.a3.island.Extractor.getPolyHumidity;

public class HumidityTest{
    List<Structs.Polygon> polys = new ArrayList<>();
    List<Structs.Segment> segs = new ArrayList<>();
    ArrayList<Structs.Vertex> verts = new ArrayList<>();
    Structs.Vertex centroid1 = Structs.Vertex.newBuilder().setX(100).setY(100).build();
    Structs.Vertex centroid2 = Structs.Vertex.newBuilder().setX(110).setY(110).build();
    Structs.Vertex centroid3 = Structs.Vertex.newBuilder().setX(300).setY(300).build();

    Structs.Vertex floor = Structs.Vertex.newBuilder().setX(1200).setY(1200).build();

    private double setUpHumidity(){
        verts.add(centroid1);
        verts.add(centroid2);
        verts.add(centroid3);
        Structs.Polygon poly1 = Structs.Polygon.newBuilder().setCentroidIdx(0).build();
        Structs.Polygon poly2 = Structs.Polygon.newBuilder().setCentroidIdx(1).build();
        Structs.Polygon poly3 = Structs.Polygon.newBuilder().setCentroidIdx(2).build();
        polys.add(Tiles.setType(poly1, Tiles.TileType.LAKE));
        polys.add(poly2);
        polys.add(poly3);
        Structs.Mesh TestMesh = Structs.Mesh.newBuilder().addAllVertices(verts).addAllPolygons(polys).build();
        Humidity Humid = new Humidity();
        TestMesh = Humid.GenerateHumidities(TestMesh);
        double Humidity1 = Extractor.getPolyHumidity(TestMesh.getPolygons(1));
        double Humidity2 = Extractor.getPolyHumidity(TestMesh.getPolygons(2));
        return (Humidity1-Humidity2);
    }

    private void TestHumidity(){
        double context = setUpHumidity();
        System.out.println("Testing correctness of lake proximity humidity distribution");
        Assert.assertTrue(context>0);
        System.out.println("Test Passed -> Humidity decreases as distance from lake decreases");
    }

    private int setUpHumidityFloor(){
        verts.add(centroid1);
        verts.add(floor);
        Structs.Polygon poly1 = Structs.Polygon.newBuilder().setCentroidIdx(0).build();
        Structs.Polygon poly2 = Structs.Polygon.newBuilder().setCentroidIdx(1).build();
        polys.add(Tiles.setType(poly1, Tiles.TileType.LAKE));
        polys.add(poly2);
        Structs.Mesh TestMesh = Structs.Mesh.newBuilder().addAllVertices(verts).addAllPolygons(polys).build();
        Humidity Humid = new Humidity();
        TestMesh = Humid.GenerateHumidities(TestMesh);
        double HumidityFloor = Extractor.getPolyHumidity(TestMesh.getPolygons(1));
        return (int) HumidityFloor;
    }

    private void HumidityFloorTest(){
        int context = setUpHumidityFloor();
        System.out.println("Testing that humidity has a min value of 300");
        Assert.assertEquals(context,300);
        System.out.println("Test Passed -> Expected: 300"+" Found: "+context);
    }

    public void TestSuite(){
        HumidityFloorTest();
        TestHumidity();
    }

}
