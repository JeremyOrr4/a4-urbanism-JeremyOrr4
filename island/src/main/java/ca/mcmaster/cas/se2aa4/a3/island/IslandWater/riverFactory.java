package ca.mcmaster.cas.se2aa4.a3.island.IslandWater;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class riverFactory {
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

    public Structs.Segment SetasRiver(Structs.Segment s){
        Structs.Property isRiver = Structs.Property.newBuilder().setKey("IsRiver").setValue("True").build();
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue("0,0,255").build();
        return Structs.Segment.newBuilder(s).addProperties(isRiver).addProperties(color).build();
    }

    public List<Structs.Segment> riverExpansion(Structs.Mesh aMesh,List<Structs.Segment> segs,Structs.Segment s){
        List<Structs.Vertex> vert = aMesh.getVerticesList();
        List<Structs.Segment> seg = aMesh.getSegmentsList();
        List<Structs.Polygon> poly = aMesh.getPolygonsList();
        List<Structs.Segment> segNew = new ArrayList<>();
        for (Structs.Segment k: segs){
            segNew.add(k);
        }
        boolean ReachedWater = false;
        Structs.Segment currentSeg = s;
        ArrayList<Structs.Segment> Marked = new ArrayList<>();
        int MarkedTrack =0;
        Marked.add(s);
        int check = 20;
        while (!ReachedWater && check >0){
            for (Structs.Polygon p: poly){
                if (p.getSegmentIdxsList().contains(segNew.indexOf(currentSeg))){
                    if (Tiles.getTileType(p).equals("Water")||Tiles.getTileType(p).equals("Lagoon")){
                        ReachedWater=true;
                    }
                }
            }
            int segCount =0;
            for (Structs.Segment x: segNew){
                if((x.getV1Idx()==currentSeg.getV1Idx() || x.getV2Idx()==currentSeg.getV1Idx())&&!Marked.contains(x)){
                    for (Structs.Polygon m:poly){
                        if (m.getSegmentIdxsList().contains(currentSeg)){
                            MarkedTrack+=1;
                            if (MarkedTrack==1){
                                for (int l: m.getSegmentIdxsList()){
                                    Marked.add(seg.get(l));
                                }
                                MarkedTrack=0;
                            }
                        }
                    }
                    segNew.set(segCount,SetasRiver(x));
                    currentSeg = segNew.get(segCount);
                    Marked.add(currentSeg);
                    break;
                }else if((x.getV1Idx()==currentSeg.getV2Idx() || x.getV2Idx()==currentSeg.getV2Idx())&&!Marked.contains(x)){
                    for (Structs.Polygon m:poly){
                        if (m.getSegmentIdxsList().contains(currentSeg)){
                            MarkedTrack+=1;
                            if (MarkedTrack==1){
                                for (int l: m.getSegmentIdxsList()){
                                    Marked.add(seg.get(l));
                                }
                                MarkedTrack=0;
                            }
                        }
                    }
                    segNew.set(segCount,SetasRiver(x));
                    currentSeg = segNew.get(segCount);
                    Marked.add(currentSeg);
                    break;
                }
                segCount+=1;
            }
            check-=1;
        }


        return segNew;
    }
}
