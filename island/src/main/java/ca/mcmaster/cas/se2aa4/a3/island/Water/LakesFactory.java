package ca.mcmaster.cas.se2aa4.a3.island.Water;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Cell.Cell;
import ca.mcmaster.cas.se2aa4.a3.island.Cell.Type;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Humidity;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

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


    public static void createLakes(List<Cell> cells, int numLakes){

        List<Integer> lakeCandidates = getLakeCellCandidates(cells); 
        System.out.println("Z: " + lakeCandidates.size());

        Random lakeChance = new Random(); 
        for(int i=0; i<numLakes; i++){



            int lakeID =lakeChance.nextInt(lakeCandidates.size()-1);
            Cell lakeCandidate = cells.get(lakeCandidates.get(lakeID)); 



            if(lakeCandidate.isTerrain() && !bordersWater(lakeCandidate, cells)){
               lakeCandidate.setType(Type.LAKE);
                LakeExpansion(cells, lakeCandidate);
            } 
        }

    }




    private boolean CheckNeighbour(Structs.Polygon p,List<Structs.Polygon> polys){
        for (int i: p.getNeighborIdxsList()) {
            if (!Tiles.getTileType(polys.get(i)).equals("Land")) {
                return false;
            }
        }
        return true;
    }


    //CELL EQUIVALENT 
    public static boolean bordersWater(Cell cell, List<Cell> cells){


        for(Integer i : cell.getNeighborCells()){
            if(!cell.isTerrain()) return true; 
            if(!cells.get(i).isTerrain() || cells.get(i).getType() == Type.BEACH ){
                return true; 
            }
        }
        return false; 


        // for(int i : cell.getNeighborCells()){
        //     if(cells.get(i).getType().isWater) return true ; 
        // }
        // return false; 






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


    //CELL EQUIVALENT
    public static List<Integer> getLakeCellCandidates(List<Cell> cells){

        List<Integer> lakeCandidates = new ArrayList<>();
        for(Cell cell : cells){

           
           if(!cell.getType().isWater && !bordersWater(cell, cells)){
          
          lakeCandidates.add(cells.indexOf(cell)); 
           }
        }
        return lakeCandidates; 
    }



    //CELL EQUIVALENT
    public static void LakeExpansion(List<Cell> cells, Cell cell){
        Random random = new Random(); 

        for(int i : cell.getNeighborCells()){

            if (random.nextInt(100)>65){
               if(!bordersWater(cells.get(i), cells)) cells.get(i).setType(Type.LAKE);
            } 

        }

     


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
