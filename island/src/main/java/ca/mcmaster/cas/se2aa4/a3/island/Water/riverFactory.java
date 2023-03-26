package ca.mcmaster.cas.se2aa4.a3.island.Water;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor;
import ca.mcmaster.cas.se2aa4.a3.island.Cell.Edge;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class riverFactory {



    public static void generateRivers(List<Edge> edges, int num){


    }

    
    public Structs.Mesh riverGenerator(Structs.Mesh aMesh,int riverNum){
        List<Structs.Segment> segs = new ArrayList<>();
        List<Integer> MarkedPolys = new ArrayList<>();
        Random bag = new Random();
        for (Structs.Segment s: aMesh.getSegmentsList()){
            segs.add(s);
        }
        for (Structs.Polygon p: aMesh.getPolygonsList()){
            if (Tiles.getTileType(p).equals("Land")){
                for (int n: p.getSegmentIdxsList()){
                    Structs.Segment s = aMesh.getSegmentsList().get(n);
                    if (Extractor.getVertexElevation(aMesh.getVerticesList().get(s.getV1Idx()))>100 && riverNum>0 && bag.nextInt()>90 && !MarkedPolys.contains(aMesh.getPolygonsList().indexOf(p))){
                        if (!Extractor.isRiver(s)){
                            s = SetasRiver(s);
                            for (int k: p.getNeighborIdxsList()){
                                MarkedPolys.add(k);
                            }
                            MarkedPolys.add(aMesh.getPolygonsList().indexOf(p));
                            riverNum-=1;
                            segs.set(n,s);
                            segs = riverExpansion(aMesh,segs,s);
                        }
                    }
                }
            }
        }
        return Structs.Mesh.newBuilder().addAllVertices(aMesh.getVerticesList()).addAllSegments(segs).addAllPolygons(aMesh.getPolygonsList()).build();
    }

    private Structs.Segment SetasRiver(Structs.Segment s){
        Structs.Property isRiver = Structs.Property.newBuilder().setKey("IsRiver").setValue("True").build();
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue("0,0,255").build();
        return Structs.Segment.newBuilder(s).addProperties(isRiver).addProperties(color).build();
    }

    private double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt( Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
    }

    private List<Structs.Segment> riverExpansion(Structs.Mesh aMesh,List<Structs.Segment> segs,Structs.Segment s) {
        List<Structs.Vertex> vert = aMesh.getVerticesList();
        List<Structs.Segment> seg = aMesh.getSegmentsList();
        List<Structs.Polygon> poly = aMesh.getPolygonsList();
        List<Structs.Segment> segNew = new ArrayList<>();
        for (Structs.Segment k : segs) {
            segNew.add(k);
        }
        int range = 50;
        double finalX=0;
        double finalY=0;
        double distance=0;
        Structs.Segment current = s;
        for (Structs.Polygon p: poly){
            if (Tiles.getTileType(p).equals("Water")||Tiles.getTileType(p).equals("Lake")||Tiles.getTileType(p).equals("Lagoon")){
                if (distance(vert.get(p.getCentroidIdx()).getX(),vert.get(p.getCentroidIdx()).getY(),vert.get(s.getV1Idx()).getX(),vert.get(s.getV1Idx()).getY())<range){
                    finalX= vert.get(p.getCentroidIdx()).getX();
                    finalY= vert.get(p.getCentroidIdx()).getY();
                    distance = distance(vert.get(p.getCentroidIdx()).getX(),vert.get(p.getCentroidIdx()).getY(),vert.get(s.getV1Idx()).getX(),vert.get(s.getV1Idx()).getY());
                    break;
                }
            }
            range+=10;
        }
        for (int i=0;i<10;i++){
            for (Structs.Segment x: segs){
                if (x.getV1Idx()==current.getV1Idx() && distance(finalX,finalY,vert.get(x.getV2Idx()).getX(),vert.get(x.getV2Idx()).getY())<distance){
                    current = x;
                    segs.set(segs.indexOf(x),SetasRiver(x));
                    distance = distance(finalX,finalY,vert.get(x.getV2Idx()).getX(),vert.get(x.getV2Idx()).getY());
                    break;
                }else if (x.getV1Idx()==current.getV2Idx() && distance(finalX,finalY,vert.get(x.getV2Idx()).getX(),vert.get(x.getV2Idx()).getY())<distance){
                    current = x;
                    segs.set(segs.indexOf(x),SetasRiver(x));
                    distance = distance(finalX,finalY,vert.get(x.getV2Idx()).getX(),vert.get(x.getV2Idx()).getY());
                    break;
                }
                else if (x.getV2Idx()==current.getV1Idx() && distance(finalX,finalY,vert.get(x.getV1Idx()).getX(),vert.get(x.getV1Idx()).getY())<distance){
                    current = x;
                    segs.set(segs.indexOf(x),SetasRiver(x));
                    distance = distance(finalX,finalY,vert.get(x.getV1Idx()).getX(),vert.get(x.getV1Idx()).getY());
                    break;
                }else if (x.getV2Idx()==current.getV2Idx() && distance(finalX,finalY,vert.get(x.getV1Idx()).getX(),vert.get(x.getV1Idx()).getY())<distance){
                    current = x;
                    segs.set(segs.indexOf(x),SetasRiver(x));
                    distance = distance(finalX,finalY,vert.get(x.getV1Idx()).getX(),vert.get(x.getV1Idx()).getY());
                    break;
                }
            }
            if (distance < 50){
                break;
            }
        }
        return segs;
    }
}
