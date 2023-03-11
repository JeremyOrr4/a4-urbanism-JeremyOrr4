package ca.mcmaster.cas.se2aa4.a3.island;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.TileType;


public class BeachGenerator {


public static Mesh beachMesh(Mesh aMesh){


    List<Polygon> newPolygons = new ArrayList<Polygon>();

    List<Polygon> originalPolygons = aMesh.getPolygonsList();
    
    for(Polygon p : originalPolygons){


        Polygon newTile = p; 

        if(Tiles.getTileType(p).equals("Land")){

            
           for(int i=0; i< p.getNeighborIdxsCount(); i++){
            
            Polygon neighbor = originalPolygons.get(p.getNeighborIdxs(i)); 
            
                if(Tiles.getTileType(neighbor).equals("Water")){
                    System.out.println("Edge");
                    newTile = Tiles.setType(p, TileType.BEACH);  
                  
                }

           }
         

        }
        
        newPolygons.add(newTile); 

       


    }
    

    return Mesh.newBuilder().addAllVertices(aMesh.getVerticesList()).addAllSegments(aMesh.getSegmentsList()).addAllPolygons(newPolygons).build(); 
}


    
}
