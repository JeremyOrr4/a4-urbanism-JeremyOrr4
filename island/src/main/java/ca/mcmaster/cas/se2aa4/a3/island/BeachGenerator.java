package ca.mcmaster.cas.se2aa4.a3.island;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles.TileType;


public class BeachGenerator {

Mesh original; 
List<Polygon> originalPolygons; 
List<Polygon> newPolygons; 

public BeachGenerator(Mesh aMesh){

    this.original = aMesh; 
    originalPolygons = aMesh.getPolygonsList();
    newPolygons = new ArrayList<Polygon>();
}


public  Mesh beachMesh(){
    
    for(Polygon p : originalPolygons){

        Polygon newTile = p; 
        if(isBeachTile(p)) newTile = Tiles.setType(p, TileType.BEACH);
        newPolygons.add(newTile); 
    }

    return Mesh.newBuilder().addAllVertices(original.getVerticesList()).addAllSegments(original.getSegmentsList()).addAllPolygons(newPolygons).build(); 
}



/**
 *  
 * @param p A polygon who's beach status is to be tested
 * @return true if the polygon should be a beach (neigbors water)
 */
public  boolean isBeachTile(Polygon p){

    if(Tiles.getTileType(p).equals("Water")) return false; 
    if(Tiles.getTileType(p).equals("Lagoon")) return false; 
    for (int i: p.getNeighborIdxsList()){

        
        String type = Tiles.getTileType(originalPolygons.get(i)); 

    
        if (type.equals("Water")   || type.equals("Lagoon")){
            return true; 
        }
    }
    return false; 
}



/**
 * Checks if a given land polygon is completely surrounded by water
 * We may want to trim these depending on the deisred output 
 * Otherwise mini-islands may generate for irregular shapes
 * 
 * @param p
 * @return
 */
public boolean isIsolated(Polygon p){

    for(int id : p.getNeighborIdxsList()){

        String type = Tiles.getTileType(originalPolygons.get(id)); 
        if(!type.equals("Water")) return false; 

    }
    return true; 



}

    
}
