package ca.mcmaster.cas.se2aa4.a3.island.Water;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Humidity;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
/**Produce Given Number of lakes of various sizes**/
public class LakesFactory {

    List<Structs.Polygon> polysNew = new ArrayList<>();
    public Structs.Mesh RandomLakes(int lakeNum, Structs.Mesh aMesh){
        List<Structs.Polygon> polys = aMesh.getPolygonsList();
        for (Structs.Polygon p: polys){
            this.polysNew.add(p);
        }
        List<Integer> LakeCandidates = getLakeCandidates(polys);
        Random LakeChance = new Random();

        while (lakeNum>0){
            int LakeId = LakeChance.nextInt(LakeCandidates.size()-1);
            this.polysNew.set((int)LakeCandidates.get(LakeId), Tiles.setType(polys.get(LakeCandidates.get(LakeId)), Tiles.TileType.LAKE));
            LakeExpansion(polysNew,polysNew.get((int)LakeCandidates.get(LakeId)));
            LakeCandidates = getLakeCandidates(this.polysNew);
            lakeNum-=1;
        }
        return Structs.Mesh.newBuilder().addAllVertices(aMesh.getVerticesList()).addAllSegments(aMesh.getSegmentsList()).addAllPolygons(this.polysNew).build();

    }


   



    private boolean CheckNeighbour(Structs.Polygon p,List<Structs.Polygon> polys){
        for (int i: p.getNeighborIdxsList()) {
            if (!Tiles.getTileType(polys.get(i)).equals("Land")) {
                return false;
            }
        }
        return true;
    }


  


    private List<Integer> getLakeCandidates(List<Structs.Polygon> polys){
        List<Integer> LakeCandidates = new ArrayList<>();
        for (Structs.Polygon p: polys){
            if (Tiles.getTileType(p).equals("Land")){
                if (CheckNeighbour(p,polys)) LakeCandidates.add(polys.indexOf(p));
            }
        }
        return LakeCandidates;
    }





 

    private void LakeExpansion(List<Structs.Polygon> polys,Structs.Polygon p){
        Random random = new Random();
        for (int n:p.getNeighborIdxsList()){
            if (random.nextInt(100)>85){
                this.polysNew.set(n,Tiles.setType(polys.get(n), Tiles.TileType.LAKE));
            }
        }
    }
}
