package ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**Generates and adds humidities to tiles based on culminative distance from lakes and rivers**/
public class Humidity {

    private Structs.Polygon addHumidity(Structs.Polygon p, String HumidityValue){
        Structs.Property Humidity = Structs.Property.newBuilder().setKey("Humidity").setValue(HumidityValue).build();
        return Structs.Polygon.newBuilder(p).addProperties(Humidity).build();
    }

    public Structs.Mesh GenerateHumidities(Structs.Mesh aMesh){
        Random bad = new Random();
        int baseHumidity = 500;
        List<Double> lakeCentersX = new ArrayList<>();
        List<Double> lakeCentersY = new ArrayList<>();
        List<Structs.Polygon> polys = aMesh.getPolygonsList();
        List<Structs.Segment> segs = aMesh.getSegmentsList();
        List<Structs.Vertex> verts = aMesh.getVerticesList();
        List<Structs.Polygon> polysNew = new ArrayList<>();
        for (Structs.Polygon p: polys){
            polysNew.add(p);
            if (Tiles.getTileType(p).equals("Lake")){
                lakeCentersX.add(verts.get(p.getCentroidIdx()).getX());
                lakeCentersY.add(verts.get(p.getCentroidIdx()).getY());
            }
        }
        for (Structs.Polygon p: polys){
            int HumidTrack = 0;
            int MaxHumidity = 600;
            if (!Tiles.getTileType(p).equals("Water") && !Tiles.getTileType(p).equals("Lake")){
                for (int i=0; i<lakeCentersX.size();i++){
                    if (Math.abs(lakeCentersX.get(i)-verts.get(p.getCentroidIdx()).getX())+Math.abs(lakeCentersY.get(i)-verts.get(p.getCentroidIdx()).getY())<400){
                        HumidTrack+=400-(Math.abs(lakeCentersX.get(i)-verts.get(p.getCentroidIdx()).getX())+Math.abs(lakeCentersY.get(i)-verts.get(p.getCentroidIdx()).getY()));
                    }
                }
                if (HumidTrack>1000) HumidTrack = 1000;
                if (HumidTrack<200) HumidTrack = 200;
                for (int i: p.getSegmentIdxsList()){
                    if (Extractor.isRiver(segs.get(i))){
                        HumidTrack+=100;
                        break;
                    }
                }
                polysNew.set(polys.indexOf(p),addHumidity(p,""+(int)(HumidTrack/2+200)));
                HumidTrack=0;
            }
        }
        return Structs.Mesh.newBuilder().addAllVertices(aMesh.getVerticesList()).addAllSegments(aMesh.getSegmentsList()).addAllPolygons(polysNew).build();
    }
}
