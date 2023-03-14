package ca.mcmaster.cas.se2aa4.a3.island.IslandWater;
import ca.mcmaster.cas.se2aa4.a3.island.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class LakesFactory {
    public Structs.Mesh RandomLakes(int lakeNum, Structs.Mesh aMesh){
        List<Structs.Polygon> polys = aMesh.getPolygonsList();
        List<Structs.Polygon> polysNew = new ArrayList<>();
        for (Structs.Polygon p: polys){
            polysNew.add(p);
        }
        List<Integer> LakeCandidates = getLakeCandidates(polys);
        Random LakeChance = new Random();

        while (lakeNum>0){
            int LakeId = LakeChance.nextInt(LakeCandidates.size()-1);
            polysNew.set((int)LakeCandidates.get(LakeId),Tiles.setType(polys.get(LakeCandidates.get(LakeId)), Tiles.TileType.LAGOON));
            LakeCandidates = getLakeCandidates(polysNew);
            lakeNum-=1;
        }

        return Structs.Mesh.newBuilder().addAllVertices(aMesh.getVerticesList()).addAllSegments(aMesh.getSegmentsList()).addAllPolygons(polysNew).build();

    }

    public boolean CheckNeighbour(Structs.Polygon p,List<Structs.Polygon> polys){
        for (int i: p.getNeighborIdxsList()) {
            if (!Tiles.getTileType(polys.get(i)).equals("Land")) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> getLakeCandidates(List<Structs.Polygon> polys){
        List<Integer> LakeCandidates = new ArrayList<>();
        for (Structs.Polygon p: polys){
            if (Tiles.getTileType(p).equals("Land")){
                if (CheckNeighbour(p,polys)) LakeCandidates.add(polys.indexOf(p));
            }
        }
        return LakeCandidates;
    }
}
