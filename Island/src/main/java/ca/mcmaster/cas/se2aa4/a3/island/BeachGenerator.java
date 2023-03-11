package ca.mcmaster.cas.se2aa4.a3.island;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.TileType;


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

    if(!Tiles.getTileType(p).equals("Land")) return false; 
    for (int i: p.getNeighborIdxsList()){
        if (Tiles.getTileType(originalPolygons.get(i)).equals("Water")){
            return true; 
        }
    }
    return false; 
}

    
}
